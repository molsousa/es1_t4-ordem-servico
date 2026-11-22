/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.bo.OSProduto;
import unioeste.geral.ordemservico.bo.OSTipoServico;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.dao.OSProdutoDAO;
import unioeste.geral.ordemservico.dao.OSTipoServicoDAO;
import unioeste.geral.ordemservico.dao.OrdemServicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.exception.OrdemServicoInvalidoException;
import unioeste.geral.pessoa.col.ColPessoaFisica;

/**
 *
 * @author molsousa
 */
public class ColOrdemServico {

    private static final ZoneId ZONA = ZoneId.of("America/Sao_Paulo");

    public boolean cadastrarOrdemServico(Connection conn, OrdemServico os, int idTipoEquipamento, String cpf, int idStatusOS) throws Exception {

        if (os.getDescProblema() == null || os.getDescProblema().isEmpty()) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.DESCRICAO_INVALIDA, os.getDescProblema());
        }

        if (os.getDtEmissao() == null || !LocalDate.now(ZONA).equals(os.getDtEmissao().toLocalDate())) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.DATA_INVALIDA, os.getDtEmissao());
        }

        try {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            ColCliente colCliente = new ColCliente();

            Cliente cliente = new Cliente();
            cpf = ColPessoaFisica.formatarCPF(cpf);
            cliente.setCpf(cpf);
            ColPessoaFisica.validarCPF(cliente);

            int idCliente = colCliente.recuperarIDCliente(conn, cliente);

            return ordemServicoDAO.cadastrarOrdemServico(conn, idCliente, idTipoEquipamento, idStatusOS, os);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

    public OrdemServico recuperarOrdemServico(Connection conn, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

        List<OSTipoServico> osTipoServicos;
        List<OSProduto> osProdutos;

        ColOSTipoServico colOSTipoServico = new ColOSTipoServico();
        try {
            osTipoServicos = colOSTipoServico.recuperarOSTipoServicos(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }

        ColOSProduto colOSProduto = new ColOSProduto();
        try {
            osProdutos = colOSProduto.recuperarOSProdutos(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }

        try {
            atualizarValorTotalOS(conn, nroOS);
            return ordemServicoDAO.obterOrdemServico(conn, nroOS, osProdutos, osTipoServicos);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

    public boolean atualizarOSTecnico(Connection conn, String cpf, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }
        
        String statusOS = recuperarOrdemServico(conn, nroOS).getStatusOS().getDescricao();
        
        if (!statusOS.equals("Emitida")){
            if (!statusOS.equals("emitida")) {
                throw new OrdemServicoException("Ordem não pode ser alterada, status: " + statusOS);
            }
        }

        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        ColTecnico colTecnico = new ColTecnico();
        int idTecnico = colTecnico.recuperarIDTecnico(conn, cpf);

        try {
            return ordemServicoDAO.atualizarOSTecnico(conn, idTecnico, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public double atualizarValorTotalOS(Connection conn, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        ColOSProduto cosp = new ColOSProduto();
        ColOSTipoServico costs = new ColOSTipoServico();
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        double valorTotal = 0;

        try {
            for (OSTipoServico osts : costs.recuperarOSTipoServicos(conn, nroOS)) {
                valorTotal += osts.getValor();
            }

            for (OSProduto osp : cosp.recuperarOSProdutos(conn, nroOS)) {
                double valorTotalProdutos = osp.getQtd() * osp.getValor();
                valorTotal += valorTotalProdutos;
            }

            ordemServicoDAO.atualizarValorTotalOS(conn, nroOS, valorTotal);
            return valorTotal;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public boolean atualizarStatusOS(Connection conn, int nroOS, int idStatusOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

        try {
            return ordemServicoDAO.atualizarStatusOS(conn, nroOS, idStatusOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public boolean removerOrdemServico(Connection conn, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }
        
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();

        try {
            OSProdutoDAO ospdao = new OSProdutoDAO();
            OSTipoServicoDAO ostsdao = new OSTipoServicoDAO();

            ospdao.removerOSProduto(conn, nroOS);
            ostsdao.removerOSTipoServico(conn, nroOS);

            boolean sucesso = ordemServicoDAO.removerOrdemServico(conn, nroOS);

            return sucesso;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.bo.Tecnico;
import unioeste.geral.ordemservico.dao.OrdemServicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.exception.OrdemServicoInvalidoException;

/**
 *
 * @author molsousa
 */
public class ColOrdemServico {
    
    public boolean cadastrarOrdemServico(Connection conn, OrdemServico os, int idTipoEquipamento, String cpf, int idStatusOS) throws Exception {
        if(os.getDescProblema() == null || os.getDescProblema().isEmpty()) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.DESCRICAO_INVALIDA, os.getDescProblema());
        }
        
        if(os.getDtEmissao() == null) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.DATA_INVALIDA, os.getDtEmissao());
        }
        
        
        try {
            OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
            ColCliente colCliente = new ColCliente();
            ColStatusOS colStatusOS = new ColStatusOS();
            
            Cliente cliente = new Cliente();
            cliente.setCpf(cpf);
            
            int idCliente = colCliente.recuperarIDCliente(conn, cliente);
            
            return ordemServicoDAO.cadastrarOrdemServico(conn, idCliente, idTipoEquipamento, idStatusOS, os);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public OrdemServico recuperarOrdemServico(Connection conn, int nroOS) throws Exception {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        
        try {
            return ordemServicoDAO.obterOrdemServico(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public boolean atualizarOSTecnico(Connection conn, int idTecnico, int nroOS) throws Exception {
        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO();
        
        try {
            return ordemServicoDAO.atualizarOSTecnico(conn, idTecnico, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }
    
}

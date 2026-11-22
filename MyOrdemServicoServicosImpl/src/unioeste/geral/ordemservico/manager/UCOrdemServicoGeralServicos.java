/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.col.ColOSProduto;
import unioeste.geral.ordemservico.col.ColOSTipoServico;
import unioeste.geral.ordemservico.col.ColOrdemServico;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class UCOrdemServicoGeralServicos {

    public boolean cadastrarOrdemServico(OrdemServico os, int idStatusOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                boolean sucesso = colOrdemServico.cadastrarOrdemServico(conn, os, os.getTipoEquipamento().getCodigo(), os.getCliente().getCpf(), idStatusOS);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }
    
    public List<OrdemServico> obterOrdemServicos() throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                List<OrdemServico> ordemServicos = colOrdemServico.recuperarOrdemServicos(conn);
                conn.commit();
                return ordemServicos;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public OrdemServico obterOrdemServico(int nroOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                OrdemServico ordemServico = colOrdemServico.recuperarOrdemServico(conn, nroOS);
                conn.commit();
                return ordemServico;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public boolean atualizarTecnicoOS(String cpf, int nroOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                boolean sucesso = colOrdemServico.atualizarOSTecnico(conn, cpf, nroOS);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public boolean atualizarStatusOS(int nroOS, int idStatusOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                boolean sucesso = colOrdemServico.atualizarStatusOS(conn, nroOS, idStatusOS);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public double cadastrarOSProduto(int nroOS, int idProduto, double valorCobrado, int qtd) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOSProduto cosp = new ColOSProduto();
                double valorTotalProdutos = cosp.cadastrarOSProduto(conn, nroOS, idProduto, valorCobrado, qtd);
                conn.commit();
                return valorTotalProdutos;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public boolean cadastrarOSTipoServico(int nroOS, int idTipoServico, double valorCobrado) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOSTipoServico costs = new ColOSTipoServico();
                boolean sucesso = costs.cadastrarOSTipoServico(conn, nroOS, idTipoServico, valorCobrado);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public boolean removerOrdemServico(int nroOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                boolean sucesso = colOrdemServico.removerOrdemServico(conn, nroOS);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public boolean removerProdutoOS(int idProduto, int nroOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOSProduto colOSProduto = new ColOSProduto();
                boolean sucesso = colOSProduto.removerProdutoOS(conn, idProduto, nroOS);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }

    public boolean removerTipoServicoOS(int idTipoServico, int nroOS) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOSTipoServico colOSTipoServico = new ColOSTipoServico();
                boolean sucesso = colOSTipoServico.removerTipoServicoOS(conn, idTipoServico, nroOS);
                conn.commit();
                return sucesso;
            } catch (Exception e) {
                conn.rollback();

                if (e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }

                throw e;
            }
        }
    }
}

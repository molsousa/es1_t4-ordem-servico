/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.col.ColTipoServico;
import unioeste.geral.ordemservico.bo.TipoServico;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class UCTipoServicoGeralServicos {
    
    public int cadastrarTipoServico(String nome, double valor) throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColTipoServico colTipoServico = new ColTipoServico();
                int idTipoServico = colTipoServico.cadastrarTipoServico(conn, nome, valor);
                conn.commit();
                return idTipoServico;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        } 
    }
    
    public List<TipoServico> listarTipoServicos() throws Exception {
        
        try(Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColTipoServico colTipoServico = new ColTipoServico();
                List<TipoServico> tipoServicos = colTipoServico.recuperarTipoServicos(conn);
                conn.commit();
                return tipoServicos;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }
}

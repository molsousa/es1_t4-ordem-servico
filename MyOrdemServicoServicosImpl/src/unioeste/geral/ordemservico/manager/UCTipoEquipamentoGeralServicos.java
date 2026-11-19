/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.bo.TipoEquipamento;
import unioeste.geral.ordemservico.col.ColTipoEquipamento;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class UCTipoEquipamentoGeralServicos {

    public int cadastrarTipoEquipamento(String nomeTipoEquipamento) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColTipoEquipamento colTipoEquipamento = new ColTipoEquipamento();
                int id = colTipoEquipamento.cadastrarTipoEquipamento(conn, nomeTipoEquipamento);
                conn.commit();
                return id;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }

    public List<TipoEquipamento> listarTipoEquipamento() throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColTipoEquipamento colTipoEquipamento = new ColTipoEquipamento();
                List<TipoEquipamento> tipoEquipamentos = colTipoEquipamento.recuperarTipoEquipamentos(conn);
                conn.commit();
                return tipoEquipamentos;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }

    public TipoEquipamento obterTipoEquipamento(int codTipoEquipamento) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColTipoEquipamento colTipoEquipamento = new ColTipoEquipamento();
                TipoEquipamento tipoEquipamento = colTipoEquipamento.recuperarTipoEquipamento(conn, codTipoEquipamento);
                conn.commit();
                return tipoEquipamento;
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

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
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
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
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
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
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
            }
        }
    }
}

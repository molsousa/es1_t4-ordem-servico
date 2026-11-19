/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.bo.TipoEquipamento;

/**
 *
 * @author molsousa
 */
public class TipoEquipamentoDAO {

    public int cadastrarTipoEquipamento(Connection conn, String nomeEquipamento) throws Exception {

        String SQL = "INSERT INTO \"TipoEquipamento\" (nome_tipo_equipamento) VALUES (?) "
                + "RETURNING id_tipo_equipamento";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, nomeEquipamento);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_tipo_equipamento");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar tipo de equipamento no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Não foi possível cadastrar tipo de equipamento no banco");
    }
    
    public List<TipoEquipamento> listarTipoEquipamentos(Connection conn) throws Exception {
        
        String SQL = "SELECT * FROM \"TipoEquipamento\"";
        List<TipoEquipamento> tipoEquipamentos = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    TipoEquipamento tipoEquipamento = new TipoEquipamento();
                    
                    tipoEquipamento.setCodigo(rs.getInt("id_tipo_equipamento"));
                    tipoEquipamento.setNome(rs.getString("nome_tipo_equipamento"));
                    
                    tipoEquipamentos.add(tipoEquipamento);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipos de equipamento no banco: " + e.getMessage(), e);
        }
        
        if(tipoEquipamentos.isEmpty()) {
            throw new OrdemServicoException("Não há tipos de equipamentos na base de dados");
        }
        
        return tipoEquipamentos;
    }
    
    public TipoEquipamento buscarEquipamento(Connection conn, int codEquipamento) throws Exception {
        String SQL = "SELECT nome_tipo_equipamento FROM \"TipoEquipamento\" WHERE id_tipo_equipamento = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, codEquipamento);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    TipoEquipamento tipoEquipamento = new TipoEquipamento();
                    
                    tipoEquipamento.setCodigo(codEquipamento);
                    tipoEquipamento.setNome(rs.getString("nome_tipo_equipamento"));
                    
                    return tipoEquipamento;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipos de equipamento no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não há tipo de equipamento com esse código: " + codEquipamento);
    }
    
    public int buscarIDTipoEquipamento(Connection conn, TipoEquipamento equipamento) throws Exception {
        String SQL = "SELECT id_tipo_equipamento FROM \"TipoEquipamento\" WHERE nome_tipo_equipamento = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, equipamento.getNome());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt("id_tipo_equipamento");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipo de equipamento no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não há tipo de equipamento com esse nome: " + equipamento.getNome());
    }
}

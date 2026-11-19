/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.StatusOS;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class StatusOSDAO {
    
    public StatusOS obterStatusOS(Connection conn, int idStatusOS) throws Exception {
        String SQL = "SELECT * FROM \"StatusOS\" WHERE id_status_os = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            
            stmt.setInt(1, idStatusOS);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    StatusOS statusOS = new StatusOS();
                    
                    statusOS.setDescricao(rs.getString("descricao_status_os"));
                    
                    return statusOS;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar status da ordem de serviço no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Não foi possível status da ordem de serviço no banco");
    }
    
    public int obterIDStatusOS(Connection conn, StatusOS statusOS) throws Exception {
        String SQL = "SELECT id_status_os from \"StatusOS\" WHERE descricao_status_os = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, statusOS.getDescricao());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt("id_status_os");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar status da ordem de serviço no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Não foi possível status da ordem de serviço no banco");
    }
}

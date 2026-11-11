/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.StatusOS;
import unioeste.geral.ordemservico.dao.StatusOSDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class ColStatusOS {
    
    public StatusOS recuperarStatusOS(Connection conn, int idStatusOS) throws Exception {
        StatusOSDAO statusOSDAO = new StatusOSDAO();
        
        try {
            return statusOSDAO.obterStatusOS(conn, idStatusOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
}

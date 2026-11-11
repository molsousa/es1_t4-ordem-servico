/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.Tecnico;
import unioeste.geral.ordemservico.dao.TecnicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class ColTecnico {
    
    public Tecnico recuperarTecnicoPorID(Connection conn, int id) throws Exception {
        
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        
        try {
            return tecnicoDAO.buscarTecnicoPorID(conn, id);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
}

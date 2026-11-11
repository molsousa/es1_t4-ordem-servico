/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.dao.OrdemServicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class ColOrdemServico {
    
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
    
}

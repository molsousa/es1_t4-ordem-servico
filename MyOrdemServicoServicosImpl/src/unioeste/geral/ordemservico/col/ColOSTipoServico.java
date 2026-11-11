/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.bo.OSTipoServico;
import unioeste.geral.ordemservico.dao.OSTipoServicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class ColOSTipoServico {
    
    public List<OSTipoServico> recuperarOSTipoServicos(Connection conn, int nroOS) throws Exception {
        
        try {
            OSTipoServicoDAO ostsdao = new OSTipoServicoDAO();
            return ostsdao.obterOSTipoServicos(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
}

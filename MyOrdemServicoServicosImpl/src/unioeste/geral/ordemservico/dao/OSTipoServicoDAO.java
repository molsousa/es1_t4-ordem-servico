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
import unioeste.geral.ordemservico.bo.OSTipoServico;
import unioeste.geral.ordemservico.bo.TipoServico;
import unioeste.geral.ordemservico.col.ColTipoServico;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class OSTipoServicoDAO {
    
    public boolean cadastrarOSTipoServico(Connection conn, int nroOS, int idTipoServico, double valorCobrado) throws Exception {
        String SQL = "INSERT INTO \"OSTipoServico\" (nro_os, id_tipo_servico, valor_tipo_servico) VALUES (?,?,?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, nroOS);
            stmt.setInt(2, idTipoServico);
            stmt.setDouble(3, valorCobrado);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
        } catch(SQLException e) {
            throw new SQLException("Não foi possível cadastrar os tipo serviço");
        }
    }
    
    public List<OSTipoServico> obterOSTipoServicos(Connection conn, int nroOS) throws Exception {
        String SQL = "SELECT * FROM \"OSTipoServico\" WHERE nro_os = ?";
        List<OSTipoServico> osTipoServicos = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, nroOS);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                while(rs.next()) {
                    OSTipoServico osts = new OSTipoServico();
                    
                    int idTipoServico = rs.getInt("id_tipo_servico");
                    osts.setValor(rs.getDouble("valor_tipo_servico"));
                    
                    ColTipoServico colTipoServico = new ColTipoServico();
                    
                    TipoServico ts;
                    ts = colTipoServico.recuperarTipoServico(conn, idTipoServico);
                    osts.setTipoServico(ts);
                    
                    osTipoServicos.add(osts);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipo de serviço no banco: " + e.getMessage(), e);
        }
        
        if(osTipoServicos.isEmpty()) {
            throw new OrdemServicoException("Não há tipos de serviço para essa ordem de serviço");
        }
        
        return osTipoServicos;
    }
}

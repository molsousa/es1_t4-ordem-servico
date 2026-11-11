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
import unioeste.geral.ordemservico.bo.TipoServico;

/**
 *
 * @author molsousa
 */
public class TipoServicoDAO {

    public int cadastrarTipoServico(Connection conn, String nomeTipoServico, double valor) throws Exception {

        String SQL = "INSERT INTO \"TipoServico\" (nome_tipo_servico, valor_referencia) VALUES (?,?) "
                + "RETURNING id_tipo_servico";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, nomeTipoServico);
            stmt.setDouble(2, valor);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id_tipo_servico");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar tipo de serviço no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Não foi possível cadastrar tipo de serviço no banco");
    }

    public List<TipoServico> listarTipoServicos(Connection conn) throws Exception {

        String SQL = "SELECT * FROM \"TipoServico\"";
        List<TipoServico> tipoServicos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    TipoServico tipoServico = new TipoServico();

                    tipoServico.setCodigo(rs.getInt("id_tipo_servico"));
                    tipoServico.setNome(rs.getString("nome_tipo_servico"));
                    tipoServico.setValor(rs.getDouble("valor_referencia"));

                    tipoServicos.add(tipoServico);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipos de serviço no banco: " + e.getMessage(), e);
        }
        
        if(tipoServicos.isEmpty()) {
            throw new OrdemServicoException("Não há tipos de serviços na base de dados");
        }

        return tipoServicos;
    }
    
    public TipoServico obterTipoServico(Connection conn, int idTipoServico) throws Exception {
        String SQL = "SELECT * FROM \"TipoServico\" WHERE id_tipo_servico = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idTipoServico);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                if(rs.next()) {
                    TipoServico ts = new TipoServico();
                    
                    ts.setCodigo(idTipoServico);
                    ts.setNome(rs.getString("nome_tipo_servico"));
                    ts.setValor(rs.getDouble("valor_referencia"));
                    
                    return ts;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tipo de serviço no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não foi possível retornar tipo de serviço do banco");
    }
}

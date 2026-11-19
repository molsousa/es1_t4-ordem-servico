/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.pessoa.bo.DDD;
import unioeste.geral.pessoa.bo.DDI;
import unioeste.geral.pessoa.exception.PessoaException;
import unioeste.geral.pessoa.bo.Fone;

/**
 *
 * @author molsousa
 */
public class FoneDAO {
    
    public int cadastrarFone(Connection conn, Fone fone, int id, String nomeTabela) throws SQLException, PessoaException {
        
        String SQL = "INSERT INTO \"Fone"+nomeTabela+"\" (nro_fone,nro_ddi,nro_ddd,id_"+nomeTabela+") "
                + "VALUES (?,?,?,?) RETURNING id_fone_"+nomeTabela;
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            
            stmt.setString(1, fone.getNro());
            stmt.setInt(2, Integer.parseInt(fone.getDdi().getNro()));
            stmt.setInt(3, Integer.parseInt(fone.getDdd().getNro()));
            stmt.setInt(4, id);
            
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt("id_fone_"+nomeTabela);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar telefone no banco: " + e.getMessage(), e);
        }

        throw new PessoaException("Erro ao retornar id cadastrado");
    }

    public List<Fone> obterFones(Connection conn, int id, String nomeTabela) throws SQLException, PessoaException {

        String SQL = "SELECT nro_fone, nro_ddi, nro_ddd FROM \"Fone" + nomeTabela + "\" WHERE id_" + nomeTabela + " = ?";
        List<Fone> fones = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) {

                    Fone fone = new Fone();
                    DDD ddd = new DDD();
                    DDI ddi = new DDI();

                    fone.setNro(rs.getString("nro_fone"));
                    ddi.setNro(rs.getString("nro_ddi"));
                    ddd.setNro(rs.getString("nro_ddd"));

                    fone.setDdi(ddi);
                    fone.setDdd(ddd);

                    fones.add(fone);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar telefones no banco: " + e.getMessage(), e);
        }

        if (fones.isEmpty()) {
            throw new PessoaException("Erro ao retornar lista de telefones");
        }

        return fones;
    }
    
    public int obterIDFone(Connection conn, Fone fone, String nomeTabela) throws SQLException, PessoaException {
        
        String SQL = "SELECT * FROM \"Fone"+nomeTabela+"\" WHERE nro_fone = ? AND nro_ddd = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, fone.getNro());
            stmt.setInt(2, Integer.parseInt(fone.getDdd().getNro()));
            
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt("id_fone_"+nomeTabela);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar telefone no banco: " + e.getMessage(), e);
        }
        
        throw new PessoaException("Telefone não encontrado");
    }
    
    public boolean atualizarFone(Connection conn, Fone fone, String nomeTabela, int idFone) throws SQLException, PessoaException {
        
        String SQL = "UPDATE \"Fone"+nomeTabela+"\" SET nro_fone = ?, nro_ddi = ?, nro_ddd = ? WHERE id_fone_"+nomeTabela+" = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, fone.getNro());
            stmt.setInt(2, Integer.parseInt(fone.getDdi().getNro()));
            stmt.setInt(3, Integer.parseInt(fone.getDdd().getNro()));
            stmt.setInt(4, idFone);
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }
}

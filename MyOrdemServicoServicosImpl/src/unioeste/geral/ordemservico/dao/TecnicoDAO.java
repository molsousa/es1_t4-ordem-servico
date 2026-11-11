/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.col.ColEndereco;
import unioeste.geral.ordemservico.bo.Tecnico;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.EnderecoEspecifico;
import unioeste.geral.pessoa.bo.Fone;
import unioeste.geral.pessoa.col.ColEmail;
import unioeste.geral.pessoa.col.ColFone;

/**
 *
 * @author molsousa
 */
public class TecnicoDAO {
    
    public Tecnico buscarTecnicoPorID(Connection conn, int id) throws Exception {
        String SQL = "SELECT nome_tecnico, cpf, nro_endereco, comp_endereco, id_endereco FROM "
                + "\"Tecnico\" WHERE id_tecnico = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                if(rs.next()) {
                    Tecnico tecnico = new Tecnico();
                    Endereco endereco;
                    List<Fone> fones;
                    List<Email> emails;
                    
                    ColFone colFone = new ColFone();
                    ColEmail colEmail = new ColEmail();
                    ColEndereco colEndereco = new ColEndereco();
                    
                    tecnico.setCpf(rs.getString("cpf"));
                    
                    fones = colFone.obterFones(conn, id, "Tecnico");
                    emails = colEmail.obterEmails(conn, id, "Tecnico");
                    
                    tecnico.setEnderecoEspecifico(new EnderecoEspecifico());
                    
                    tecnico.setNome(rs.getString("nome_tecnico"));
                    
                    tecnico.getEnderecoEspecifico().setComplemento(rs.getString("comp_endereco"));
                    tecnico.getEnderecoEspecifico().setNroEndereco(rs.getInt("nro_endereco"));
                    
                    endereco = colEndereco.recuperarEnderecoPorID(conn, rs.getInt("id_endereco"));
                    
                    tecnico.getEnderecoEspecifico().setEndereco(endereco);
                    
                    tecnico.setFones(fones);
                    tecnico.setEmails(emails);
                    
                    return tecnico;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tecnico no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Tecnico não existe na base de dados");
    }
}

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
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class EmailDAO {

    public int cadastrarEmail(Connection conn, Email email, int id, String nomeTabela) throws SQLException, PessoaException {

        String SQL = "INSERT INTO \"Email" + nomeTabela + "\" (endereco,id_" + nomeTabela + ") "
                + "VALUES (?,?) RETURNING id_email_" + nomeTabela;

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_email_" + nomeTabela);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar email no banco: " + e.getMessage(), e);
        }

        throw new PessoaException("Erro ao retornar id cadastrado");
    }

    public List<Email> obterEmails(Connection conn, int id, String nomeTabela) throws SQLException, PessoaException {

        String SQL = "SELECT endereco FROM \"Email" + nomeTabela + "\" WHERE id_" + nomeTabela + " = ?";
        List<Email> emails = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Email email = new Email();

                    email.setEndereco(rs.getString("endereco"));

                    emails.add(email);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar emails: " + e.getMessage(), e);
        }

        if (emails.isEmpty()) {
            throw new PessoaException("Nenhum email encontrado para o id: " + id);
        }

        return emails;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.Fone;
import unioeste.geral.pessoa.col.ColEmail;
import unioeste.geral.pessoa.col.ColFone;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class UCPessoaGeralServicos {

    public UCPessoaGeralServicos() {
    }

    public List<Fone> obterFones(int id, String nomeTabela) throws SQLException, PessoaException {
        ColFone colFone = new ColFone();

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                List<Fone> fones = colFone.obterFones(conn, id, nomeTabela);
                conn.commit();

                return fones;
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
            }
        }
    }

    public List<Email> obterEmails(int id, String nomeTabela) throws SQLException, PessoaException {
        ColEmail colEmail = new ColEmail();

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                List<Email> emails = colEmail.obterEmails(conn, id, nomeTabela);
                conn.commit();

                return emails;
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
            }
        }
    }

}

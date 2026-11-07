/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.dao.EmailDAO;
import unioeste.geral.pessoa.exception.EmailException;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class ColEmail {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static boolean validarEmail(Email email) throws PessoaException {

        if (email.getEndereco() == null || email.getEndereco().trim().isEmpty()) {
            throw new EmailException(EmailException.CodigoErro.ENDERECO_EMAIL_INVALIDO, email.getEndereco());
        }

        email.setEndereco(email.getEndereco().trim());

        return true;
    }

    public static boolean validarFormato(Email email) throws PessoaException {

        if (!EMAIL_PATTERN.matcher(email.getEndereco()).matches()) {
            throw new EmailException(EmailException.CodigoErro.ENDERECO_EMAIL_MAL_FORMATADO, email.getEndereco());
        }

        return true;
    }

    public int cadastrarEmail(Connection conn, Email email, int id, String nomeTabela) throws SQLException, PessoaException {

        EmailDAO emailDAO = new EmailDAO();

        validarEmail(email);
        validarFormato(email);

        try {
            return emailDAO.cadastrarEmail(conn, email, id, nomeTabela);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (PessoaException e) {
            throw new PessoaException("Ocorreu um erro durante o cadastro: " + e.getMessage());
        }
    }

    public List<Email> obterEmails(Connection conn, int id, String nomeTabela) throws SQLException, PessoaException {
        EmailDAO emailDAO = new EmailDAO();

        try {
            return emailDAO.obterEmails(conn, id, nomeTabela);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (PessoaException e) {
            throw new PessoaException("Ocorreu um erro durante a busca: " + e.getMessage());
        }
    }
}

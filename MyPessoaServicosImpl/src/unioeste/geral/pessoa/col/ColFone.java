/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import unioeste.geral.pessoa.bo.Fone;
import unioeste.geral.pessoa.dao.FoneDAO;
import unioeste.geral.pessoa.exception.FoneException;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class ColFone {

    private static final Pattern FONE_PATTERN = Pattern.compile("^\\d{5}-\\d{4}$");
    private static final Pattern FONE_PATTERN_ALT = Pattern.compile("^\\d{4}-\\d{4}$");

    public static boolean validarFormato(Fone fone) throws PessoaException {

        if (!FONE_PATTERN.matcher(fone.getNro()).matches()) {
            if (!FONE_PATTERN_ALT.matcher(fone.getNro()).matches()) {
                throw new FoneException(FoneException.CodigoErro.FONE_MAL_FORMATADO, fone.getNro());
            }
        }

        return true;
    }

    public static boolean validarFone(Fone fone) throws PessoaException {

        if (fone.getNro() == null || fone.getNro().trim().isEmpty()) {
            throw new FoneException(FoneException.CodigoErro.FONE_INVALIDO, fone.getNro());
        }

        fone.setNro(fone.getNro().trim());

        return true;
    }
    
    public int cadastrarFones(Connection conn, Fone fone, int id, String nomeTabela) throws PessoaException, SQLException {
        
        FoneDAO foneDAO = new FoneDAO();
        
        validarFone(fone);
        validarFormato(fone);
        
        try {
            return foneDAO.cadastrarFone(conn, fone, id, nomeTabela);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (PessoaException e) {
            throw new PessoaException("Ocorreu um erro durante o cadastro: " + e.getMessage());
        }
    }

    public List<Fone> obterFones(Connection conn, int id, String nomeTabela) throws SQLException, PessoaException {

        FoneDAO foneDAO = new FoneDAO();

        try {
            return foneDAO.obterFones(conn, id, nomeTabela);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } catch (PessoaException e) {
            throw new PessoaException("Ocorreu um erro durante a busca: " + e.getMessage());
        }
    }

}

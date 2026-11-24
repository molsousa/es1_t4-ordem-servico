/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.col;

import unioeste.geral.pessoa.bo.Pessoa;
import unioeste.geral.pessoa.exception.NomeException;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class ColPessoa {

    public static boolean validarNome(Pessoa pessoa) throws PessoaException {

        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new NomeException(NomeException.CodigoErro.NOME_INVALIDO, pessoa.getNome());
        }

        pessoa.setNome(pessoa.getNome().trim());

        return true;
    }

    public static boolean validarNomeSocial(Pessoa pessoa) throws PessoaException {

        if (pessoa.getNomeSocial() == null || pessoa.getNomeSocial().trim().isEmpty()) {
            throw new NomeException(NomeException.CodigoErro.NOME_SOCIAL_INVALIDO, pessoa.getNomeSocial());
        }

        pessoa.setNomeSocial(pessoa.getNomeSocial().trim());

        return true;
    }
}

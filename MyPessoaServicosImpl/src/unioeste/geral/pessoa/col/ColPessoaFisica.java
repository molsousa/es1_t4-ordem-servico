/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.col;

import java.util.regex.Pattern;
import unioeste.geral.pessoa.bo.PessoaFisica;
import unioeste.geral.pessoa.exception.CPFException;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class ColPessoaFisica {

    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    public static boolean validarCPF(PessoaFisica pessoaFisica) throws PessoaException {

        if (pessoaFisica.getCpf() == null || pessoaFisica.getCpf().trim().isEmpty()) {
            throw new CPFException(CPFException.CodigoErro.CPF_VAZIO, "insira um CPF válido.");
        }

        pessoaFisica.setCpf(pessoaFisica.getCpf().trim());

        if (!CPF_PATTERN.matcher(pessoaFisica.getCpf()).matches()) {
            throw new CPFException(CPFException.CodigoErro.CPF_MAL_FORMATADO, pessoaFisica.getCpf());
        }

        return true;
    }

    public static String formatarCPF(String cpf) throws PessoaException {

        if (cpf == null) {
            return null;
        }

        String num = cpf.replaceAll("\\D", "");

        if (num.length() != 11) {
            throw new CPFException(CPFException.CodigoErro.CPF_INVALIDO, cpf);
        }

        return num.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}

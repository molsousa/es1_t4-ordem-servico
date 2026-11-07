/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.exception;

import java.util.Objects;

/**
 *
 * @author molsousa
 */
public class DDIException extends PessoaException {

    private static final long serialVersionUID = 1;

    private final CodigoErro erro;

    public DDIException(CodigoErro erro, String nro) {
        super(montarMensagem(erro, nro));
        this.erro = Objects.requireNonNull(erro, "erro");
    }

    private static String montarMensagem(CodigoErro erro, String nro) {
        return erro.getMessage() + ": " + nro;
    }

    public CodigoErro getErro() {
        return erro;
    }

    public enum CodigoErro {
        DDI_INVALIDO("DDI Inválido");

        private String msg;

        CodigoErro(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return this.msg;
        }
    }
}

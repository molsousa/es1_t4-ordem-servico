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
public class EmailException extends PessoaException {
    
    private static final long serialVersionUID = 1;
    
    private final CodigoErro erro;
    
    public EmailException(CodigoErro erro, String endereco) {
        super(montarMensagem(erro, endereco));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, String endereco) {
        return erro.getMessage() + ": " + endereco;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        ENDERECO_EMAIL_MAL_FORMATADO("Endereço de email mal formatado"),
        ENDERECO_EMAIL_INVALIDO("Endereço de email inválido");
        
        private final String msg;
        
        CodigoErro(String msg) {
            this.msg = msg;
        }
        
        public String getMessage() {
            return this.msg;
        }
    }
}

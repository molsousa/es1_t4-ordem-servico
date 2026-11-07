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
public class NomeException extends PessoaException {
    
    private static final long serialVersionUID = 1;
    
    private final CodigoErro erro;
    
    public NomeException(CodigoErro erro, String nome) {
        super(montarMensagem(erro, nome));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, String nome) {
        return erro.getMessage() + ": " + nome;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        NOME_SOCIAL_INVALIDO("Nome social inválido"),
        NOME_INVALIDO("Nome inválido");
        
        private String msg;
        
        CodigoErro(String msg) {
            this.msg = msg;
        }
        
        public String getMessage() {
            return this.msg;
        }
    }
}

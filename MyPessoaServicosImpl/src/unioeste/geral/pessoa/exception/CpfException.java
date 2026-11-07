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
public class CpfException extends PessoaException {
    
    private static final long serialVersionUID = 1;
    private final CodigoErro erro;
    
    
    public CpfException(CodigoErro erro, String cpf) {
        super(montarMensagem(erro, cpf));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, String cpf) {
        return erro.getMessage() + ": " + cpf;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        CPF_INVALIDO("CPF inválido"),
        CPF_VAZIO("Campo CPF vazio"),
        CPF_MAL_FORMATADO("CPF mal formatado");
        
        private final String msg;
        
        CodigoErro(String msg) {
            this.msg = msg;
        }
        
        public String getMessage() {
            return msg;
        }
    }
}

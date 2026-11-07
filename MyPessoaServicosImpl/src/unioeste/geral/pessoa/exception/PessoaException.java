/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.exception;

/**
 *
 * @author molsousa
 */
public class PessoaException extends Exception {
    
    public PessoaException(String msg) {
        super(msg);
    }
    
    public PessoaException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

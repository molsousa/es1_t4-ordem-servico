/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.exception;

/**
 *
 * @author molsousa
 */
public class OrdemServicoException extends Exception {

    public OrdemServicoException(String msg) {
        super(msg);
    }
    
    public OrdemServicoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

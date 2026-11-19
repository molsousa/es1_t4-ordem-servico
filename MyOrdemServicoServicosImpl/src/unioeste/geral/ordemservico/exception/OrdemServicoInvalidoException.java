/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.exception;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author molsousa
 */
public class OrdemServicoInvalidoException extends OrdemServicoException {
    
    public static final long serialVersionUID = 1;
    
    private final CodigoErro erro;
    
    public OrdemServicoInvalidoException(CodigoErro erro, String descricao) {
        super(montarMensagem(erro, descricao));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, String descricao) {
        return erro.getMessage() + ": " + descricao;
    }
    
    public OrdemServicoInvalidoException(CodigoErro erro, int nroOS) {
        super(montarMensagem(erro, nroOS));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, int nroOS) {
        return erro.getMessage() + ": " + nroOS;
    }
    
    public OrdemServicoInvalidoException(CodigoErro erro, Date dtEmissao) {
        super(montarMensagem(erro, dtEmissao));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, Date dtEmissao) {
        return erro.getMessage() + ": " + dtEmissao.toString();
    }
    
    public CodigoErro getErro() {
        return this.erro;
    }
    
    public enum CodigoErro {
        DESCRICAO_INVALIDA("Descrição inválida"),
        NRO_OS_INVALIDA("Número da ordem de serviço inválido"),
        DATA_INVALIDA("Data inválida");
        
        private final String msg;
        
        CodigoErro(String msg) {
            this.msg = msg;
        }
        
        public String getMessage() {
            return this.msg;
        }
    }
}

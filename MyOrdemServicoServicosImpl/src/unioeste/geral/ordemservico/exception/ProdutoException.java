/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.exception;

import java.util.Objects;

/**
 *
 * @author molsousa
 */
public class ProdutoException extends OrdemServicoException {
    
    private static final long serialVersionUID = 1;
    
    private final CodigoErro erro;
    
    public ProdutoException(CodigoErro erro, String nome) {
        super(montarMensagem(erro, nome));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    public ProdutoException(CodigoErro erro, double valor) {
        super(montarMensagem(erro, valor));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    public ProdutoException(CodigoErro erro, int id) {
        super(montarMensagem(erro, id));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, String nome) {
        return erro.getMessage() + ": " + nome;
    }
    
    private static String montarMensagem(CodigoErro erro, double valor) {
        return erro.getMessage() + ": " + valor;
    }
    
    private static String montarMensagem(CodigoErro erro, int id) {
        return erro.getMessage() + ": " + id;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        NOME_INVALIDO("Nome inválido"),
        VALOR_INVALIDO("Valor inválido"),
        ID_INVALIDO("ID inválido");

        private final String msg;

        CodigoErro(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return this.msg;
        }
    }
}

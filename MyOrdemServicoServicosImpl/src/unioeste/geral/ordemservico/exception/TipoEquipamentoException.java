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
public class TipoEquipamentoException extends OrdemServicoException {
    
    private static final long serialVersionUID = 1;

    private final CodigoErro erro;
    
    public TipoEquipamentoException(CodigoErro erro, String nome) {
        super(montarMensagem(erro, nome));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    public TipoEquipamentoException(CodigoErro erro, int codigo) {
        super(montarMensagem(erro, codigo));
        this.erro = Objects.requireNonNull(erro, "erro");
    }
    
    private static String montarMensagem(CodigoErro erro, String nome) {
        return erro.getMessage() + ": " + nome;
    }
    
    private static String montarMensagem(CodigoErro erro, int codigo) {
        return erro.getMessage() + ": " + codigo;
    }
    
    public CodigoErro getErro() {
        return erro;
    }
    
    public enum CodigoErro {
        NOME_INVALIDO("Nome inválido"),
        CODIGO_INVALIDO("Código inválido");

        private final String msg;

        CodigoErro(String msg) {
            this.msg = msg;
        }

        public String getMessage() {
            return this.msg;
        }
    }
}

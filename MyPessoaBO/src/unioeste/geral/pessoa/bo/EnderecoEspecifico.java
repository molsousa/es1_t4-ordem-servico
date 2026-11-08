/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.bo;

import java.io.Serializable;
import unioeste.geral.endereco.bo.Endereco;

/**
 *
 * @author molsousa
 */
public class EnderecoEspecifico implements Serializable {
    
    public final static long serialVersionUID = 1;

    private long nroEndereco;
    private String complemento;
    private Endereco endereco;

    public EnderecoEspecifico() {
    }

    public long getNroEndereco() {
        return nroEndereco;
    }

    public void setNroEndereco(long nroEndereco) {
        this.nroEndereco = nroEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}

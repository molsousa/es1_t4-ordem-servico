/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.bo;

import java.io.Serializable;

/**
 *
 * @author molsousa
 */
public class StatusOS implements Serializable {

    public final static long serialVersionUID = 1;

    private String descricao;

    public StatusOS() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

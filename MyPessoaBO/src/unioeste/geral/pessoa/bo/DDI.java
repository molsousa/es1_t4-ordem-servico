/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.bo;

import java.io.Serializable;

/**
 *
 * @author molsousa
 */
public class DDI implements Serializable {

    public final static long serialVersionUID = 1;

    private String nro;

    public DDI() {
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

}

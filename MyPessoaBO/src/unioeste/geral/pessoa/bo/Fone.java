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
public class Fone implements Serializable {

    public final static long serialVersionUID = 1;

    private String nro;
    private DDD ddd;
    private DDI ddi;

    public Fone() {
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public DDD getDdd() {
        return ddd;
    }

    public void setDdd(DDD ddd) {
        this.ddd = ddd;
    }

    public DDI getDdi() {
        return ddi;
    }

    public void setDdi(DDI ddi) {
        this.ddi = ddi;
    }

}

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
public class OSTipoServico implements Serializable {

    public final static long serialVersionUID = 1;

    private TipoServico tipoServico;

    public OSTipoServico() {
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

}

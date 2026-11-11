/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.bo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author molsousa
 */
public class OrdemServico implements Serializable {

    public final static long serialVersionUID = 1;

    private int nro;
    private Date dtEmissao;
    private String descProblema;
    private double valorTotalOS;
    private Tecnico tecnico;
    private Cliente cliente;
    private List<OSTipoServico> osTipoServicos;
    private List<OSProduto> osProdutos;
    private TipoEquipamento tipoEquipamento;
    private StatusOS statusOS;

    public OrdemServico() {
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }

    public double getValorTotalOS() {
        return valorTotalOS;
    }

    public void setValorTotalOS(double valorTotalOS) {
        this.valorTotalOS = valorTotalOS;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OSTipoServico> getOsTipoServicos() {
        return osTipoServicos;
    }

    public void setOsTipoServicos(List<OSTipoServico> osTipoServicos) {
        this.osTipoServicos = osTipoServicos;
    }

    public List<OSProduto> getOsProdutos() {
        return osProdutos;
    }

    public void setOsProdutos(List<OSProduto> osProdutos) {
        this.osProdutos = osProdutos;
    }

    public TipoEquipamento getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public StatusOS getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(StatusOS statusOS) {
        this.statusOS = statusOS;
    }

}

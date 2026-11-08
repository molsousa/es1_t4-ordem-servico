/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.bo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author molsousa
 */
public class Pessoa implements Serializable {

    public final static long serialVersionUID = 1;

    private String nome;
    private String nomeSocial;
    private List<Fone> fones;
    private List<Email> emails;
    private EnderecoEspecifico enderecoEspecifico;

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public List<Fone> getFones() {
        return fones;
    }

    public void setFones(List<Fone> fones) {
        this.fones = fones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public EnderecoEspecifico getEnderecoEspecifico() {
        return enderecoEspecifico;
    }

    public void setEnderecoEspecifico(EnderecoEspecifico enderecoEspecifico) {
        this.enderecoEspecifico = enderecoEspecifico;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.endereco.bo.EnderecoEspecifico;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.dao.ClienteDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.Fone;
import unioeste.geral.pessoa.bo.PessoaFisica;
import unioeste.geral.pessoa.col.ColEmail;
import unioeste.geral.pessoa.col.ColFone;
import unioeste.geral.pessoa.col.ColPessoaFisica;
import unioeste.geral.pessoa.exception.CpfException;

/**
 *
 * @author molsousa
 */
public class ColCliente {

    public int cadastrarCliente(Connection conn, String nome, String cpf,
            EnderecoEspecifico enderecoEspecifico, List<Fone> fones, List<Email> emails) throws Exception {

        ClienteDAO clienteDAO = new ClienteDAO();
        ColEmail colEmail = new ColEmail();
        ColFone colFone = new ColFone();

        try {
            int idCliente = clienteDAO.cadastrarCliente(conn, nome, cpf, enderecoEspecifico);

            for (Email email : emails) {
                colEmail.cadastrarEmail(conn, email, idCliente, "Cliente");
            }
            
            for(Fone fone : fones) {
                colFone.cadastrarFones(conn, fone, idCliente, "Cliente");
            }
            
            return idCliente;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public Cliente recuperarClientePorCPF(Connection conn, String cpf) throws Exception {

        ClienteDAO clienteDAO = new ClienteDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setCpf(cpf);

        try {
            ColPessoaFisica.validarCPF(pessoaFisica);
        } catch (CpfException e) {
            if (e.getErro() == CpfException.CodigoErro.CPF_MAL_FORMATADO) {
                cpf = pessoaFisica.getCpf();
                cpf = ColPessoaFisica.formatarCPF(cpf);
            }
        }

        try {
            return clienteDAO.buscarClientePorCPF(conn, cpf);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public Cliente recuperarClientePorID(Connection conn, int id) throws Exception {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        try {
            return clienteDAO.buscarClientePorID(conn, id);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

}

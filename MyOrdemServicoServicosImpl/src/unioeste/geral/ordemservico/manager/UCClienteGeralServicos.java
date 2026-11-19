/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.col.ColCliente;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.EnderecoEspecifico;
import unioeste.geral.pessoa.bo.Fone;

/**
 *
 * @author molsousa
 */
public class UCClienteGeralServicos {

    public Cliente cadastrarCliente(String nome, String cpf, EnderecoEspecifico enderecoEspecifico,
            List<Fone> fones, List<Email> emails) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColCliente colCliente = new ColCliente();
                int idCliente = colCliente.cadastrarCliente(conn, nome, cpf, enderecoEspecifico, fones, emails);

                conn.commit();
                return colCliente.recuperarClientePorID(conn, idCliente);
            } catch (Exception  e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }

    public Cliente obterClientePorCPF(String cpf) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColCliente colCliente = new ColCliente();
                Cliente cliente = colCliente.recuperarClientePorCPF(conn, cpf);

                conn.commit();
                return cliente;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }

    public List<Cliente> obterClientePorPadrao(String padrao) throws Exception {

        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColCliente colCliente = new ColCliente();
                List<Cliente> clientes = colCliente.recuperarClientePorPadrao(conn, padrao);
                conn.commit();
                return clientes;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }
    
    /*
    public boolean atualizarCliente(Cliente cliente) throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                
            }
        }
    }*/
}

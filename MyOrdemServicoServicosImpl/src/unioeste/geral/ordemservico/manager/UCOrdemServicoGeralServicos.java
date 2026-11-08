/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.endereco.bo.EnderecoEspecifico;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.col.ColCliente;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.Fone;

/**
 *
 * @author molsousa
 */
public class UCOrdemServicoGeralServicos {
    
    public Cliente cadastrarCliente(String nome, String cpf, EnderecoEspecifico enderecoEspecifico, 
            List<Fone> fones, List<Email> emails) throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColCliente colCliente = new ColCliente();
                int idCliente = colCliente.cadastrarCliente(conn, nome, cpf, enderecoEspecifico, fones, emails);
                
                conn.commit();
                return colCliente.recuperarClientePorID(conn, idCliente);
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
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
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
            }
        } 
    }
}

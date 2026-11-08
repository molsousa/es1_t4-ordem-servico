/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.col.ColCliente;

/**
 *
 * @author molsousa
 */
public class UCOrdemServicoGeralServicos {
    
    public Cliente obterCliente(String cpf) throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            try {
                ColCliente colCliente = new ColCliente();
                Cliente cliente = colCliente.obterCliente(conn, cpf);
                
                conn.commit();
                return cliente;
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
            }
        } 
    }
}

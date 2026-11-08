/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.dao.ClienteDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.pessoa.bo.PessoaFisica;
import unioeste.geral.pessoa.col.ColPessoaFisica;

/**
 *
 * @author molsousa
 */
public class ColCliente {
    
    public Cliente obterCliente(Connection conn, String cpf) throws Exception {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();
        /*
        pessoaFisica.setCpf(cpf);
        
        ColPessoaFisica.validarCPF(pessoaFisica);
        
        cpf = pessoaFisica.getCpf();
        ColPessoaFisica.formatarCPF(cpf);
        */
        try {
            return clienteDAO.buscarCliente(conn, cpf);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
}

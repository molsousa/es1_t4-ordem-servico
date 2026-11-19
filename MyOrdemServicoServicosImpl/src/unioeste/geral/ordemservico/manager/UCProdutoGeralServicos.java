/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.bo.Produto;
import unioeste.geral.ordemservico.col.ColProduto;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class UCProdutoGeralServicos {
    
    public int cadastrarProduto(String nome, double valor) throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            try {
                ColProduto colProduto = new ColProduto();
                int codigoProduto = colProduto.cadastrarProduto(conn, nome, valor);
                conn.commit();
                return codigoProduto;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }
    
    public List<Produto> listarProdutos() throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            
            try {
                ColProduto colProduto = new ColProduto();
                List<Produto> produtos = colProduto.recuperarProdutos(conn);
                conn.commit();
                return produtos;
            } catch (Exception e) {
                conn.rollback();
                
                if(e instanceof SQLException) {
                    throw new OrdemServicoException(e.getMessage(), e);
                }
                
                throw e;
            }
        }
    }
}

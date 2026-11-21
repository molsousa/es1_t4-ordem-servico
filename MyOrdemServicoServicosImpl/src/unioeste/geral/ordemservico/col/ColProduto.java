/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.dao.ProdutoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.exception.ProdutoException;
import unioeste.geral.ordemservico.bo.Produto;

/**
 *
 * @author molsousa
 */
public class ColProduto {

    public int cadastrarProduto(Connection conn, String nome, double valor) throws Exception {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        if (nome == null || nome.trim().isEmpty()) {
            throw new ProdutoException(ProdutoException.CodigoErro.NOME_INVALIDO, nome);
        }

        nome = nome.trim();

        if (valor <= 0.0f) {
            throw new ProdutoException(ProdutoException.CodigoErro.VALOR_INVALIDO, valor);
        }

        try {
            return produtoDAO.cadastrarProduto(conn, nome, valor);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

    public List<Produto> recuperarProdutos(Connection conn) throws Exception {

        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            return produtoDAO.listarProdutos(conn);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

    public Produto recuperarProduto(Connection conn, int idProduto) throws Exception {
        
        if (idProduto <= 0) {
            throw new ProdutoException(ProdutoException.CodigoErro.ID_INVALIDO, idProduto);
        }
        
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            return produtoDAO.obterProduto(conn, idProduto);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
}

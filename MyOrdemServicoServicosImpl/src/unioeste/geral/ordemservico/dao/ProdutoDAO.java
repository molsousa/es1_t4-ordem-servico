/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unioeste.geral.ordemservico.bo.Produto;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class ProdutoDAO {

    public int cadastrarProduto(Connection conn, String nome, double valor) throws Exception {
        String SQL = "INSERT INTO \"Produto\" (nome_produto, valor_referencia) VALUES(?,?) RETURNING id_produto";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, valor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_produto");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar produtos no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Não foi possível cadastrar produto no banco");
    }

    public List<Produto> listarProdutos(Connection conn) throws Exception {
        String SQL = "SELECT * FROM \"Produto\"";

        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto();

                    produto.setCodigo(rs.getInt("id_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setValor(rs.getDouble("valor_referencia"));

                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar produtos no banco: " + e.getMessage(), e);
        }

        if (produtos.isEmpty()) {
            throw new OrdemServicoException("Não há produtos na base de dados");
        }

        return produtos;
    }

    public Produto obterProduto(Connection conn, int idProduto) throws Exception {
        String SQL = "SELECT * \"Produto\" WHERE id_produto = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    
                    p.setCodigo(idProduto);
                    p.setNome(rs.getString("nome_produto"));
                    p.setValor(rs.getDouble("valor_referencia"));
                    
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar produto no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não foi possível retornar produto do banco");
    }
}

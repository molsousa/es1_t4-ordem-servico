/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.bo.OSProduto;
import unioeste.geral.ordemservico.dao.OSProdutoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class ColOSProduto {
    
    public double cadastrarOSProduto(Connection conn, int nroOS, int idProduto, double valorCobrado, int qtd) throws Exception {
        
        try {
            OSProdutoDAO ospdao = new OSProdutoDAO();
            
            if(ospdao.cadastrarOSProduto(conn, nroOS, idProduto, valorCobrado, qtd)) {
                return (valorCobrado * qtd);
            } else {
                throw new OrdemServicoException("Ocorreu um erro no cadastro do produto na ordem de serviço");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public List<OSProduto> recuperarOSProdutos(Connection conn, int nroOS) throws Exception {

        try {
            OSProdutoDAO ospdao = new OSProdutoDAO();
            return ospdao.obterOSProdutos(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

}

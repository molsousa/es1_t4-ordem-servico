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
import unioeste.geral.ordemservico.bo.OSProduto;
import unioeste.geral.ordemservico.bo.Produto;
import unioeste.geral.ordemservico.col.ColProduto;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class OSProdutoDAO {
    
    public List<OSProduto> obterOSProdutos(Connection conn, int nroOS) throws Exception {
        String SQL = "SELECT * FROM \"OSProduto\" WHERE nro_os = ?";
        List<OSProduto> osProdutos = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, nroOS);
            
            try (ResultSet rs = stmt.executeQuery()) {
                
                while(rs.next()) {
                    OSProduto op = new OSProduto();
                    
                    int idProduto = rs.getInt("id_produto");
                    op.setValor(rs.getDouble("valor_produto"));
                    op.setQtd(rs.getInt("qtd_produto"));
                    
                    ColProduto colProduto = new ColProduto();
                    
                    Produto p;
                    p = colProduto.recuperarProduto(conn, idProduto);
                    op.setProduto(p);
                    
                    osProdutos.add(op);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar produto no banco: " + e.getMessage(), e);
        }
        
        if(osProdutos.isEmpty()) {
            throw new OrdemServicoException("Não há produtos para essa ordem de serviço");
        }
        
        return osProdutos;
    }
    
    
}

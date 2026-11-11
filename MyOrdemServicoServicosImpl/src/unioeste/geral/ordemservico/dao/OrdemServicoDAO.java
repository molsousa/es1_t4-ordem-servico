/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.col.ColCliente;
import unioeste.geral.ordemservico.col.ColOSProduto;
import unioeste.geral.ordemservico.col.ColOSTipoServico;
import unioeste.geral.ordemservico.col.ColStatusOS;
import unioeste.geral.ordemservico.col.ColTecnico;
import unioeste.geral.ordemservico.col.ColTipoEquipamento;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class OrdemServicoDAO {
    
    public int cadastrarOrdemServico(Connection conn, int idCliente, int idTipoEquipamento, int idStatusOS, OrdemServico os) throws Exception {
        String SQL = "INSERT INTO \"OrdemServico\" (dt_emissao, desc_problema, id_cliente, id_tipo_equipamento, id_status_os) "
                + "VALUES (?,?,?,?,?) RETURNING nro_os";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            
            stmt.setDate(1, os.getDtEmissao());
            stmt.setString(2, os.getDescProblema());
            stmt.setInt(3, idCliente);
            stmt.setInt(4, idTipoEquipamento);
            stmt.setInt(5, idStatusOS);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("nro_os");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar ordem de serviço no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não foi possível cadastrar ordem de serviço no banco");
    }
    
    public OrdemServico obterOrdemServico(Connection conn, int nroOS) throws Exception {
        String SQL = "SELECT * FROM \"OrdemServico\" WHERE nro_os = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, nroOS);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    OrdemServico os = new OrdemServico();
                    
                    os.setNro(nroOS);
                    os.setDescProblema(rs.getString("desc_problema"));
                    os.setValorTotalOS(rs.getDouble("valor_total_os"));
                    os.setDtEmissao(rs.getDate("dt_emissao"));
                    
                    ColOSTipoServico colOSTipoServico = new ColOSTipoServico();
                    try {                        
                        os.setOsTipoServicos(colOSTipoServico.recuperarOSTipoServicos(conn, nroOS));
                    } catch (OrdemServicoException e) {
                        os.setOsTipoServicos(null);
                    }
                    
                    ColOSProduto colOSProduto = new ColOSProduto();
                    try {                        
                        os.setOsProdutos(colOSProduto.recuperarOSProdutos(conn, nroOS));
                    } catch (OrdemServicoException e) {
                        os.setOsProdutos(null);
                    }
                    
                    ColCliente colCliente = new ColCliente();
                    os.setCliente(colCliente.recuperarClientePorID(conn, rs.getInt("id_cliente")));
                    
                    ColTipoEquipamento colTipoEquipamento = new ColTipoEquipamento();
                    os.setTipoEquipamento(colTipoEquipamento.recuperarTipoEquipamento(conn, rs.getInt("id_tipo_equipamento")));
                    
                    ColTecnico colTecnico = new ColTecnico();
                    try {                        
                        os.setTecnico(colTecnico.recuperarTecnicoPorID(conn, rs.getInt("id_tecnico")));
                    } catch (OrdemServicoException e) {
                        System.out.println("Testando saída");
                        os.setTecnico(null);
                    }
                    
                    ColStatusOS colStatusOS = new ColStatusOS();
                    os.setStatusOS(colStatusOS.recuperarStatusOS(conn, rs.getInt("id_status_os")));
                    
                    return os;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter ordem de serviço no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não foi possível obter ordem de serviço do banco");
    }
}

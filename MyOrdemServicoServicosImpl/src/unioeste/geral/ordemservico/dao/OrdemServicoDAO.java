/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.bo.OSProduto;
import unioeste.geral.ordemservico.bo.OSTipoServico;
import unioeste.geral.ordemservico.col.ColCliente;
import unioeste.geral.ordemservico.col.ColStatusOS;
import unioeste.geral.ordemservico.col.ColTecnico;
import unioeste.geral.ordemservico.col.ColTipoEquipamento;
import unioeste.geral.ordemservico.exception.OrdemServicoException;

/**
 *
 * @author molsousa
 */
public class OrdemServicoDAO {

    public boolean cadastrarOrdemServico(Connection conn, int idCliente, int idTipoEquipamento, int idStatusOS, OrdemServico os) throws Exception {
        String SQL = "INSERT INTO \"OrdemServico\" (dt_emissao, desc_problema, id_cliente, id_tipo_equipamento, id_status_os) "
                + "VALUES (?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setDate(1, os.getDtEmissao());
            stmt.setString(2, os.getDescProblema());
            stmt.setInt(3, idCliente);
            stmt.setInt(4, idTipoEquipamento);
            stmt.setInt(5, idStatusOS);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar ordem de serviço no banco: " + e.getMessage(), e);
        }
    }

    public OrdemServico obterOrdemServico(Connection conn, int nroOS, List<OSProduto> produtos, List<OSTipoServico> tipoServicos) throws Exception {
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

                    ColCliente colCliente = new ColCliente();
                    os.setCliente(colCliente.recuperarClientePorID(conn, rs.getInt("id_cliente")));

                    ColTipoEquipamento colTipoEquipamento = new ColTipoEquipamento();
                    os.setTipoEquipamento(colTipoEquipamento.recuperarTipoEquipamento(conn, rs.getInt("id_tipo_equipamento")));

                    ColTecnico colTecnico = new ColTecnico();
                    try {
                        os.setTecnico(colTecnico.recuperarTecnicoPorID(conn, rs.getInt("id_tecnico")));
                    } catch (OrdemServicoException e) {
                        os.setTecnico(null);
                    }

                    ColStatusOS colStatusOS = new ColStatusOS();
                    os.setStatusOS(colStatusOS.recuperarStatusOS(conn, rs.getInt("id_status_os")));

                    os.setOsProdutos(produtos);
                    os.setOsTipoServicos(tipoServicos);

                    return os;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter ordem de serviço no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Não foi possível obter ordem de serviço do banco");
    }

    public boolean atualizarOSTecnico(Connection conn, int idTecnico, int nroOS) throws Exception {
        String SQL = "UPDATE \"OrdemServico\" SET id_tecnico = ? WHERE nro_os = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idTecnico);
            stmt.setInt(2, nroOS);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar técnico a ordem de serviço: " + e.getMessage(), e);
        }
    }

    public boolean atualizarValorTotalOS(Connection conn, int nroOS, double valorTotalOS) throws Exception {
        String SQL = "UPDATE \"OrdemServico\" SET valor_total_os = ? WHERE nro_os = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setDouble(1, valorTotalOS);
            stmt.setInt(2, nroOS);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atribuir valor total a ordem de serviço: " + e.getMessage(), e);
        }
    }

    public boolean atualizarStatusOS(Connection conn, int nroOS, int idStatusOS) throws Exception {
        String SQL = "UPDATE \"OrdemServico\" SET id_status_os = ? WHERE nro_os = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idStatusOS);
            stmt.setInt(2, nroOS);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar status da ordem de serviço: " + e.getMessage(), e);
        }
    }

    public boolean removerOrdemServico(Connection conn, int nroOS) throws Exception {
        String SQL = "DELETE FROM \"OrdemServico\" WHERE nro_os = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, nroOS);

            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }
}

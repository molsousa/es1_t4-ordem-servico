/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.dao.TipoServicoDAO;
import unioeste.geral.ordemservico.bo.TipoServico;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.exception.TipoServicoException;

/**
 *
 * @author molsousa
 */
public class ColTipoServico {

    public int cadastrarTipoServico(Connection conn, String nomeTipoServico, double valor) throws Exception {

        nomeTipoServico = nomeTipoServico.trim();

        if (nomeTipoServico == null || nomeTipoServico.isEmpty()) {
            throw new TipoServicoException(TipoServicoException.CodigoErro.NOME_INVALIDO, nomeTipoServico);
        }

        if (valor <= 0.0f) {
            throw new TipoServicoException(TipoServicoException.CodigoErro.VALOR_INVALIDO, valor);
        }

        try {
            TipoServicoDAO tipoServicoDAO = new TipoServicoDAO();

            return tipoServicoDAO.cadastrarTipoServico(conn, nomeTipoServico, valor);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

    public List<TipoServico> recuperarTipoServicos(Connection conn) throws Exception {

        try {
            TipoServicoDAO tipoServicoDAO = new TipoServicoDAO();
            return tipoServicoDAO.listarTipoServicos(conn);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
}

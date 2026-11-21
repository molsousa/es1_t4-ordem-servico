/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.bo.OSTipoServico;
import unioeste.geral.ordemservico.dao.OSTipoServicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.exception.OrdemServicoInvalidoException;
import unioeste.geral.ordemservico.exception.TipoServicoException;

/**
 *
 * @author molsousa
 */
public class ColOSTipoServico {

    public boolean cadastrarOSTipoServico(Connection conn, int nroOS, int idTipoServico, double valorCobrado) throws Exception {

        if (valorCobrado <= 0.0) {
            throw new TipoServicoException(TipoServicoException.CodigoErro.VALOR_INVALIDO, valorCobrado);
        }

        if (idTipoServico <= 0) {
            throw new TipoServicoException(TipoServicoException.CodigoErro.ID_INVALIDO, idTipoServico);
        }

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        try {
            OSTipoServicoDAO ostsdao = new OSTipoServicoDAO();
            return ostsdao.cadastrarOSTipoServico(conn, nroOS, idTipoServico, valorCobrado);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public List<OSTipoServico> recuperarOSTipoServicos(Connection conn, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        try {
            OSTipoServicoDAO ostsdao = new OSTipoServicoDAO();
            return ostsdao.obterOSTipoServicos(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }

    public boolean removerOSTipoServico(Connection conn, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        OSTipoServicoDAO ostsdao = new OSTipoServicoDAO();

        try {
            return ostsdao.removerOSTipoServico(conn, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    public boolean removerTipoServicoOS(Connection conn, int idTipoServico, int nroOS) throws Exception {

        if (nroOS <= 0) {
            throw new OrdemServicoInvalidoException(OrdemServicoInvalidoException.CodigoErro.NRO_OS_INVALIDA, nroOS);
        }

        if (idTipoServico <= 0) {
            throw new OrdemServicoException("Id do tipo de serviço inválido: " + idTipoServico);
        }

        OSTipoServicoDAO ostsdao = new OSTipoServicoDAO();

        try {
            return ostsdao.removerTipoServicoOS(conn, idTipoServico, nroOS);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }
}

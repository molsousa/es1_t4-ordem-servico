/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.dao.TipoEquipamentoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.ordemservico.exception.TipoEquipamentoException;
import unioeste.geral.ordemservico.bo.TipoEquipamento;

/**
 *
 * @author molsousa
 */
public class ColTipoEquipamento {
    
    public int cadastrarTipoEquipamento(Connection conn, String nomeEquipamento) throws Exception {
        TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
        nomeEquipamento = nomeEquipamento.trim();
        
        if(nomeEquipamento.isEmpty()) {
            throw new TipoEquipamentoException(TipoEquipamentoException.CodigoErro.NOME_INVALIDO, nomeEquipamento);
        }
        
        try {
            return tipoEquipamentoDAO.cadastrarTipoEquipamento(conn, nomeEquipamento);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public List<TipoEquipamento> recuperarTipoEquipamentos(Connection conn) throws Exception {
        
        TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
        
        try {
            return tipoEquipamentoDAO.listarTipoEquipamentos(conn);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public TipoEquipamento recuperarTipoEquipamento(Connection conn, int codTipoEquipamento) throws Exception {
        
        TipoEquipamentoDAO tipoEquipamentoDAO = new TipoEquipamentoDAO();
        
        if(codTipoEquipamento <= 0) {
            throw new TipoEquipamentoException(TipoEquipamentoException.CodigoErro.CODIGO_INVALIDO, codTipoEquipamento);
        }
        
        try {
            return tipoEquipamentoDAO.buscarEquipamento(conn, codTipoEquipamento);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
}

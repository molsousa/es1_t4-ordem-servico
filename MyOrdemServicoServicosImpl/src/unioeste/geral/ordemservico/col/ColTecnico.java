/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.col;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.geral.ordemservico.bo.Tecnico;
import unioeste.geral.ordemservico.dao.TecnicoDAO;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.pessoa.bo.PessoaFisica;
import unioeste.geral.pessoa.col.ColPessoaFisica;
import unioeste.geral.pessoa.exception.CPFException;

/**
 *
 * @author molsousa
 */
public class ColTecnico {
    
    public int recuperarIDTecnico(Connection conn, String cpf) throws Exception {
        
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setCpf(cpf);
        
        try {
            ColPessoaFisica.validarCPF(pessoaFisica);
        } catch (CPFException e) {
            if (e.getErro() == CPFException.CodigoErro.CPF_MAL_FORMATADO) {
                cpf = pessoaFisica.getCpf();
                cpf = ColPessoaFisica.formatarCPF(cpf);
            }
        }
        
        try {
            return tecnicoDAO.buscarIDTecnico(conn, cpf);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public List<Tecnico> recuperarTecnicos(Connection conn) throws Exception {
        
        try {
            TecnicoDAO tecnicoDAO = new TecnicoDAO();
            return tecnicoDAO.listarTecnicos(conn);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public Tecnico recuperarTecnicoPorCPF(Connection conn, String cpf) throws Exception {
        
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setCpf(cpf);
        
        try {
            ColPessoaFisica.validarCPF(pessoaFisica);
        } catch (CPFException e) {
            if (e.getErro() == CPFException.CodigoErro.CPF_MAL_FORMATADO) {
                cpf = pessoaFisica.getCpf();
                cpf = ColPessoaFisica.formatarCPF(cpf);
            }
        }
        
        try {
            return tecnicoDAO.buscarTecnicoPorCPF(conn, cpf);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
    
    public Tecnico recuperarTecnicoPorID(Connection conn, int id) throws Exception {
        
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        
        try {
            return tecnicoDAO.buscarTecnicoPorID(conn, id);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        } catch (OrdemServicoException e) {
            throw new OrdemServicoException(e.getMessage(), e);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import unioeste.apoio.bd.ConnectionFactory;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.bo.TipoEquipamento;
import unioeste.geral.ordemservico.col.ColCliente;
import unioeste.geral.ordemservico.col.ColOrdemServico;
import unioeste.geral.ordemservico.col.ColTipoEquipamento;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.EnderecoEspecifico;
import unioeste.geral.pessoa.bo.Fone;

/**
 *
 * @author molsousa
 */
public class UCOrdemServicoGeralServicos {
    
    public OrdemServico obterOrdemServico(int nroOS) throws Exception {
        
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                ColOrdemServico colOrdemServico = new ColOrdemServico();
                OrdemServico ordemServico = colOrdemServico.recuperarOrdemServico(conn, nroOS);
                conn.commit();
                return ordemServico;
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException(e.getMessage(), e);
            }
        }
    }
}

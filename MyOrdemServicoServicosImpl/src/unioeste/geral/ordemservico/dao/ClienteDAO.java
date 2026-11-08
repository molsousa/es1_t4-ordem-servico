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
import unioeste.geral.endereco.bo.Endereco;
import unioeste.geral.endereco.bo.EnderecoEspecifico;
import unioeste.geral.endereco.col.ColEndereco;
import unioeste.geral.endereco.exception.EnderecoNaoEncontradoException;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.exception.OrdemServicoException;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.pessoa.bo.Fone;
import unioeste.geral.pessoa.col.ColEmail;
import unioeste.geral.pessoa.col.ColFone;

/**
 *
 * @author molsousa
 */
public class ClienteDAO {

    public int cadastrarCliente(Connection conn, String nome, String cpf, EnderecoEspecifico enderecoEspecifico) throws Exception {

        String SQL = "INSERT INTO \"Cliente\" (nome_cliente, cpf, nro_endereco, comp_endereco, id_endereco) VALUES "
                + "(?,?,?,?,?) RETURNING id_cliente";
        
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            
            ColEndereco colEndereco = new ColEndereco();
            
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setLong(3, enderecoEspecifico.getNroEndereco());
            stmt.setString(4, enderecoEspecifico.getComplemento());
            
            int idEndereco;
            
            try {
                idEndereco = colEndereco.recuperarIDEnderecoPorCEP(conn, enderecoEspecifico.getEndereco().getCep());
            } catch (EnderecoNaoEncontradoException e) {
                idEndereco = colEndereco.cadastrarEndereco(conn, enderecoEspecifico.getEndereco());
            }
            
            stmt.setInt(5, idEndereco);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    return rs.getInt("id_cliente");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar cliente no banco: " + e.getMessage(), e);
        }
        
        throw new OrdemServicoException("Não foi possível cadastrar cliente no banco");
    }

    public Cliente buscarClientePorCPF(Connection conn, String cpf) throws Exception {

        String SQL = "SELECT id_cliente, nome_cliente, nro_endereco, comp_endereco, id_endereco FROM "
                + "\"Cliente\" WHERE cpf = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    Endereco endereco;
                    List<Fone> fones;
                    List<Email> emails;
                    int id;

                    ColFone colFone = new ColFone();
                    ColEmail colEmail = new ColEmail();
                    ColEndereco colEndereco = new ColEndereco();

                    cliente.setCpf(cpf);
                    id = rs.getInt("id_cliente");

                    fones = colFone.obterFones(conn, id, "Cliente");
                    emails = colEmail.obterEmails(conn, id, "Cliente");

                    cliente.setEnderecoEspecifico(new EnderecoEspecifico());

                    cliente.setNome(rs.getString("nome_cliente"));

                    cliente.getEnderecoEspecifico().setComplemento(rs.getString("comp_endereco"));
                    cliente.getEnderecoEspecifico().setNroEndereco(rs.getInt("nro_endereco"));

                    endereco = colEndereco.recuperarEnderecoPorID(conn, rs.getInt("id_endereco"));

                    cliente.getEnderecoEspecifico().setEndereco(endereco);

                    cliente.setEmails(emails);
                    cliente.setFones(fones);

                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cliente no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Cliente não existe na base de dados");
    }
    
    public Cliente buscarClientePorID(Connection conn, int id) throws Exception {

        String SQL = "SELECT nome_cliente, cpf, nro_endereco, comp_endereco, id_endereco FROM "
                + "\"Cliente\" WHERE id_cliente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    Endereco endereco;
                    List<Fone> fones;
                    List<Email> emails;

                    ColFone colFone = new ColFone();
                    ColEmail colEmail = new ColEmail();
                    ColEndereco colEndereco = new ColEndereco();

                    cliente.setCpf(rs.getString("cpf"));

                    fones = colFone.obterFones(conn, id, "Cliente");
                    emails = colEmail.obterEmails(conn, id, "Cliente");

                    cliente.setEnderecoEspecifico(new EnderecoEspecifico());

                    cliente.setNome(rs.getString("nome_cliente"));

                    cliente.getEnderecoEspecifico().setComplemento(rs.getString("comp_endereco"));
                    cliente.getEnderecoEspecifico().setNroEndereco(rs.getInt("nro_endereco"));

                    endereco = colEndereco.recuperarEnderecoPorID(conn, rs.getInt("id_endereco"));

                    cliente.getEnderecoEspecifico().setEndereco(endereco);

                    cliente.setEmails(emails);
                    cliente.setFones(fones);

                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cliente no banco: " + e.getMessage(), e);
        }

        throw new OrdemServicoException("Cliente não existe na base de dados");
    }
}

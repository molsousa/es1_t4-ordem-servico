/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.endereco.bo.EnderecoEspecifico;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.pessoa.bo.Email;
import unioeste.geral.ordemservico.manager.UCOrdemServicoGeralServicos;
import unioeste.geral.pessoa.bo.Fone;

/**
 *
 * @author molsousa
 */
@WebServlet("/cliente/cadastrar")
public class CadastrarClienteServlet extends HttpServlet {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCOrdemServicoGeralServicos osManager = new UCOrdemServicoGeralServicos();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            Cliente clienteForm = objectMapper.readValue(request.getReader(), Cliente.class);
            String nomeForm = clienteForm.getNome();
            String cpfForm = clienteForm.getCpf();
            EnderecoEspecifico eEForm = clienteForm.getEnderecoEspecifico();
            List<Fone> fonesForm = clienteForm.getFones();
            List<Email> emailsForm = clienteForm.getEmails();
            
            Cliente cliente = osManager.cadastrarCliente(nomeForm, cpfForm, eEForm, fonesForm, emailsForm);
            
            String clienteResponse = objectMapper.writeValueAsString(cliente);
            
            response.getWriter().println(clienteResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}

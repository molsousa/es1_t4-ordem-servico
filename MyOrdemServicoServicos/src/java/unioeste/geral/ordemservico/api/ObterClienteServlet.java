/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.ordemservico.bo.Cliente;
import unioeste.geral.ordemservico.manager.UCOrdemServicoGeralServicos;

/**
 *
 * @author molsousa
 */
@WebServlet("/cliente/obter")
public class ObterClienteServlet extends HttpServlet {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCOrdemServicoGeralServicos osManager = new UCOrdemServicoGeralServicos();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            String cpf = request.getParameter("cpf");
            
            Cliente cliente = osManager.obterCliente(cpf);
            String clienteResponse = objectMapper.writeValueAsString(cliente);
            response.getWriter().write(clienteResponse);
        } catch (Exception e) {
            e.printStackTrace();
            
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}

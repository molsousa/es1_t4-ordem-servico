package unioeste.geral.ordemservico.api.ordemservico;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.ordemservico.bo.OrdemServico;
import unioeste.geral.ordemservico.manager.UCOrdemServicoGeralServicos;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author molsousa
 */
@WebServlet("/ordem-servico/cadastrar")
public class CadastrarOrdemServicoServlet extends HttpServlet {
    
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final UCOrdemServicoGeralServicos osManager = new UCOrdemServicoGeralServicos();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            JsonNode jsonNode = objectMapper.readTree(request.getReader());
            
            int idStatusOS = 1;

            OrdemServico osForm = objectMapper.treeToValue(jsonNode, OrdemServico.class);
            
            boolean sucesso = osManager.cadastrarOrdemServico(osForm, idStatusOS);
            
            String osResponse = objectMapper.writeValueAsString(sucesso);
            response.getWriter().println(osResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}

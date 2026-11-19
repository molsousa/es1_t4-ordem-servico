/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.api.ordemservico;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.ordemservico.manager.UCOrdemServicoGeralServicos;

/**
 *
 * @author molsousa
 */
@WebServlet("/ordem-servico/atualizar/os-produto")
public class CadastrarOSProdutoServlet extends HttpServlet {
    
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
    private final UCOrdemServicoGeralServicos osManager = new UCOrdemServicoGeralServicos();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            JsonNode body = objectMapper.readTree(request.getReader());
            
            JsonNode nroNode = body.get("nroOS");
            JsonNode prodNode = body.get("idProduto");
            JsonNode qtdNode = body.get("qtdProduto");
            JsonNode valorCobradoNode = body.get("valorCobradoProduto");
            
            int nroOS = nroNode.asInt();
            int idProduto = prodNode.asInt();
            int qtd = qtdNode.asInt();
            double valorCobrado = valorCobradoNode.asDouble();
            
            double valorTotal = osManager.cadastrarOSProduto(nroOS, idProduto, valorCobrado, qtd);
            String cadastroOSProdutoResponse = objectMapper.writeValueAsString(valorTotal);
            
            response.getWriter().println(cadastroOSProdutoResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}

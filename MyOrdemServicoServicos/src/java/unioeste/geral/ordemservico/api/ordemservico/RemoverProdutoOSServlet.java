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
@WebServlet("/ordem-servico/produto/remover")
public class RemoverProdutoOSServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final UCOrdemServicoGeralServicos osManager = new UCOrdemServicoGeralServicos();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            int nroOS = Integer.parseInt(request.getParameter("nro_os"));
            int idProduto = Integer.parseInt(request.getParameter("id_produto"));
            
            boolean sucesso = osManager.removerProdutoOS(idProduto, nroOS);

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

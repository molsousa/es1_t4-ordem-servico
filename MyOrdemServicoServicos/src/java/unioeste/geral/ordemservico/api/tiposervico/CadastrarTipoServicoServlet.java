/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.api.tiposervico;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.ordemservico.bo.TipoServico;
import unioeste.geral.ordemservico.manager.UCTipoServicoGeralServicos;

/**
 *
 * @author molsousa
 */
@WebServlet("/tipo-servico/cadastrar")
public class CadastrarTipoServicoServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCTipoServicoGeralServicos tipoServicoManager = new UCTipoServicoGeralServicos();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            TipoServico tipoServicoForm = objectMapper.readValue(request.getReader(), TipoServico.class);

            int idTipoServico = tipoServicoManager.cadastrarTipoServico(tipoServicoForm.getNome(), tipoServicoForm.getValor());
            String tipoServicoResponse = objectMapper.writeValueAsString(idTipoServico);

            response.getWriter().println(tipoServicoResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}

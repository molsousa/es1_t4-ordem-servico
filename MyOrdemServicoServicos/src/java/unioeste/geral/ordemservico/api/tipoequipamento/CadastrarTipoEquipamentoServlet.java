/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.ordemservico.api.tipoequipamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unioeste.geral.ordemservico.manager.UCTipoEquipamentoGeralServicos;

/**
 *
 * @author molsousa
 */
@WebServlet("/tipo-equipamento/cadastrar")
public class CadastrarTipoEquipamentoServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UCTipoEquipamentoGeralServicos tipoEquipamentoManager = new UCTipoEquipamentoGeralServicos();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
            String nomeTipoEquipamento = request.getParameter("nome_tipo_equipamento");
            
            int idTipoEquipamento = tipoEquipamentoManager.cadastrarTipoEquipamento(nomeTipoEquipamento);
            String idTipEquipResponse = objectMapper.writeValueAsString(idTipoEquipamento);
            
            response.getWriter().println(idTipEquipResponse);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            Map<String, String> erro = new HashMap<>();
            erro.put("message", e.getMessage());

            String errorJson = objectMapper.writeValueAsString(erro);
            response.getWriter().println(errorJson);
        }
    }
}

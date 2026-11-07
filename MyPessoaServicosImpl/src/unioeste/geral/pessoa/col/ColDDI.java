/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.col;

import java.util.regex.Pattern;
import unioeste.geral.pessoa.bo.DDI;
import unioeste.geral.pessoa.exception.DDIException;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class ColDDI {

    private static final Pattern DDI_PATTERN = Pattern.compile("^\\d{3}$");
    private static final Pattern DDI_PATTERN_ALT = Pattern.compile("^\\d{2}$");

    public static boolean validarFormato(DDI ddi) throws PessoaException {

        if (!DDI_PATTERN.matcher(ddi.getNro()).matches()) {
            if (DDI_PATTERN_ALT.matcher(ddi.getNro()).matches()) {
                ddi.setNro("0" + ddi.getNro());
            } else {
                throw new DDIException(DDIException.CodigoErro.DDI_INVALIDO, ddi.getNro());
            }
        }

        return true;
    }
    
    public static boolean validarDDI(DDI ddi) throws PessoaException {
        
        if (ddi.getNro() == null || ddi.getNro().trim().isEmpty()) {
            throw new DDIException(DDIException.CodigoErro.DDI_INVALIDO, ddi.getNro());
        }
        
        ddi.setNro(ddi.getNro().trim());
        
        return true;
    }
}

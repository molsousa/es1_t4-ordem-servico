/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unioeste.geral.pessoa.col;

import java.util.regex.Pattern;
import unioeste.geral.pessoa.bo.DDD;
import unioeste.geral.pessoa.exception.DDDException;
import unioeste.geral.pessoa.exception.PessoaException;

/**
 *
 * @author molsousa
 */
public class ColDDD {

    private static final Pattern DDD_PATTERN = Pattern.compile("^\\d{3}$");
    private static final Pattern DDD_PATTERN_ALT = Pattern.compile("^\\d{2}$");

    public static boolean validarFormato(DDD ddd) throws PessoaException {

        if (!DDD_PATTERN.matcher(ddd.getNro()).matches()) {
            if (DDD_PATTERN_ALT.matcher(ddd.getNro()).matches()) {
                ddd.setNro("0" + ddd.getNro());
            } else {
                throw new DDDException(DDDException.CodigoErro.DDD_INVALIDO, ddd.getNro());
            }
        }
        
        return true;
    }
    
    public static boolean validarDDD(DDD ddd) throws PessoaException {
        
        if(ddd.getNro() == null || ddd.getNro().trim().isEmpty()) {
            throw new DDDException(DDDException.CodigoErro.DDD_INVALIDO, ddd.getNro());
        }
        
        ddd.setNro(ddd.getNro().trim());
        
        return true;
    }

}

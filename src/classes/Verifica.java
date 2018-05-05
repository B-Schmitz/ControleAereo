/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import exception.ExceptionCoord;

/**
 *
 * @author comp16
 */
public class Verifica {
    public Double verificaDouble (String a) throws ExceptionCoord{
        try{
            return Double.parseDouble(a);
        }catch(Exception e){
            throw new ExceptionCoord("Número inválido.");
        }
    }
}

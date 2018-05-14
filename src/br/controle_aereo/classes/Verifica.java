/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controle_aereo.classes;

import br.controle_aereo.excecoes.ExcecaoGeral;

/**
 *
 * @author comp16
 */
public class Verifica {
    public Double verificaDouble (String a) throws ExcecaoGeral{
        try{
            return Double.parseDouble(a);
        }catch(Exception e){
            throw new ExcecaoGeral("Número inválido.");
        }
    }
}

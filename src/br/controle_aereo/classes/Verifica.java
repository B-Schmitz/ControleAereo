package br.controle_aereo.classes;

import br.controle_aereo.excecoes.ExcecaoGeral;

public class Verifica {

    public Double verificaDouble(String a) throws ExcecaoGeral {
        try {
            return Double.parseDouble(a);
        } catch (NumberFormatException e) {
            throw new ExcecaoGeral("Número inválido.");
        }
    }
}

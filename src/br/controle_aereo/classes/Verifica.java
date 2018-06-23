package br.controle_aereo.classes;

import br.controle_aereo.excecoes.ExcecaoErro;

public class Verifica {

    public Double verificaDouble(String a) throws ExcecaoErro {
        try {
            return Double.parseDouble(a);
        } catch (NumberFormatException e) {
            throw new ExcecaoErro("Número inválido.");
        }
    }
}

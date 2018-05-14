package br.controle_aereo.excecoes;

public class ExcecaoGeral extends Exception {

    public ExcecaoGeral() {
        super("Erro Geral.");
    }

    public ExcecaoGeral(String message) {
        super(message);
    }

}

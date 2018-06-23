package br.controle_aereo.excecoes;

public class ExcecaoErro extends Exception {

    public ExcecaoErro() {
        super("Erro Desconhecido.");
    }

    public ExcecaoErro(String message) {
        super(message);
    }

}

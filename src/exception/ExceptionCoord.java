/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Rafael
 */
public class ExceptionCoord extends Exception {

    public ExceptionCoord() {
        super("Erro Geral.");
    }

    public ExceptionCoord(String message) {
        super(message);
    }

}

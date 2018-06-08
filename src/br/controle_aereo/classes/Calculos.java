package br.controle_aereo.classes;

public class Calculos {

    double[] resultado = new double[2];

    public double[] calculaPolar(double x, double y) {
        // Cálcula os valores de x e y ao quadradro
        x = Math.pow(x, 2);
        y = Math.pow(y, 2);
        resultado[0] = Math.sqrt(x + y); // Soma os valores e tira a raiz, isso é o raio
        resultado[1] = Math.toDegrees((Math.atan2(y, x))); // Realiza arcotangente de y dividido por x, isso é o ângulo

        return resultado;
    }

    public double[] calculaCartesiano(double raio, double angulo) {
        resultado[0] = raio * Math.cos(Math.toRadians(angulo)); // Raio vezes o consseno do ângulo = X
        resultado[1] = raio * Math.sin(Math.toRadians(angulo)); // Raio vezes o seno do ângulo = Y

        return resultado;
    }

    public double[] calculaRotacao(double x, double y, double angulo, double Tx, double Ty) {
        // Diminui o Tx e Ty
        x -= Tx;
        y -= Ty;
        // Formula da rotação, adicionando o Tx e Ty no fim.
        resultado[0] = (x * Math.cos(Math.toRadians(angulo)) - y * Math.sin(Math.toRadians(angulo)) + Tx);
        resultado[1] = (y * Math.cos(Math.toRadians(angulo)) + x * Math.sin(Math.toRadians(angulo)) + Ty);
        return resultado;
    }

    public double[] calculaEscala(double x, double y, double Sx, double Sy) {
        resultado[0] = x * (Sx / 100);
        resultado[1] = y * (Sy / 100);
        return resultado;
    }

    public double[] calculaTranslacao(double x, double y, double Tx, double Ty) {
        resultado[0] = x + Tx;
        resultado[1] = y + Ty;
        return resultado;
    }

    public double calculaDistanciaPontos(double x1, double y1, double x2, double y2) {
        double dist, dx, dy;
        dx = x2 - x1;
        dx = Math.pow(dx, 2);
        dy = y2 - y1;
        dy = Math.pow(dy, 2);
        dist = Math.sqrt(dx + dy);
        return dist;
    }

    public double[] calculaIntersecacao(double x1, double y1, double angulo1, double x2, double y2, double angulo2) {
        double x, y, m, m1;

        m = Math.tan(Math.toRadians(angulo1));
        m1 = Math.tan(Math.toRadians(angulo2));

        if (m == m1) {
            return null;
        } else {
            x = ((m * x1 + y1 - y2) + (m1 * x2)) / (m1 - m);
            y = ((m * x) - (m * x1)) + y1;
            resultado[0] = x;
            resultado[1] = y;
            return resultado;
        }
    }

    public double calculaTempo(double x1, double y1, double velocidade, double x2, double y2) {
        double distancia = calculaDistanciaPontos(x1, y1, x2, y2);
        return (distancia / velocidade) * 3600;
    }
}

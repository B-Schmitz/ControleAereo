package br.controle_aereo.classes;

import java.util.Objects;

public class Calculos {

   private final double[] resultado = new double[2];

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

    public double[] calculaIntersecacao(double x1, double y1, double dir1, double x2, double y2, double dir2) {
        
         Double x, y, m1, m2, linear1, linear2;

        m1 = Math.tan(Math.toRadians(dir1));
        m2 = Math.tan(Math.toRadians(dir2));
        
         System.out.println("Coeficiente m1: " + m1 + "Coeficiente m2: " + m2);

        if (Objects.equals(m1, m2)) {
            return null;
       } 
            if(dir1 != 90 && dir1 != 270){
            linear1 = y1 - (m1 * x1);
            linear2 = y2 - (m2 * x2);
            System.out.println("Coeficiente linear1: " + linear1 + "Coeficiente linear2: " + linear2);
            x = (linear2 - linear1)/(m1 - m2);
            y = m1 * x + linear1;
                System.out.println("X : " + x);
                System.out.println("Y : " + y);
                
                resultado[0] = x;
                resultado[1] = y;
            }
           
    return resultado;
    }

    public double calculaTempo(double x1, double y1, double velocidade, double x2, double y2,double anguloDir) {
        
        
        if(anguloDir%360 > 0 && anguloDir%360 < 180){
            if(y1 > y2){
                return -1;
            }
        }
        else if(anguloDir%360 > 180){
            if(y1 < y2){
                return -1;
            }
        }
        
        else{
            if(anguloDir%360 == 0){
                if( x1 > x2){
                    return -1;
                }
            }
            
            if(anguloDir%360 == 180){
                if(x1 < x2){
                   return -1; 
                }
            }
        }
        double distancia = calculaDistanciaPontos(x1, y1, x2, y2);
        return (distancia / velocidade) * 3600;
    }
}
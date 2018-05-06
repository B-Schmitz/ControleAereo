package br.controle_aereo.classes;

public class Pontos {

    Double x, y, raio, angulo, velocidade, direcao;
    int linha, id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getR() {
        return raio;
    }

    public void setR(Double r) {
        this.raio = r;
    }

    public Double getAng() {
        return angulo;
    }

    public void setAng(Double ang) {
        this.angulo = ang;
    }

    public Double getVel() {
        return velocidade;
    }

    public void setVel(Double vel) {
        this.velocidade = vel;
    }

    public Double getDir() {
        return direcao;
    }

    public void setDir(Double dir) {
        this.direcao = dir;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

}

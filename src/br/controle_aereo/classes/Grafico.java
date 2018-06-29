package br.controle_aereo.classes;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import br.controle_aereo.janelas.Principal;

public class Grafico {

    private final Principal frame;
    private Graphics grafico;
    private Pontos ponto;
    private BufferedImage img = null;

    public Grafico(Principal frame) {
        this.frame = frame;
        Carregar_Imagem();
    }

    public final void Carregar_Imagem() {
        try {
            img = ImageIO.read(new File("src/br/controle_aereo/icones/aviao.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Desenha() {
        grafico = frame.GetPainel().getGraphics();
        grafico.drawLine(0, 200, 400, 200);
        grafico.drawLine(200, 0, 200, 400);
        Double[] coordenadas = new Double[2];

       if (frame.isAlive()) {
            grafico.clearRect(0, 0, 400, 400);
            if (frame.isAcaoExclusao()) {
                while (!frame.getFilaAcao().isEmpty()) {
                    ponto = new Pontos();
                    ponto = frame.getFilaAcao().poll();
                    coordenadas = normalizaPontos(ponto.getX(), ponto.getY());

                }
                frame.setAcaoExclusao(false);
            }
            grafico.drawLine(0, 200, 400, 200);
            grafico.drawLine(200, 0, 200, 400);
            for (int i = 0; i < frame.getModel().getRowCount(); i++) {
                coordenadas = normalizaPontos(Double.parseDouble(frame.getModel().getValueAt(i, 2).toString().replace(",", ".")), Double.parseDouble(frame.getModel().getValueAt(i, 3).toString().replace(",", ".")));
                inserePonto(coordenadas[0], coordenadas[1], Double.parseDouble(frame.getModel().getValueAt(i, 7).toString().replace(",", ".")), frame.getModel().getValueAt(i, 1).toString());
            }
        }

    }

    public void inserePonto(Double x, Double y, double dir, String id) {

        double Direcao = Math.toRadians(360 - dir);
        double Localizacao_X = img.getWidth() / 2;
        double Localizacao_Y = img.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Direcao, Localizacao_X, Localizacao_Y);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        grafico.drawImage(op.filter(img, null), x.intValue() - 15, y.intValue() - 15, null);
        grafico.drawString(id, x.intValue() - 1, y.intValue() - 12);

    }

    public Double[] normalizaPontos(double x, double y) {
        Double[] coordenadas = new Double[2];
        if (y >= 0) {
            y = 200 - y;
        } else {
            y = -y + 200;
        }
        x = x + 200;

        coordenadas[0] = x;
        coordenadas[1] = y;

        return coordenadas;
    }
}
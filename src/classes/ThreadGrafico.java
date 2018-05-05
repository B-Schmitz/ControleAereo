package classes;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import telas.Principal;


public class ThreadGrafico implements Runnable {

    Principal frame;
    Graphics graph;
    Pontos ponto;
    BufferedImage img = null;

    public ThreadGrafico(Principal frame) {
        this.frame = frame;
        carregaImg();
    }

    public final void carregaImg() {
        try {
            img = ImageIO.read(new File("src/br/controle_aereo/icones/aviao.png"));
            System.out.println("Carregou imagem.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Thread iniciada com sucesso.");
        graph = frame.GetPainel().getGraphics();
        graph.drawLine(0, 200, 400, 200);
        graph.drawLine(200, 0, 200, 400);
        Double[] coordenadas = new Double[2];

        while (frame.isAlive()) {
            if (frame.isAcaoExclusao()) {
                while (!frame.getFilaAcao().isEmpty()) {
                    ponto = new Pontos();
                    ponto = frame.getFilaAcao().poll();
                    coordenadas = normalizaPontos(ponto.getX(), ponto.getY());
                    graph.clearRect(coordenadas[0].intValue() - 10, coordenadas[1].intValue() - 10, 20, 20);
                }
                frame.setAcaoExclusao(false);
            }
            graph.drawLine(0, 200, 400, 200);
            graph.drawLine(200, 0, 200, 400);
            for (int i = 0; i < frame.getModel().getRowCount(); i++) {
                coordenadas = normalizaPontos(Double.parseDouble(frame.getModel().getValueAt(i, 2).toString().replace(",", ".")), Double.parseDouble(frame.getModel().getValueAt(i, 3).toString().replace(",", ".")));
                inserePonto(coordenadas[0], coordenadas[1],Double.parseDouble(frame.getModel().getValueAt(i, 7).toString().replace(",", ".")));
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadGrafico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void inserePonto(Double x, Double y, double dir) {

        double rotationRequired = Math.toRadians(360-dir);
        double locationX = img.getWidth() / 2;
        double locationY = img.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        //graph.drawOval(x.intValue() - 3, y.intValue() - 3, 6, 6);
        graph.drawImage(op.filter(img, null), x.intValue() - 15, y.intValue() - 15, null);

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

package com.juniverse.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Dibuja la curva del dragón (fractal de Heighway) de forma recursiva en un panel Swing.
 *
 * @author Java Universe
 */
public class DragonFractal extends JPanel {

    private static final int WIDTH_SCREEN = 800;
    private static final int HEIGHT_SCREEN = 600;
    private static final int MAX_DEPTH = 15;
    private static final long serialVersionUID = 1L;

    /**
     * Punto de entrada: crea la ventana de 800x600 con fondo negro y muestra el fractal.
     * @param args no se usan
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal del Dragón");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        frame.setLocationRelativeTo(null);
        DragonFractal panel = new DragonFractal();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setVisible(true);
    }

    /** Dibuja el fractal partiendo de un segmento horizontal centrado de 300 píxeles. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(1.2f));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Coordenadas iniciales centradas
        double length = 300.0;
        double x1 = getWidth() / 2.0 - length / 2.0;
        double y1 = getHeight() / 2.0;
        double x2 = getWidth() / 2.0 + length / 2.0;
        double y2 = getHeight() / 2.0;

        drawDragon(g2, x1, y1, x2, y2, MAX_DEPTH, true);
    }

    /**
     * Dibuja recursivamente un segmento de la curva del dragón: cada segmento se sustituye
     * por dos segmentos perpendiculares que alternan el giro (izquierda/derecha).
     * @param g2 contexto gráfico
     * @param x1 x del punto inicial
     * @param y1 y del punto inicial
     * @param x2 x del punto final
     * @param y2 y del punto final
     * @param depth niveles de recursión restantes (0 dibuja la línea)
     * @param left {@code true} si este segmento gira hacia la izquierda
     */
    private void drawDragon(Graphics2D g2, double x1, double y1, double x2,
            double y2, int depth, boolean left) {
        if (depth == 0) {
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        } else {
            double midX = (x1 + x2) / 2;
            double midY = (y1 + y2) / 2;

            double dx = (x2 - x1) / 2;
            double dy = (y2 - y1) / 2;

            double newX, newY;
            if (left) {
                newX = midX - dy;
                newY = midY + dx;
            } else {
                newX = midX + dy;
                newY = midY - dx;
            }

            drawDragon(g2, x1, y1, newX, newY, depth - 1, true);
            drawDragon(g2, newX, newY, x2, y2, depth - 1, false);
        }
    }
}

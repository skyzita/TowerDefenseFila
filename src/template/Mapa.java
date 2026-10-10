package template;

import java.awt.Point;

/**
 *
 * @author soleile
 */
public class Mapa {
    
    //Define como o mapa será desenhado
    int[][] mapa = {
        {1, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0 ,0, 0, 0, 1},
        {0, 0, 0, 1, 1, 1, 1, 1},
        {0, 0, 0, 1, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1}
    };
    
    
    //Define os caminhos que os inimigos irão percorrer
    final private Point[] caminho = {
    new Point(0 * 64 + 32, 0 * 64 + 32),
    new Point(0 * 64 + 32, 1 * 64 + 32),
    new Point(7 * 64 + 32, 1 * 64 + 32),
    new Point(7 * 64 + 32, 3 * 64 + 32),
    new Point(3 * 64 + 32, 3 * 64 + 32),
    new Point(3 * 64 + 32, 5 * 64 + 32),
    new Point(7 * 64 + 32, 5 * 64 + 32),
    new Point(7 * 64 + 32, 7 * 64 + 32),
    new Point(0 * 64 + 32, 7 * 64 + 32)};
    
    public Point[] getcaminho() {
        return caminho;
    }

    public int[][] getMapa() {
        return mapa;
    }
    
}

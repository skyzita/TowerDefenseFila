package template;

/**
 *
 * @author Mari
 */
public class Tiros {
    
    final private double velocidade = 400;
    final private int dano;
    final private Inimigos alvo;
    private double x;
    private double y;
    public boolean atingiuAlvo = false;
    
    public Tiros (double x, double y, int dano, Inimigos alvo) {
        this.x = x;
        this.y = y;
        this.dano = dano;
        this.alvo = alvo;
    }
    
    public void update (double delta) {
        if (alvo == null || alvo.morreu) {
            atingiuAlvo = true;
            return;
        }
        
        double dx = alvo.getPosXIni() - x;
        double dy = alvo.getPosYIni() - y;
        double distancia = Math.hypot(dx, dy);
        
        if (distancia <= 10) {
            alvo.receberDano(dano);
            atingiuAlvo = true;
        } else {
            x += (dx / distancia) * velocidade * delta;
            y += (dy / distancia) * velocidade * delta;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
        
    
}

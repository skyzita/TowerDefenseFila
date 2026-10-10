package template;
/**
 *
 * @author soleile
 */
public class Inimigos {
final private String nomeIni;
final private int corIni;
final private int recompensaIni;
private int vidaIni;
private int danoIni;
private int velocidadeIni;
private double tamanhoXIni, tamanhoYIni;
private double posYIni, posXIni;
private int indicePontoAtual;
private double angulo;
public boolean morreu;

    public Inimigos(String nomeIni, int vidaIni, int danoIni, int corIni, int recompensaIni, int velocidadeIni, double tamanhoXIni, double tamanhoYIni, boolean morreu) {
        this.nomeIni = nomeIni;
        this.vidaIni = vidaIni;
        this.danoIni = danoIni;
        this.corIni = corIni;
        this.recompensaIni = recompensaIni;
        this.velocidadeIni = velocidadeIni;
        this.tamanhoXIni = tamanhoXIni;
        this.tamanhoYIni = tamanhoYIni;
        this.morreu = morreu;
    }

    

    public double getPosYIni() {
        return posYIni;
    }
    public void setPosYIni(double posYIni) {
        this.posYIni = posYIni;
    }

    public double getPosXIni() {
        return posXIni;
    }
    public void setPosXIni(double posXIni) {
        this.posXIni = posXIni;
    }

    public int getIndicePontoAtual() {
        return indicePontoAtual;
    }
    public void setIndicePontoAtual(int indicePontoAtual) {
        this.indicePontoAtual = indicePontoAtual;
    }

    public int getDanoIni() {
        return danoIni;
    }

    public int getVelocidadeIni() {
        return velocidadeIni;
    }

    public int getCorIni() {
        return corIni;
    }
    
    public void receberDano(int dano) {
        vidaIni -= dano;
        if (vidaIni <= 0) {
            morreu = true;
            
        }
    }
    
    public int getRecompensaIni() {
        return recompensaIni;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
    
    
}

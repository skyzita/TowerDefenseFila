package template;

/**
 *
 * @author soleile
 */
public class Jogador {
    private int vidaJogador;
    private int dinheiroJogador;

    public Jogador (int vidaJogador, int dinheiroJogador) {
        this.vidaJogador = vidaJogador;
        this.dinheiroJogador = dinheiroJogador;
    }
    
    public int getVidaJogador() {
        return vidaJogador;
    }

    public int getDinheiroJogador() {
        return dinheiroJogador;
    }
    
    public void receberDano(int dano){
        vidaJogador -= dano;
    }
    
    public void ganharDinheiro(int valor){
        dinheiroJogador += valor;
    }
    
    public void gastarDinheiro(int valor) {
        if(dinheiroJogador >= valor) {
            dinheiroJogador -= valor;
        }
        
    }
    
    public boolean estaMorto(){
    if(vidaJogador <= 0) {
        return true;
    }
        return false;
    }
    
}
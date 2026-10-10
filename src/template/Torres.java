package template;

import java.util.ArrayList;
import template.Torres;

/**
 *
 * @author soleile
 */
public class Torres {
    
    final private int danoTorre;
    final private int corTorre;
    final private int raioTorre;
    final private int preco;
    final private double delayTiro;
    private String nomeTorre;
    private double posX;
    private double posY;
    private double angulo;
    
    //Se a torre têm um limite de unidades daquele mesmo tipo
    private boolean limitada;
    private int limite;
    
    private float timer;
    
    public Torres(String nomeTorre, int danoTorre, int preco, int corTorre, int raioTorre, double delayTiro, boolean limitada, int limite) {
        this.nomeTorre = nomeTorre;
        this.danoTorre = danoTorre;
        this.preco = preco;
        this.corTorre = corTorre;
        this.raioTorre = raioTorre;
        this.delayTiro = delayTiro;
        this.limitada = limitada;
        this.limite = limite;
    }

    private Inimigos encontrarAlvo(ArrayList<Inimigos> inimigos){

       for(int i = 0; i < inimigos.size(); i++){
        double dx = inimigos.get(i).getPosXIni() - posX;
        double dy = inimigos.get(i).getPosYIni() - posY;
        
        if(Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(raioTorre, 2)){
            return inimigos.get(i);
        }
       }
       
       return null;

    }
    
    void update(double delta, ArrayList<Inimigos> inimigos, ArrayList<Tiros> listaTiros) {
        timer += delta;
        Inimigos alvo = encontrarAlvo(inimigos);
        
        if(alvo != null) { 
            
            double dx = alvo.getPosXIni() - this.posX;
            double dy = alvo.getPosYIni() - this.posY;
            this.angulo = Math.atan2(dy, dx);
            
            if (timer >= delayTiro) {
                int danoAplicado;
                if (alvo.getCorIni() == corTorre) {
                    danoAplicado = danoTorre;
                } else {
                    danoAplicado = danoTorre / 2;
                }
                
                double tamanhoCano = 20;
                double pontaX = this.posX + (Math.cos(this.angulo) * tamanhoCano);
                double pontaY = this.posY + (Math.sin(this.angulo) * tamanhoCano);
                
                Tiros novoTiro = new Tiros(pontaX, pontaY, danoAplicado, alvo);
                listaTiros.add(novoTiro);
                
                timer = 0;
            } 
        }
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getCorTorre() {
        return corTorre;
    }
    
    public int getPreco() {
        return preco;
    }

    public double getAngulo() {
        return angulo;
    }
    
    
}

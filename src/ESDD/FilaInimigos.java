package ESDD;

import template.Inimigos;

/**
 *
 * @author soleile
 */

import ESDD.NosInimigos;
        
public class FilaInimigos {
    
    private NosInimigos caudaFila;
    private NosInimigos cabecaFila;
    

    public FilaInimigos(NosInimigos caudaFila, NosInimigos cabecaFila) {
        this.caudaFila = null;
        this.cabecaFila = null;
    }    
    
    
    //Checa se a Fila de Inimigos está vazia
    public boolean filaVazia(){
    if(this.cabecaFila == null){
     return true;   
     }
    return false;
    }
    
    
    // Coloca novos inimigos atrás da fila
    public void enfileirar(NosInimigos novoNo){
        if(filaVazia()){
            cabecaFila = novoNo;
            caudaFila = cabecaFila;
        }
        else{
            caudaFila.setProximo(novoNo);
            caudaFila = novoNo;
            
        }
    }
    // Remove os inimigos que estão na cabeça para criá-los no jogo
    public Inimigos desenfileirar() {
        if(filaVazia()){
            return null;
        }
        
        else{
            Inimigos temp = cabecaFila.getInimigo();
            cabecaFila = cabecaFila.getProximo();
            if(cabecaFila == null){
                caudaFila = null;
            }
            
            return temp;
        }
    }

    public NosInimigos getCaudaFila() {
        return caudaFila;
    }

    public void setCaudaFila(NosInimigos caudaFila) {
        this.caudaFila = caudaFila;
    }

    public NosInimigos getCabecaFila() {
        return cabecaFila;
    }

    public void setCabecaFila(NosInimigos cabecaFila) {
        this.cabecaFila = cabecaFila;
    }
}

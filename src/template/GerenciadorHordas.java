package template;

import ESDD.FilaInimigos;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A classe gerenciador de hordas ajuda o jogo a gerar uma nova horda com mais facilidade, apenas instanciando inimigos criados.
 * @author soleile
 *  
 */
public class GerenciadorHordas {
    
    final private ArrayList<FilaInimigos> filaEspera;
    final private ArrayList<Inimigos> inimigosNoMapa;
    final private Point[] caminho;
    final private Jogador jogador;
    final private int tempoSpawn;
    private int indiceRodadaAtual = 0;
    private int contadorSpawn;

    
    //Construtor das Hordas
    public GerenciadorHordas(ArrayList<FilaInimigos> fila, Point[] caminho, Jogador jogador, int delta) {
        this.filaEspera = fila;
        this.inimigosNoMapa = new ArrayList<>();
        this.caminho = caminho;
        this.jogador = jogador;
        this.tempoSpawn = delta;
        this.contadorSpawn = 0;
    }
    
    //Método que atualiza a posição dos Inimigos
    public void update(){
        spawnInimigos();
        moverInimigos();
        apagarInimigos();
        //verificarFimDaRodada();
    }

    
    //Esse método gerencia quando os inimigos saem da fila de spawn
    private void spawnInimigos() {
        
        if (indiceRodadaAtual >= filaEspera.size()) return;
        
        FilaInimigos rodadaAtual = filaEspera.get(indiceRodadaAtual);
        
        if(!rodadaAtual.filaVazia()) {
            contadorSpawn++;
            if(contadorSpawn >= tempoSpawn){
                contadorSpawn = 0;
                Inimigos novoInimigo = rodadaAtual.desenfileirar();
                if(novoInimigo != null){
                    
                    novoInimigo.setPosXIni(caminho[0].x);
                    novoInimigo.setPosYIni(caminho[0].y - 32);
                    novoInimigo.setIndicePontoAtual(1);
                    inimigosNoMapa.add(novoInimigo);
                }
            }
        }
    }
    
    /* Inimigos têm como objetivo moverem-se de ponto a ponto, ganhando um novo
    *  ponto para se mover após chegar em seu destino.
    *  
    *  Caso o inimigo chegue no último ponto (base), o inimigo desaparece e
    *  causa dano ao jogador.
    */
private void moverInimigos(){
        for(int i = inimigosNoMapa.size() - 1; i >= 0; i--){
            Inimigos ini = inimigosNoMapa.get(i);
            int indiceAlvo = ini.getIndicePontoAtual();
            
            if(indiceAlvo >= caminho.length){
                jogador.receberDano(ini.getDanoIni());
                inimigosNoMapa.remove(i);
                continue;
            }
            
            Point alvo = caminho[indiceAlvo];
            double dx = alvo.x - ini.getPosXIni();
            double dy = alvo.y - ini.getPosYIni();
            double distancia = Math.hypot(dx, dy);
            double velocidade = ini.getVelocidadeIni();
            
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    ini.setAngulo(90);
                } else {
                    ini.setAngulo(270);
                } 
                       
            } else if (Math.abs(dy) > Math.abs(dx)) {
                if (dy > 0) {
                    ini.setAngulo(180);
                } else {
                    ini.setAngulo(0);
                }
            }
            
            if(distancia <= velocidade) {
                ini.setPosXIni(alvo.x);
                ini.setPosYIni(alvo.y);
                ini.setIndicePontoAtual(indiceAlvo + 1);
            } else {
                ini.setPosXIni(ini.getPosXIni() + (dx / distancia) * velocidade);
                ini.setPosYIni(ini.getPosYIni() + (dy / distancia) * velocidade);
            }
        }
    }

    /* Esse método tem como objetivo apagar o inimigo quando ele é morto e
    *  entregar o dinheiro ao jogador dado após a morte do inimigo. 
    */
    public void apagarInimigos() {
        for (int i = inimigosNoMapa.size() - 1; i >= 0; i--) {
            Inimigos ini = inimigosNoMapa.get(i);
            
            if (ini.morreu) {
                jogador.ganharDinheiro(ini.getRecompensaIni());
                inimigosNoMapa.remove(ini);
            }
        }
        
    }
    
    /*Esse método verifica se uma rodada acabou e altera o valor do indice para 
    * começar uma nova rodada
    */
    public boolean verificarFimDaRodada() {
        if (indiceRodadaAtual >= filaEspera.size()) return true;
        
        FilaInimigos rodadaAtual = filaEspera.get(indiceRodadaAtual);
        
        if (rodadaAtual.filaVazia() && inimigosNoMapa.isEmpty()) {
            indiceRodadaAtual++;
            return true;
        }
        
        return false;
    }
    
    public ArrayList<Inimigos> getInimigosNoMapa() {
        return inimigosNoMapa;
    }
    
    public FilaInimigos getFilaRodadaAtual() {
        if (indiceRodadaAtual < filaEspera.size()) {
            return filaEspera.get(indiceRodadaAtual);
        }
        return null;
    }
    
}

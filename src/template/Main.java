package template;


import ESDD.FilaInimigos;
import ESDD.NosInimigos;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.core.utils.CoreUtils;
import br.com.davidbuzatto.jsge.core.utils.DrawingUtils;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.image.Image;
import br.com.davidbuzatto.jsge.image.ImageUtils;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashSet;


/**
 * Tower Defense
 * 
 * JSGE basic project template.
 * 
 * @author Sol de Lima
 * @author Mari Diniz
 */
public class Main extends EngineFrame {
    
    private Image logo;
    private boolean rodadaAtiva;
    private NosInimigos atual;
    private Jogador jogador;
    private GerenciadorHordas gerenciadorHordas;
    
    private Mapa mapa;
    private FilaInimigos rodada1;
    private FilaInimigos rodada2;
    private FilaInimigos rodada3;
    private FilaInimigos rodada4;
    private String torreSelecionada = null;
    private ArrayList<FilaInimigos> todasAsRodadas;
    private ArrayList<Tiros> tirosNoMapa;
    
    private Image agua;
    private Image grama;
    private Image arminhaAmarela;
    private Image arminhaAzul;
    private Image arminhaVermelha;
    private Image tubaraoImg;
    private Image canudoImg;
    private Image sapinhoImg;
    
    
    private ArrayList<Torres> torres;
    
    
    
    public Main() {
        
        super(
            800,                 // largura                      / width
            500,                 // algura                       / height
            "Window Title",      // título                       / title
            60,                  // quadros por segundo desejado / target FPS
            true,                // suavização                   / antialiasing
            false,               // redimensionável              / resizable
            false,               // tela cheia                   / full screen
            false,               // sem decoração                / undecorated
            false,               // sempre no topo               / always on top
            false                // fundo invisível              / invisible background
        );
        
    }
    
    /**
     * Cria o mundo do jogo.
     * Esse método executa apenas uma vez durante a inicialização da engine.
     * 
     * Creates the game world.
     * This method runs just one time during engine initialization.
     */
    @Override
    public void create() {
        logo = DrawingUtils.createLogo();
        logo.resize( (int) ( logo.getWidth() * 0.1 ), (int) ( logo.getWidth() * 0.1 ) );
        setWindowIcon( logo );
        
        mapa = new Mapa();
        jogador = new Jogador(100, 100);
        rodada1 = new FilaInimigos(null, null);
        rodada2 = new FilaInimigos(null, null);
        rodada3 = new FilaInimigos(null, null);
        rodada4 = new FilaInimigos(null, null);
        torres = new ArrayList<>();
        todasAsRodadas = new ArrayList<>();
        tirosNoMapa = new ArrayList<>();
        
        Inimigos tubarao = new Inimigos("Tubarão", 50, 100, 1, 50, 1, 32, 32, false);
        Inimigos sapinho = new Inimigos("Sapinho", 50, 100, 1, 50, 1, 32, 32, false);
        Inimigos canudo  = new Inimigos("Canudo", 50, 100, 3, 50, 1, 32, 32, false);
              
        // Criando torres: Nome, Dano, Preço, Cor Efetiva, Raio, Velocidade de Tiro, Cooldown do Tiro, Limitada(True, False), Limitada a Quanto
        Torres torre1 = new Torres("Torre Amarela", 10, 50, 1, 50, 2.5, false, 0);
        Torres torre2 = new Torres("Torre Azul", 10, 50, 2, 50, 2.5, false, 0);
        Torres torre3 = new Torres("Torre Vermelha", 10, 50, 3, 50, 2.5, false, 0);
           
            
        for (int i = 0; i < 3; i++) {
            Inimigos novoTubarao = new Inimigos("Tubarão", 50, 100, 1, 50, 1, 32, 32, false);
            rodada1.enfileirar(new NosInimigos(novoTubarao));
        }
            
        for (int i = 0; i < 3; i++) {
            Inimigos novoSapinho = new Inimigos("Sapinho", 50, 100, 2, 50, 1, 32, 32, false);
            rodada2.enfileirar(new NosInimigos(novoSapinho));
        }
            
        for (int i = 0; i < 3; i++) {
            Inimigos novoCanudo  = new Inimigos("Canudo", 50, 100, 3, 50, 1, 32, 32, false);
            rodada3.enfileirar(new NosInimigos(novoCanudo));
        }
        
        for (int i = 0; i < 3; i++) {
            Inimigos novoTubarao = new Inimigos("Tubarão", 50, 100, 1, 50, 1, 32, 32, false);
            Inimigos novoSapinho = new Inimigos("Sapinho", 50, 100, 2, 50, 1, 32, 32, false);
            Inimigos novoCanudo  = new Inimigos("Canudo", 50, 100, 3, 50, 1, 32, 32, false);
            rodada4.enfileirar(new NosInimigos(novoTubarao));
            rodada4.enfileirar(new NosInimigos(novoSapinho));
            rodada4.enfileirar(new NosInimigos(novoCanudo));
        }
        
        todasAsRodadas.add(rodada1);
        todasAsRodadas.add(rodada2);
        todasAsRodadas.add(rodada3);
        todasAsRodadas.add(rodada4);
        gerenciadorHordas = new GerenciadorHordas(todasAsRodadas, mapa.getcaminho(), jogador, 60);
        
        agua = ImageUtils.loadImage("resources/images/awa.jpg");
        grama = ImageUtils.loadImage("resources/images/mato.jpg");
        arminhaAmarela = ImageUtils.loadImage("resources/images/arminha_amarela.png");
        arminhaAzul = ImageUtils.loadImage("resources/images/arminha_azul.png");
        arminhaVermelha = ImageUtils.loadImage("resources/images/arminha_vermelha.png");
        tubaraoImg = ImageUtils.loadImage("resources/images/tubarao.png");
        sapinhoImg = ImageUtils.loadImage("resources/images/sapinho.png");
        canudoImg = ImageUtils.loadImage("resources/images/canudo.png");
        
        
        
        
    }

    /**
     * Lê a entrada do usuário e atualiza o mundo do jogo.
     * Os métodos de entrada devem ser usados aqui.
     * Atenção: Você NÃO DEVE usar nenhum dos métodos de desenho da engine aqui.
     * 
     * Reads user input and update game world.
     * Input methods should be used here.
     * Warning: You MUST NOT use any of the engine drawing methods here.
     * 
     * @param delta O tempo passado, em segundos, de um quadro para o outro.
     * Time passed, in seconds, between frames.
     */
    @Override
    public void update( double delta ) {    
    
    if (isKeyPressed(KEY_SPACE) && !rodadaAtiva){
        rodadaAtiva = true;
    }
    
    if(rodadaAtiva){
        gerenciadorHordas.update();
        if (gerenciadorHordas.verificarFimDaRodada()) {
            rodadaAtiva = false;
        }
    }

    for(int i = 0; i < torres.size(); i++){
        torres.get(i).update(delta, gerenciadorHordas.getInimigosNoMapa(), tirosNoMapa);
    }
    
    for (int i = tirosNoMapa.size() - 1; i >= 0; i--) {
        Tiros t = tirosNoMapa.get(i);
        t.update(delta);
        
        if (t.atingiuAlvo) {
            tirosNoMapa.remove(t);
        }
    }
    
    if(isMouseButtonPressed(MOUSE_BUTTON_LEFT)){
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        
        int torreAmarelaX = (getScreenWidth() - getScreenWidth() / 3);
        int torreAzulX = (getScreenWidth() - getScreenWidth() / 3) + 100;
        int torreVermelhaX = (getScreenWidth() - getScreenWidth() / 3) + 200;
        
        int tamanhoTile = 64;
        int col = mouseX / tamanhoTile;
        int linha = mouseY / tamanhoTile;
        double posX = col * tamanhoTile + (tamanhoTile / 2.0);
        double posY = linha * tamanhoTile + (tamanhoTile / 2.0);
        
        if(mouseX >= torreAmarelaX && mouseX <= (torreAmarelaX + 50) && mouseY >= 100 && mouseY <= 100 + 50 ) {
            torreSelecionada = "Amarela";
        } else if(mouseX >= torreAzulX && mouseX <= (torreAzulX + 50) && mouseY >= 100 && mouseY <= 100 + 50 ) {
            torreSelecionada = "Azul";
        } else if(mouseX >= torreVermelhaX && mouseX <= (torreVermelhaX + 50) && mouseY >= 100 && mouseY <= 100 + 50 ) {
            torreSelecionada = "Vermelha";
        } else if (torreSelecionada != null && linha >= 0 && linha < mapa.getMapa().length && col >= 0 && col < mapa.getMapa()[0].length) {
            if(mapa.getMapa()[linha][col] == 0){
                switch (torreSelecionada) {
                    case "Amarela":

                        Torres novaTorre = new Torres("Torre Amarela", 10, 50, 1, 100, 0.5, false, 0);
                        if (jogador.getDinheiroJogador() >= novaTorre.getPreco()) {
                            novaTorre.setPosX(posX);
                            novaTorre.setPosY(posY);
                            torres.add(novaTorre);
                            jogador.gastarDinheiro(novaTorre.getPreco());
                            torreSelecionada = null;
                            break;
                        }
                        

                    case "Azul":
                        
                        Torres novaTorre2 = new Torres("Torre Azul", 10, 50, 2, 100, 0.5, false, 0);
                        if (jogador.getDinheiroJogador() >= novaTorre2.getPreco()) {
                            novaTorre2.setPosX(posX);
                            novaTorre2.setPosY(posY);
                            torres.add(novaTorre2);
                            jogador.gastarDinheiro(novaTorre2.getPreco());
                            torreSelecionada = null;
                        break;
                        }
                        
                        
                    case "Vermelha":
                        
                        
                        
                        Torres novaTorre3 = new Torres("Torre Vermelha", 10, 50, 3, 100, 0.5, false, 0);
                        if (jogador.getDinheiroJogador() >= novaTorre3.getPreco()) {
                            novaTorre3.setPosX(posX);
                            novaTorre3.setPosY(posY);
                            torres.add(novaTorre3);
                            jogador.gastarDinheiro(novaTorre3.getPreco());
                            torreSelecionada = null;
                            break;    
                        }
                        
                        
                }
            }
        }
    }
    
    
    }
    
    /**
     * Desenha o mundo do jogo.
     * Todas as operações de desenho DEVEM ser feitas aqui.
     * 
     * Draws the game world.
     * All drawing related operations MUST be performed here.
     */
    private void desenharMapa(){
        int[][] matriz = mapa.getMapa();
        int tamanhoTile = 64;
        
        
        for(int linha = 0; linha < matriz.length; linha++){
           for(int col = 0; col < matriz[linha].length; col++){
               int posX = col * tamanhoTile;
               int posY = linha * tamanhoTile;
               
               Rectangle tiles = new Rectangle(posX, posY, tamanhoTile, tamanhoTile);
               
               if(matriz[linha][col] == 0){
                   if (grama != null) {
                       //drawImage(grama, posX, posY);
                       grama.draw(this, tiles, posX, posY);
                   } else {
                       
                   }
                   
                   //fillRectangle(posX, posY, tamanhoTile, tamanhoTile, 0, 0, 0, GREEN);
               }
               else if(matriz[linha][col] == 1){
                   if (agua != null) {
                       //agua.draw(this, tiles, posX, posY);
                       drawImage(agua, posX, posY);
                   }
                   
               }
           }
        }
    }
    
    private void desenharTorres(){
        for(Torres tor : torres){
            int centroX = (int) tor.getPosX();
            int centroY = (int) tor.getPosY();
            int raio = 16;
            int tamanho = raio * 2;
            
            int imgX = centroX - (arminhaAzul.getWidth() / 2);
            int imgY = centroY - (arminhaAzul.getHeight() / 2);
            double pivotX = arminhaAzul.getWidth() / 2.0;
            double pivotY = arminhaAzul.getHeight() / 2.0;
            
            drawCircle(centroX, centroY, 100, RED);
            
            switch (tor.getCorTorre()) {
                case 1: 
                    if (arminhaAmarela != null) {
                        arminhaAmarela.draw(this, imgX, imgY, pivotX, pivotY, Math.toDegrees(tor.getAngulo()));
                        break;
                    }
                case 2: 
                    if (arminhaAzul != null) {
                        arminhaAzul.draw(this, imgX, imgY, pivotX, pivotY, Math.toDegrees(tor.getAngulo()));
                        break;
                    }
                case 3:
                    if (arminhaVermelha != null) {
                        arminhaVermelha.draw(this, imgX, imgY, pivotX, pivotY, Math.toDegrees(tor.getAngulo()));
                        break;
                    }
                default: {
                }
            }      
        }
    }
    private void desenharInimigos(){
        for(Inimigos ini : gerenciadorHordas.getInimigosNoMapa()){
            int centroX = (int) ini.getPosXIni();
            int centroY = (int) ini.getPosYIni();
            int raio = 16;
            int tamanho = raio * 2;
            
            int imgX = centroX - (arminhaAzul.getWidth() / 2);
            int imgY = centroY - (arminhaAzul.getHeight() / 2);
            double pivotX = arminhaAzul.getWidth() / 2.0;
            double pivotY = arminhaAzul.getHeight() / 2.0;
            double rotacaoFinal = ini.getAngulo();
            
            switch (ini.getCorIni()) {
                case 1:
                    if (tubaraoImg != null) {
                        tubaraoImg.draw(this, imgX, imgY, pivotX, pivotY, rotacaoFinal);
                        break;
                    }
                case 2: 
                    if (sapinhoImg != null) {
                        sapinhoImg.draw(this, imgX, imgY);
                        break;
                    }
                case 3: 
                    if (canudoImg != null) {
                        canudoImg.draw(this, imgX, imgY);
                        break;
                    }
                default: {
                }
            }
        }
    }
    
    
    private void desenharHUD(){
        drawText("Vida: " + jogador.getVidaJogador(), getScreenWidth() - 280, 12, PINK);
        drawText("Ouro: " + jogador.getDinheiroJogador(), getScreenWidth() - 280, 24, PINK);
        
        if (!rodadaAtiva) {
            drawText("Pressione ESPAÇO para iniciar a rodada", getScreenWidth() * 8/12 , getScreenHeight() - 25, PINK);
        }
        
        int baseX = getScreenWidth() - (getScreenWidth() / 3);
        int baseY = 100;
        int tamanhoCaixa = 50;
        
        drawRectangle(baseX, baseY, 50, 50, BLACK);
        drawRectangle(baseX + 100, baseY, 50, 50, BLACK);
        drawRectangle(baseX + 200, baseY, 50, 50, BLACK);
        
        int tamanhoImg = 40;
        int margem = 5;
                
        if (arminhaAmarela != null) {
            Rectangle origemAmarela = new Rectangle(0, 0, arminhaAmarela.getWidth(), arminhaAmarela.getHeight());
            Rectangle destinoAmarela = new Rectangle(baseX + margem, baseY + margem, tamanhoImg, tamanhoImg);            
            arminhaAmarela.draw(this, origemAmarela, destinoAmarela);
        }
        
        if (arminhaAzul != null) {
            Rectangle origemAzul = new Rectangle(0, 0, arminhaAzul.getWidth(), arminhaAzul.getHeight());
            Rectangle destinoAzul = new Rectangle(baseX + 100 + margem, baseY + margem, tamanhoImg, tamanhoImg);
            arminhaAzul.draw(this, origemAzul, destinoAzul);
        }
        
        if (arminhaVermelha != null) {
            Rectangle origemVermelha = new Rectangle(0, 0, arminhaVermelha.getWidth(), arminhaVermelha.getHeight());
            Rectangle destinoVermelha = new Rectangle(baseX + 200 + margem, baseY + margem, tamanhoImg, tamanhoImg);
            arminhaVermelha.draw(this, origemVermelha, destinoVermelha);
        }
        
        java.awt.Color corSelecionada = new java.awt.Color(128, 128, 128, 150);
        
        if (torreSelecionada != null) {
            switch (torreSelecionada) {
                case "Amarela":
                    fillRectangle(baseX, baseY, tamanhoCaixa, tamanhoCaixa, corSelecionada);                    
                    drawRectangle(baseX, baseY, tamanhoCaixa, tamanhoCaixa, YELLOW); 
                    break;
                case "Azul":
                    fillRectangle(baseX + 100, baseY, tamanhoCaixa, tamanhoCaixa, corSelecionada);
                    drawRectangle(baseX + 100, baseY, tamanhoCaixa, tamanhoCaixa, BLUE);
                    break;
                case "Vermelha":
                    fillRectangle(baseX + 200, baseY, tamanhoCaixa, tamanhoCaixa, corSelecionada);
                    drawRectangle(baseX + 200, baseY, tamanhoCaixa, tamanhoCaixa, RED);
                    break;
            }
        }
        
        /*drawRectangle(getScreenWidth() - (getScreenWidth() / 3), 200, 50, 50, BLACK);
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3) + 100, 200, 50, 50, BLACK);
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3) + 200, 200, 50, 50, BLACK);
        
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3), 300, 50, 50, BLACK);
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3) + 100, 300, 50, 50, BLACK);
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3) + 200, 300, 50, 50, BLACK);
        
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3), 400, 50, 50, BLACK);
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3) + 100, 400, 50, 50, BLACK);
        drawRectangle(getScreenWidth() - (getScreenWidth() / 3) + 200, 400, 50, 50, BLACK);
        */
        
        
        
        
        FilaInimigos filaAtual = gerenciadorHordas.getFilaRodadaAtual();
        
        if (filaAtual != null) {
            drawText("Próximos:", getScreenWidth() - 280, 200, 30, PINK);
            NosInimigos atualNos = filaAtual.getCabecaFila();
            int xFila = getScreenWidth() - 260;
            int yFila = 250;

            while (atualNos != null) {
                Inimigos ini = atualNos.getInimigo();
                
                if (xFila > getScreenWidth() - 30) {
                    xFila = getScreenWidth() - 260;
                    yFila += 60;
                }
                
                int tamanhoIcone = 40;
                
                Paint corInimigo = ORANGE;
                switch (ini.getCorIni()) {
                    case 1:
                        if (tubaraoImg != null) {
                            Rectangle origem = new Rectangle(0, 0, tubaraoImg.getWidth(), tubaraoImg.getHeight());
                            Rectangle destino = new Rectangle(xFila, yFila, tamanhoIcone, tamanhoIcone);
                            tubaraoImg.draw(this, origem, destino);
                        }
                        break;
                    case 2:
                        if (sapinhoImg != null) {
                            Rectangle origem = new Rectangle(0, 0, sapinhoImg.getWidth(), sapinhoImg.getHeight());
                            Rectangle destino = new Rectangle(xFila, yFila, tamanhoIcone, tamanhoIcone);
                            sapinhoImg.draw(this, origem, destino);
                        }
                        break;
                    case 3:
                        if (canudoImg != null) {
                            Rectangle origem = new Rectangle(0, 0, canudoImg.getWidth(), canudoImg.getHeight());
                            Rectangle destino = new Rectangle(xFila, yFila, tamanhoIcone, tamanhoIcone);
                            canudoImg.draw(this, origem, destino);
                        }
                        break;
                }
            
                xFila += 50;
                atualNos = atualNos.getProximo();
            }
        }
    }
    
    public void desenharTiros() {
        for (Tiros t : tirosNoMapa) {
            fillCircle((int) t.getX(), (int) t.getY(), 5, BLUE);
        }
    }
    

    
    @Override
    
    public void draw() {
        clearBackground( WHITE );
        
        desenharMapa();
        desenharInimigos();
        desenharTorres();
        desenharHUD();
        desenharTiros();  
        
        FilaInimigos filaAtual = gerenciadorHordas.getFilaRodadaAtual();
        
        if(jogador.estaMorto()){
            
            fillRectangle(0, 0, getScreenWidth(), getScreenHeight(), BLACK);
            drawText("GAME OVER", getScreenWidth() / 2 - 135 , getScreenHeight() / 2 - 30, 50, PINK);   
        }
      
        if (filaAtual == null) {
            
            fillRectangle(0, 0, getScreenWidth(), getScreenHeight(), BLACK);
            drawText("Vitória", getScreenWidth() / 2 - 110, getScreenHeight() / 2 - 30, 50, PINK);          
        }
        
        
        while (atual != null){
    
            atual = atual.getProximo();
        }
    }
    
    /**
     * Instancia a engine e a inicia.
     * 
     * Instantiates the engine and starts it.
     */
    public static void main( String[] args ) {
        new Main();
    }
    
}

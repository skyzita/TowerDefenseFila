package ESDD;

/**
 *
 * @author soleile
 */

import template.Inimigos;

public class NosInimigos{
    
    private Inimigos inimigo;
    private NosInimigos proximo;

    public NosInimigos(Inimigos inimigo) {
        this.inimigo = inimigo;
        this.proximo = null;
    }

    public Inimigos getInimigo() {
        return inimigo;
    }

    public void setInimigo(Inimigos inimigo) {
        this.inimigo = inimigo;
    }

    public NosInimigos getProximo() {
        return proximo;
    }

    public void setProximo(NosInimigos proximo) {
        this.proximo = proximo;
    }
    
}

package cl.ucn.ei.pa.sistemastarkon.dominio;

public class NodoEntrega {
    
    private Entrega entrega;
    private NodoEntrega next;
    private NodoEntrega previo;

    public NodoEntrega(Entrega entrega){
        this.entrega = entrega;
        next = null;
        previo = null;
        
    }

    public Entrega getEntrega() {
        return this.entrega;
    }

    public NodoEntrega getNext() {
        return this.next;
    }

    public void setNext(NodoEntrega next) {
        this.next = next;
    }

    public NodoEntrega getPrevio() {
        return this.previo;
    }

    public void setPrevio(NodoEntrega previo) {
        this.previo = previo;
    }
    
}

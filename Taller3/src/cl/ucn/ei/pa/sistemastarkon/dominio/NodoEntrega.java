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

    
    /** 
     * @return Entrega
     */
    public Entrega getEntrega() {
        return this.entrega;
    }

    
    /** 
     * @return NodoEntrega
     */
    public NodoEntrega getNext() {
        return this.next;
    }

    
    /** 
     * @param next
     */
    public void setNext(NodoEntrega next) {
        this.next = next;
    }

    
    /** 
     * @return NodoEntrega
     */
    public NodoEntrega getPrevio() {
        return this.previo;
    }

    
    /** 
     * @param previo
     */
    public void setPrevio(NodoEntrega previo) {
        this.previo = previo;
    }
    
}

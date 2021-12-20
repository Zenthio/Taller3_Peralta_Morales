package cl.ucn.ei.pa.sistemastarkon.logica;

import cl.ucn.ei.pa.sistemastarkon.dominio.*;

public class ListaEntrega {
    
    private NodoEntrega first;
    private NodoEntrega last;

    public ListaEntrega(){
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public boolean insertarPrimer(Entrega e){ 
        NodoEntrega nuevoNodo = new NodoEntrega(e);
        if (isEmpty()){
            last = nuevoNodo;
        } else {
            first.setPrevio(nuevoNodo);
        }
        nuevoNodo.setNext(first);
        first = nuevoNodo;
        return true;
    }

    public boolean insertarUltimo(Entrega e){
        NodoEntrega nuevoNodo = new NodoEntrega(e);
        if (isEmpty()){
            first = nuevoNodo;
        } else {
            last.setNext(nuevoNodo);
            nuevoNodo.setPrevio(last);
        }
        last = nuevoNodo;
        return true;
    }

    public boolean eliminarClave(int codigo){
        NodoEntrega current = first;
        while(current != null && current.getEntrega().getCodigo() != codigo){
            current = current.getNext();
        }
        if (current == null){
            return false;
        }
        
        if (current == first){
            first = current.getNext();
        } else {
            current.getPrevio().setNext(current.getNext());
        }
        
        if (current == last){
            last = current.getPrevio();
        } else {
            current.getNext().setPrevio(current.getPrevio());
        }
        return true;

    }
}

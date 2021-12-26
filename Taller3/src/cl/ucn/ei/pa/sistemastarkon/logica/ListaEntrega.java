package cl.ucn.ei.pa.sistemastarkon.logica;

import cl.ucn.ei.pa.sistemastarkon.dominio.*;

public class ListaEntrega {
    
    private NodoEntrega first;
    private int size;

    public ListaEntrega(){
        first = null;
        size = 0;
    }

    public NodoEntrega getFirst() {
        return this.first;
    }

    public void setFirst(NodoEntrega first) {
        this.first = first;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty(){
        return first==null;
    }
    /*
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
    
    public boolean eliminarClaveCircular(int codigo){
        NodoEntrega actual = first;
        NodoEntrega anterior = last;
    do{
        if (actual.getEntrega().getCodigo() == codigo){
            if (actual == first){
                first = first.getNext();
                last.setNext(first);
                first.setPrevio(last);
                return true;
            } else if(actual == last){
                last = anterior;
                first.setPrevio(last);
                last.setNext(first);
                return true;
            } else{
                anterior.setNext(actual.getNext());
                actual.getNext().setPrevio(anterior);
                return true;
            }
        }
        anterior = actual;
        actual = actual.getNext();
    } while (actual != first);
        return false;
    }
    /*
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
    */
    public boolean ingresarNodo(Entrega e){
        NodoEntrega nuevoNodo = new NodoEntrega(e);
        if (isEmpty()){
            first = nuevoNodo;
            first.setNext(first);
            first.setPrevio(first);
            size++;
        } else {
            NodoEntrega current = first.getPrevio();
            nuevoNodo.setPrevio(current);
            current.setNext(nuevoNodo);
            first.setPrevio(nuevoNodo);
            nuevoNodo.setNext(first);
            size++;
        }
        return true;
    }

    public Entrega buscarNodoR(String rut){
        NodoEntrega current = first;
        do{
            if (current.getEntrega().getRutRemitente().equals(rut) || current.getEntrega().getRutDestinatario().equals(rut)){
                return current.getEntrega();
            }
            current = current.getNext();
        } while (current != first);
        return null;

    }

    public Entrega buscarNodoC(int codigo){
        NodoEntrega current = first;
        
        do{
            if (current.getEntrega().getCodigo() == codigo){
                return current.getEntrega();
            }
            current = current.getNext();
        } while (current != first);
        return null;

    }

    public int buscarNodoI(int codigo){
        NodoEntrega current = first;
        int pos = 0;
        do{
            if (current.getEntrega().getCodigo() == codigo){
                return pos;
            }
            pos++;
            current = current.getNext();
        } while (current != first);
        return -1;
    }

    public Entrega buscarINodo(int pos){
        NodoEntrega current = first;
        int cont = 0;
    do{
        if (cont == pos){
            return current.getEntrega();
        }
        current = current.getNext();
        cont++;
    } while (current != first);
        return null;
    }

    public String entregas(){
        NodoEntrega current = first;
        String retorno = "";
        if (!isEmpty()){
            do{
                System.out.println(current.getEntrega().getCodigo());
                Entrega e = current.getEntrega();
                retorno += e.toString()+"\n";
                current = current.getNext();
            } while (current != first);
        } else {
            retorno = "\nNo existen entregas en esta lista para esta persona\n\n";
        }
        return retorno;
    }

    public String entregasTipo(){
        NodoEntrega current = first;
        String retorno = "";
        do{
            retorno += current.getEntrega().toStringHija();
            current = current.getNext();
        } while (current != first);
        return retorno;
    }

    /*
    COMO RECORRER UNA LISTA DOBLE NEXO CIRCULAR DESDE EL ULTIMO AL PRIMERO

    do{
        if (actual.getEntrega().getCodigo() == codigo){
            encontrado = true
        }
        current = current.getPrevio();
    } while (current != last);

    COMO BUSCAR UN NODO DADO UNA CLAVE RECORRIENDO DESDE EL ULTIMO AL PRIMERO
    Nodo current = last;
    boolean encontrado = false;
    do{
        if (actual.getEntrega().getCodigo() == codigo){
            encontrado = true
        }
        current = current.getPrevio();
    } while (current != last);

    if (encontrado){
        sysout("Nodo encontrado");
    } else {
        sysout("Nodo no encontrado");
    }

    COMO RETORNAR UN NODO DADO UNA CLAVE RECORRIENDO DESDE EL ULTIMO AL PRIMERO
    Nodo current = last;
    boolean encontrado = false;
    do{
        if (current.getEntrega().getCodigo() == codigo){
            encontrado = true
            break;
        }
        current = current.getPrevio();
    } while (current != last);

    if (encontrado){
        return current;
    } else {
        return null;
    }


    COMO RECORRER UNA LISTA DOBLE NEXO CIRCULAR DESDE EL PRIMERO AL ULTIMO
    Nodo current = first;
    do{
        if (actual.getEntrega().getCodigo() == codigo){
            encontrado = true
        }
        current = current.getNext();
    } while (current != first);


    COMO ELIMINAR UN NODO EN UNA LISTA DOBLE NEXO CIRCULAR 
    nodo actual = PRIMERO
    nodo anterior = ultimo
    do{
        if (actual.getEntrega().getCodigo() == x){
            if (actual == primero){
                primero = primero.getNext()
                ultimo.setNext(primero);
                primero.setPrevio(ultimo);
            } else if(actual == ultimo){
                ultimo == anterior;
                primero.setPrevio(ultimo);
                ultimo.setNext(primero);
            } else{
                anterior.setNext(actual.getNext());
                actual.getNext().setPrevio(anterior);
            }
        }
        anterior = actual;
        actual = actual.siguiente;
    } while (actual != primero)
    */
    
}

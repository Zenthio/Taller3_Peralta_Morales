package cl.ucn.ei.pa.sistemastarkon.logica;

import cl.ucn.ei.pa.sistemastarkon.dominio.*;
import java.util.LinkedList;

public class ListaCliente {
    
    private LinkedList<Cliente> listaCliente;

    public ListaCliente() {
        listaCliente = new LinkedList<Cliente>();
    }

    public LinkedList<Cliente> getListaCliente() {
        return this.listaCliente;
    }

    public void setListaCliente(LinkedList<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public boolean ingresarCliente(Cliente cliente){
        boolean retorno = false;
        if (listaCliente.add(cliente)){
            retorno = true;
        }
        return retorno;
    }
}

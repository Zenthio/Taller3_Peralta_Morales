package cl.ucn.ei.pa.sistemastarkon.logica;

import java.util.ArrayList;
import cl.ucn.ei.pa.sistemastarkon.dominio.*;

public class ListaLocalizacion {

    private ArrayList<Localizacion> listaLocalizacion;
    
    public ListaLocalizacion(){
        this.listaLocalizacion = new ArrayList<Localizacion>();
    }


    public ArrayList<Localizacion> getListaLocalizacion() {
        return this.listaLocalizacion;
    }

    public void setListaLocalizacion(ArrayList<Localizacion> listaLocalizacion) {
        this.listaLocalizacion = listaLocalizacion;
    }

    public boolean ingresarLocalizacion(Localizacion local){
        boolean retorno = false;
        if (listaLocalizacion.add(local)){
            retorno = true;
        }
        return retorno;
    }


}

package cl.ucn.ei.pa.sistemastarkon.dominio;
import cl.ucn.ei.pa.sistemastarkon.logica.*;

public class Localizacion{

    private String nombre;
    private double ganancia;
    private ListaEntrega entregasEnviadas;
    private ListaEntrega entregasRecibidas;

    public Localizacion(String nombre){
        ganancia = 0;
        this.nombre = nombre;
        entregasEnviadas = new ListaEntrega();
        entregasRecibidas = new ListaEntrega();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGanancia(){
        return this.ganancia;
    }

    public void setGanancia(double ganancia){
        this.ganancia = ganancia;
    }

    public String entregasLocalizacion(){
        String retorno = "La oficina en "+getNombre()+ " realizó "+entregasEnviadas.getSize()+" envíos y recibió "+entregasRecibidas.getSize()+" envios\n";
        return retorno;
    }

    public ListaEntrega getEntregasEnviadas() {
        return this.entregasEnviadas;
    }

    public void setEntregasEnviadas(ListaEntrega entregasEnviadas) {
        this.entregasEnviadas = entregasEnviadas;
    }

    public ListaEntrega getEntregasRecibidas() {
        return this.entregasRecibidas;
    }

    public void setEntregasRecibidas(ListaEntrega entregasRecibidas) {
        this.entregasRecibidas = entregasRecibidas;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
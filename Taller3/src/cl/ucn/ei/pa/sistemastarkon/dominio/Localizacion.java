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

    
    /** 
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @return double
     */
    public double getGanancia(){
        return this.ganancia;
    }

    
    /** 
     * @param ganancia
     */
    public void setGanancia(double ganancia){
        this.ganancia = ganancia;
    }

    
    /** 
     * @return String
     */
    public String entregasLocalizacion(){
        String retorno = "La oficina en "+getNombre()+ " realizó "+entregasEnviadas.getSize()+" envíos y recibió "+entregasRecibidas.getSize()+" envios\n";
        return retorno;
    }

    
    /** 
     * @return ListaEntrega
     */
    public ListaEntrega getEntregasEnviadas() {
        return this.entregasEnviadas;
    }

    
    /** 
     * @param entregasEnviadas
     */
    public void setEntregasEnviadas(ListaEntrega entregasEnviadas) {
        this.entregasEnviadas = entregasEnviadas;
    }

    
    /** 
     * @return ListaEntrega
     */
    public ListaEntrega getEntregasRecibidas() {
        return this.entregasRecibidas;
    }

    
    /** 
     * @param entregasRecibidas
     */
    public void setEntregasRecibidas(ListaEntrega entregasRecibidas) {
        this.entregasRecibidas = entregasRecibidas;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return getNombre();
    }
}
package cl.ucn.ei.pa.sistemastarkon.logica;

import cl.ucn.ei.pa.sistemastarkon.dominio.*;

public interface SistemaStarkon {
    
    //RF1

    public boolean ingresarLocalizacion(String nombre);
    
    public boolean ingresarCliente(String rut, String nombre, String apellido, int saldo, String ciudad);
    
    public boolean ingresarEntregaD(int codigo, String rutR, String rutD, int peso, int grosor);
    
    public boolean ingresarEntregaE(int codigo, String rutR, String rutD, int peso, int largo, int ancho, int prof);
    
    public boolean ingresarEntregaV(int codigo, String rutR, String rutD, String material, int peso);
   
   
    //RF2 y 5

    public boolean iniciarSesion();

    public boolean registrarCuenta();
    
    
    //RF3

    public void realizarEntrega();

    public boolean EntregaD();

    public boolean EntregaE();

    public boolean EntregaV();

    public boolean pagarEntrega(Entrega e);

    public boolean recargarSaldo();

    public String verEntregas();


    //RF4

    public String entregasTipo();

    public String entregasLocalizacion();

    public String entregasCliente();
    
    public String registroGanancias();

    public int getPrivilegio();

    public String obtenerDatosCliente();

    public String obtenerDatosLocalizacion();

    public String obtenerDatosEntrega();

}

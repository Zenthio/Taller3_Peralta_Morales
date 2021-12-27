package cl.ucn.ei.pa.sistemastarkon.logica;

import cl.ucn.ei.pa.sistemastarkon.dominio.*;

public interface SistemaStarkon {
    
    //RF1

    public void ingresarLocalizacion(String nombre);
    
    public void ingresarCliente(String rut, String nombre, String apellido, double saldo, String ciudad);
    
    public void ingresarEntregaD(int codigo, String rutR, String rutD, double peso, double grosor);
    
    public void ingresarEntregaE(int codigo, String rutR, String rutD, double peso, double largo, double ancho, double prof);
    
    public void ingresarEntregaV(int codigo, String rutR, String rutD, String material, double peso);
   
   
    //RF2 y 5

    public boolean iniciarSesion();

    public boolean registrarCuenta();
    
    
    //RF3

    public void realizarEntrega();

    public boolean EntregaD();

    public boolean EntregaE();

    public boolean EntregaV();

    public boolean pagarEntrega(Entrega e, Cliente destinatario);

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

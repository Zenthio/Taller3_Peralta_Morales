package cl.ucn.ei.pa.sistemastarkon.dominio;

import cl.ucn.ei.pa.sistemastarkon.logica.*;
import cl.ucn.ei.pa.sistemastarkon.utils.*;

public class Cliente {
    private String rut;
    private String nombre;
    private String apellido;
    private int saldo;
    private String ciudad;
    private ListaEntrega entregasRecibidas;
    private ListaEntrega entregasEnviadas; 

    public Cliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.ciudad = ciudad;
        entregasRecibidas = new ListaEntrega();
        entregasEnviadas = new ListaEntrega();
    }

    public ListaEntrega getEntregasRecibidas() {
        return this.entregasRecibidas;
    }

    public void setEntregasRecibidas(ListaEntrega entregasRecibidas) {
        this.entregasRecibidas = entregasRecibidas;
    }

    public ListaEntrega getEntregasEnviadas() {
        return this.entregasEnviadas;
    }

    public void setEntregasEnviadas(ListaEntrega entregasEnviadas) {
        this.entregasEnviadas = entregasEnviadas;
    }

    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return RutUtility.formatearRut(getRut())+","+getNombre()+","+getApellido()+","+getSaldo()+","+getCiudad();
    }

}

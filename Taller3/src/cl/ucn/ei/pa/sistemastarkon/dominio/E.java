package cl.ucn.ei.pa.sistemastarkon.dominio;

import cl.ucn.ei.pa.sistemastarkon.utils.*;

public class E extends Entrega {

    private double peso;
    private double largo;
    private double ancho;
    private double prof;

    public E(int codigo, String rutRemitente, String rutDestinatario, double peso, double largo, double ancho, double prof){
        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.prof = prof;
        calcularValor();
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getLargo() {
        return this.largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return this.ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProf() {
        return this.prof;
    }

    public void setProf(double prof) {
        this.prof = prof;
    }

    @Override
    public double calcularValor() {
        this.valor = (peso/1000)*(largo/10)*(ancho/10)*(prof/10)*50;
        return this.valor;
    }

    public String toStringHija(){
        return "Codigo: "+getCodigo()+" | Valor: "+getValor()+" | Tipo: Encomienda\n";
    }
   
    @Override
    public String toStringLectura() {
        int pesoL = (int) Math.round(peso);
        int largoL = (int) Math.round(largo);
        int anchoL = (int) Math.round(ancho);
        int profL = (int) Math.round(prof);
        return getCodigo()+",E,"+RutUtility.formatearRut(getRutRemitente().toUpperCase())+","+RutUtility.formatearRut(getRutDestinatario().toUpperCase())+","+pesoL+","+largoL+","+anchoL+","+profL;
    }
}
package cl.ucn.ei.pa.sistemastarkon.dominio;

import cl.ucn.ei.pa.sistemastarkon.utils.*;

public class E extends Entrega {

    private int peso;
    private double pesoKg;
    private int largo;
    private double largoCm;
    private int ancho;
    private double anchoCm;
    private int prof;
    private double profCm;

    public E(int codigo, String rutRemitente, String rutDestinatario, int peso, int largo, int ancho, int prof){
        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.pesoKg = (peso/1000);
        this.largo = largo;
        this.largoCm = (largo/10);
        this.ancho = ancho;
        this.anchoCm = (ancho/10);
        this.prof = prof;
        this.profCm = (prof/10);
        calcularValor();
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getLargo() {
        return this.largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getProf() {
        return this.prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    @Override
    public double calcularValor() {
        this.valor = (pesoKg)*(largoCm)*(anchoCm)*(profCm)*50;
        return this.valor;
    }

    public String toStringHija(){
        return "Codigo: "+getCodigo()+" | Valor: "+getValor()+" | Tipo: Encomienda\n";
    }
   
    @Override
    public String toStringLectura() {
        return getCodigo()+",E,"+RutUtility.formatearRut(getRutRemitente().toUpperCase())+","+RutUtility.formatearRut(getRutDestinatario().toUpperCase())+","+getPeso()+","+getLargo()+","+getAncho()+","+getProf();
    }
}
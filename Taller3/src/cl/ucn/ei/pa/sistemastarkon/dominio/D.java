package cl.ucn.ei.pa.sistemastarkon.dominio;

import cl.ucn.ei.pa.sistemastarkon.utils.*;

public class D extends Entrega{
    
    private double peso;
    private double grosor;

    public D(int codigo, String rutRemitente, String rutDestinatario, double peso, double grosor){
        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.grosor = grosor;
        calcularValor();
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getGrosor() {
        return this.grosor;
    }

    public void setGrosor(double grosor) {
        this.grosor = grosor;
    }

    @Override
    public double calcularValor() {
        this.valor = (peso/1000)*(grosor/10)*100;
        return valor;
    }

    @Override
    public String toStringHija(){
        return "Codigo: "+getCodigo()+" | Valor: "+getValor()+" | Tipo: Documento\n";
    }

    @Override
    public String toStringLectura() {
        int pesoL = (int) Math.round(peso);
        int grosorL = (int) Math.round(grosor);
        return getCodigo()+",D,"+RutUtility.formatearRut(getRutRemitente().toUpperCase())+","+RutUtility.formatearRut(getRutDestinatario().toUpperCase())+","+pesoL+","+grosorL;
    }
}

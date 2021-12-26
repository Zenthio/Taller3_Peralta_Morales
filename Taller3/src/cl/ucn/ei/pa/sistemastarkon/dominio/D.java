package cl.ucn.ei.pa.sistemastarkon.dominio;

import cl.ucn.ei.pa.sistemastarkon.utils.*;

public class D extends Entrega{
    
    private int peso;
    private double pesoKg;
    private int grosor;
    private double grosorCm;

    public D(int codigo, String rutRemitente, String rutDestinatario, int peso, int grosor){
        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.pesoKg = (peso/1000);
        this.grosor = grosor;
        this.grosorCm = (grosor/10);
        calcularValor();
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getGrosor() {
        return this.grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    @Override
    public double calcularValor() {
        this.valor = (pesoKg)*(grosorCm)*100;
        return valor;
    }

    @Override
    public String toStringHija(){
        return "Codigo: "+getCodigo()+" | Valor: "+getValor()+" | Tipo: Documento\n";
    }

    @Override
    public String toStringLectura() {
        return getCodigo()+",D,"+RutUtility.formatearRut(getRutRemitente().toUpperCase())+","+RutUtility.formatearRut(getRutDestinatario().toUpperCase())+","+getPeso()+","+getGrosor();
    }
}

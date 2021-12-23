package cl.ucn.ei.pa.sistemastarkon.dominio;

public class D extends Entrega{
    
    private double peso;
    private double grosor;

    public D(int codigo, String rutRemitente, String rutDestinatario, double peso, double grosor){
        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.grosor = grosor;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getGrosor() {
        return this.grosor;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    @Override
    public double calcularValor() {
        this.valor = (peso/1000)*grosor*100;
        return valor;
    }

    @Override
    public String toStringHija(){
        return " Valor: "+getValor()+"\n Tipo: Documento";
    }

}

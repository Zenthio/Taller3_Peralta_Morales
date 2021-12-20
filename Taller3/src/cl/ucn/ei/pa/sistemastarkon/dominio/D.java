package cl.ucn.ei.pa.sistemastarkon.dominio;

public class D extends Entrega{
    
    private int peso;
    private int grosor;

    public D(int codigo, String rutRemitente, String rutDestinatario, int peso, int grosor) {

        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.grosor = grosor;
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
        this.valor = (peso/1000)*grosor*100;
        return valor;
    }

}

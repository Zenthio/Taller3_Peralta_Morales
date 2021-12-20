package cl.ucn.ei.pa.sistemastarkon.dominio;

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
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getLargo() {
        return this.largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public double getProf() {
        return this.prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    @Override
    public double calcularValor() {
        this.valor = (peso/1000)*(largo*ancho*prof)*50;
        return this.valor;
    }
   
}

package cl.ucn.ei.pa.sistemastarkon.dominio;

public class E extends Entrega {

    private int peso;
    private int largo;
    private int ancho;
    private int prof;

    public E(int codigo, String rutRemitente, String rutDestinatario, int peso, int largo, int ancho, int prof){
        super(codigo, rutRemitente, rutDestinatario);
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.prof = prof;
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
        this.valor = (peso/1000)*(largo*ancho*prof)*50;
        return this.valor;
    }

    public String toStringHija(){
        return " Valor: "+getValor()+"\n Tipo: Encomienda";
    }
   
    @Override
    public String toStringLectura() {
        return getCodigo()+",E,"+getRutRemitente()+","+getRutDestinatario()+","+getPeso()+","+getLargo()+","+getAncho()+","+getProf();
    }
}
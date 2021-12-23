package cl.ucn.ei.pa.sistemastarkon.dominio;

public abstract class Entrega {
    
    private int codigo;
    //TIPO DE ENTREGA IRIA AQUI
    private String rutRemitente;
    private String rutDestinatario;
    protected double valor;

    protected Entrega(int codigo, String rutRemitente, String rutDestinatario){
        this.codigo = codigo;
        this.rutRemitente = rutRemitente;
        this.rutDestinatario = rutDestinatario;
        this.valor = 0;
    }

    public abstract double calcularValor();

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getRutRemitente() {
        return this.rutRemitente;
    }

    public void setRutRemitente(String rutRemitente) {
        this.rutRemitente = rutRemitente;
    }

    public String getRutDestinatario() {
        return this.rutDestinatario;
    }

    public void setRutDestinatario(String rutDestinatario) {
        this.rutDestinatario = rutDestinatario;
    }

    public double getValor() {
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public abstract String toStringHija();

    @Override
    public String toString() {
        return " Código: "+getCodigo()+"\n Rut Remitente: "+getRutRemitente()+"\n Rut Destinatario: "+getRutDestinatario()+"\n Valor: "+getValor();
    }
    
}

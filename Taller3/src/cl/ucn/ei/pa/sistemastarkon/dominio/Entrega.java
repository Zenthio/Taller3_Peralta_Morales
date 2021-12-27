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
    }

    
    /** 
     * @param getCodigo(
     * @return double
     */
    public abstract double calcularValor();

    
    /** 
     * @return int
     */
    public int getCodigo() {
        return this.codigo;
    }

    
    /** 
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    /** 
     * @return String
     */
    public String getRutRemitente() {
        return this.rutRemitente;
    }

    
    /** 
     * @param rutRemitente
     */
    public void setRutRemitente(String rutRemitente) {
        this.rutRemitente = rutRemitente;
    }

    
    /** 
     * @return String
     */
    public String getRutDestinatario() {
        return this.rutDestinatario;
    }

    
    /** 
     * @param rutDestinatario
     */
    public void setRutDestinatario(String rutDestinatario) {
        this.rutDestinatario = rutDestinatario;
    }

    
    /** 
     * @return double
     */
    public double getValor() {
        return this.valor;
    }

    
    /** 
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    
    /** 
     * @param toString(
     * @return String
     */
    public abstract String toStringHija();

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return " Código: "+getCodigo()+"\n Rut Remitente: "+getRutRemitente()+"\n Rut Destinatario: "+getRutDestinatario()+"\n Valor: "+getValor()+"\n";
    }
    
    public abstract String toStringLectura();
}

package cl.ucn.ei.pa.sistemastarkon.dominio;

import cl.ucn.ei.pa.sistemastarkon.utils.RutUtility;

public class V extends Entrega {

    private String material;
    private double peso;

    public V(int codigo, String rutRemitente, String rutDestinatario, String material, double peso) {
        super(codigo, rutRemitente, rutDestinatario);
        this.material = material;
        this.peso = peso;
        calcularValor();
        
    }

    
    /** 
     * @return String
     */
    public String getMaterial() {
        return this.material;
    }

    
    /** 
     * @param material
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    
    /** 
     * @return double
     */
    public double getPeso() {
        return this.peso;
    }

    
    /** 
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    
    /** 
     * @return double
     */
    @Override
    public double calcularValor() {
        if (material.equals("Cuero")){
            this.valor = 200*(peso/1000)*150;
        } else {
            if (material.equals("Plastico")){
                this.valor = 150*(peso/1000)*150;
            } else {
                if (material.equals("Tela")){
                    this.valor = 100*(peso/1000)*150;
                }
            }
        }
        return this.valor;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toStringHija() {
        return "Codigo: "+getCodigo()+" | Valor: "+getValor()+" | Tipo: Valija\n";
    }
 
    
    /** 
     * @return String
     */
    @Override
    public String toStringLectura() {
        String material = getMaterial();
        String primeraletra = material.substring(0,1);
        String resto = material.substring(1,getMaterial().length());
        primeraletra = primeraletra.toUpperCase();
        setMaterial(primeraletra + resto);
        int pesoL = (int) Math.round(getPeso());
        return getCodigo()+",V,"+RutUtility.formatearRut(getRutRemitente().toUpperCase())+","+RutUtility.formatearRut(getRutDestinatario().toUpperCase())+","+getMaterial()+","+pesoL;
    }
}

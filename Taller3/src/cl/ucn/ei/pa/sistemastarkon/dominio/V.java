package cl.ucn.ei.pa.sistemastarkon.dominio;

public class V extends Entrega {

    private String material;
    private double peso;

    public V(int codigo, String rutRemitente, String rutDestinatario, String material, double peso) {
        super(codigo, rutRemitente, rutDestinatario);
        this.material = material;
        this.peso = peso;
        
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public double calcularValor() {
        if (this.material.equals("Cuero")){
            this.valor = 200*(peso/1000)*150;
        } else {
            if (this.material.equals("Plastico")){
                this.valor = 150*(peso/1000)*150;
            } else {
                if (this.material.equals("Tela")){
                    this.valor = 100*(peso/1000)*150;
                }
            }
        }
        return 0;
    }

    @Override
    public String toStringHija() {
        return " Valor: "+getValor()+"\n Tipo: Valija";
    }
 
}

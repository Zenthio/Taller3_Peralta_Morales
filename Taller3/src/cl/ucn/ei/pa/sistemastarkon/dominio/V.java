package cl.ucn.ei.pa.sistemastarkon.dominio;

public class V extends Entrega {

    private String material;
    private int peso;
    private double pesoKg;

    public V(int codigo, String rutRemitente, String rutDestinatario, String material, int peso) {
        super(codigo, rutRemitente, rutDestinatario);
        this.material = material;
        this.peso = peso;
        this.pesoKg = (peso/1000);
        calcularValor();
        
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public double calcularValor() {
        if (material.equals("Cuero")){
            this.valor = 200*(pesoKg)*150;
        } else {
            if (material.equals("Plastico")){
                this.valor = 150*(pesoKg)*150;
            } else {
                if (material.equals("Tela")){
                    this.valor = 100*(pesoKg)*150;
                }
            }
        }
        return this.valor;
    }

    @Override
    public String toStringHija() {
        return "Codigo: "+getCodigo()+" | Valor: "+getValor()+" | Tipo: Valija\n";
    }
 
    @Override
    public String toStringLectura() {
        return getCodigo()+",V,"+getRutRemitente()+","+getRutDestinatario()+","+getMaterial()+","+getPeso();
    }
}

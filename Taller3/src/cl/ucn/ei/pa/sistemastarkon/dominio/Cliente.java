package cl.ucn.ei.pa.sistemastarkon.dominio;

public class Cliente {
    private String rut;
    private String nombre;
    private String apellido;
    private int saldo;
    private String ciudad;

    public Cliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.ciudad = ciudad;
    }

    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

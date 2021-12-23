package cl.ucn.ei.pa.sistemastarkon.logica;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import cl.ucn.ei.pa.sistemastarkon.dominio.*;
import cl.ucn.ei.pa.sistemastarkon.utils.*;

public class SistemaStarkonImpl implements SistemaStarkon{

    private LinkedList<Cliente> listaC;
    private ArrayList<Localizacion> listaL;
    private ListaEntrega listaE;
    private Cliente c;
    private int privilegio;
    Scanner scanner;

    public SistemaStarkonImpl(){
        scanner = new Scanner(System.in);
        listaE = new ListaEntrega();
        this.listaL = new ArrayList<Localizacion>();
        this.listaC = new LinkedList<Cliente>();
    }

    public int getPrivilegio(){
        return this.privilegio;
    }
    
    @Override
    public boolean iniciarSesion() {
        System.out.println("Ingrese opción");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1){
            System.out.println("Ingresar rut: ");
            String rut = scanner.nextLine();
            Iterator<Cliente> itC = listaC.iterator();
            while (itC.hasNext()){
                Cliente cliente = itC.next();
                if (cliente.getRut().equals(rut)){
                    this.c = cliente;
                    this.privilegio = 1;
                    return true;
                }else if(cliente.getRut().equals("admin")){
                    System.out.println("Ingrese contraseña admin: ");
                    String contraseña = scanner.nextLine();
                    if(contraseña.equals("choripan123")){
                        this.privilegio = 2;
                        return true;
                    }
                } else {
                    System.out.println("No se ha encontrado el rut, desea registrarse?");
                    int opcionR = Integer.parseInt(scanner.nextLine());
                    if (opcionR == 1){
                        registrarCuenta();
                    }
                }
            }
            
        } else if (opcion == 2){
            registrarCuenta();
        } else if(opcion == 3){
            this.privilegio = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean registrarCuenta(){
        System.out.println("Ingresar rut de cuenta a registrar: ");
        String rut = scanner.nextLine();
        for (Cliente cliente: this.listaC){
            if (cliente.getRut().equals(rut)){
                System.out.println("Rut ya existente en el sistema.");

            } else {
                System.out.println("Ingresar nombre de cuenta a registrar: ");
                String nombre = scanner.nextLine();
                System.out.println("Ingresar apellido de cuenta a registrar: ");
                String apellido = scanner.nextLine();
                System.out.println("Ingresar saldo de cuenta a registrar: ");
                int saldo = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingresar ciudad de cuenta a registrar");
                String ciudad = scanner.nextLine();
                if (ingresarCliente(rut,nombre,apellido,saldo,ciudad)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean ingresarLocalizacion(String nombre) {
        Localizacion localizacion = new Localizacion(nombre);
        return listaL.add(localizacion);
    }

    @Override
    public boolean ingresarCliente(String rut, String nombre, String apellido, double saldo, String ciudad) {
        Cliente cliente = new Cliente(rut,nombre,apellido,saldo,ciudad);
        return listaC.add(cliente);
    }

    @Override
    public boolean ingresarEntregaD(int codigo, String rutR, String rutD, double peso, double grosor) {
        Entrega e = new D(codigo,rutR,rutD,peso,grosor);
        return listaE.ingresarNodo(e);
    }
    @Override
    public boolean ingresarEntregaE(int codigo, String rutR, String rutD, double peso, double largo, double ancho,
    double prof) {
        Entrega e = new E(codigo,rutR,rutD,peso,largo,ancho,prof);
        return listaE.ingresarNodo(e);
    }
    @Override
    public boolean ingresarEntregaV(int codigo, String rutR, String rutD, String material, double peso) {
        Entrega e = new V(codigo,rutR,rutD,material,peso);
        return listaE.ingresarNodo(e);
    }

    @Override
    public void realizarEntrega() {
        System.out.println("Ingrese tipo de entrega a realizar (D, E o V): ");
        String tipo = scanner.nextLine();
        tipo = tipo.toUpperCase();
        if (tipo.equals("D")){
            EntregaD();
        } else if (tipo.equals("E")){
            EntregaE();
        } else if (tipo.equals("V")){
            EntregaV();
        } else {
            System.out.println("El tipo de entrega a realizar es incorrecto");
        }
    }

    @Override
    public boolean EntregaD(){
        System.out.println("Ingrese peso (en gramos): ");
            double peso = Double.parseDouble(scanner.nextLine());
            if (peso > 1500){
                System.out.println("El peso es mayor al máximo");
            } else {
                System.out.println("Ingrese grosor (en centimetros): ");
                double grosor = Double.parseDouble(scanner.nextLine());
                if (grosor > 5){
                    System.out.println("El grosor es mayor al máximo");
                } else {
                    System.out.println("Ingresar rut remitente: ");
                    String rutR = scanner.nextLine();
                    if (!this.c.getRut().equals(rutR)){
                        System.out.println("El rut ingresado no es el mismo que posee el usuario");
                    } else {
                        System.out.println("Ingrese rut destinatario: ");
                        String rutD = scanner.nextLine();
                        Iterator<Cliente> itC = listaC.iterator();
                        while (itC.hasNext()){
                            Cliente cliente = itC.next();
                            if (cliente.getRut().equals(rutD)){
                                String codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                int codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                                if (listaE.buscarNodoC(codigoAleatorio2) == null){
                                    Entrega e = new D(codigoAleatorio2,rutR,rutD,peso,grosor);
                                    return pagarEntrega(e);
                                }
                            } else {
                                System.out.println("No existe el rut de destinatario ingresado en el sistema");
                            }
                        }
                    }
                }
            }
            return false;
    }
    
    @Override
    public boolean EntregaE() {
        System.out.println("Ingrese peso (en gramos): ");
        double peso = Double.parseDouble(scanner.nextLine());
        if (peso > 50000 || peso < 0){
            System.out.println("El peso es mayor al máximo, o es negativo");
        } else {
            System.out.println("Ingrese largo (en centimetros");
            double largo = Double.parseDouble(scanner.nextLine());
            if (0 > largo || largo > 120){
                System.out.println("El largo es mayor al máximo, o es negativo");
            } else {
                System.out.println("Ingrese ancho (en centimetros)");
                double ancho = Double.parseDouble(scanner.nextLine());
                if (0 > ancho || ancho > 80 ){
                    System.out.println("El ancho es mayor al máximo, o es negativo");
                } else {
                    System.out.println("Ingrese profundidad (en centimetros)");
                    double prof = Double.parseDouble(scanner.nextLine());
                    if (0 > prof || prof > 80){
                        System.out.println("La profundidad es mayor al maximo, o es negativa");
                    } else {
                        System.out.println("Ingresar rut remitente: ");
                        String rutR = scanner.nextLine();
                        if (!this.c.getRut().equals(rutR)){
                            System.out.println("El rut ingresado no es el mismo que posee el usuario");
                        } else {
                            System.out.println("Ingrese rut destinatario: ");
                            String rutD = scanner.nextLine();
                            Iterator<Cliente> itC = listaC.iterator();
                            while (itC.hasNext()){
                                Cliente cliente = itC.next();
                                if (cliente.getRut().equals(rutD)){
                                    String codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                    int codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                                    if (listaE.buscarNodoC(codigoAleatorio2) == null){
                                        Entrega e = new E(codigoAleatorio2,rutR,rutD,peso,largo,ancho,prof);
                                        return pagarEntrega(e);
                                    }
                                } else {
                                    System.out.println("No existe el rut de destinatario ingresado en el sistema");
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean EntregaV() {
        System.out.println("Ingrese material: ");
        String material = scanner.nextLine();
        material.toLowerCase();
        if (!material.equals("cuero") || !material.equals("plastico") || !material.equals("tela")) {
            System.out.println("El material no existe en el sistema.");
        } else {
            System.out.println("Ingrese peso (en gramos)");
            double peso = Double.parseDouble(scanner.nextLine());
            if (0 > peso || peso > 2000){
                System.out.println("El peso es mayor al máximo, o es negativo");
            } else {
                System.out.println("Ingresar rut remitente: ");
                String rutR = scanner.nextLine();
                if (!this.c.getRut().equals(rutR)){
                    System.out.println("El rut ingresado no es el mismo que posee el usuario");
                } else {
                    System.out.println("Ingrese rut destinatario: ");
                    String rutD = scanner.nextLine();
                    Iterator<Cliente> it = listaC.iterator();
                    while (it.hasNext()){
                        Cliente clienteFor = (Cliente) it.next();
                        if (clienteFor.getRut().equals(rutD)){
                            String codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                            int codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                            if (listaE.buscarNodoC(codigoAleatorio2) == null){
                                Entrega e = new V(codigoAleatorio2,rutR,rutD,material,peso);
                                return pagarEntrega(e);
                            }
                        }
                    }
                    System.out.println("No existe el rut destinatario en el sistema");
                }
            }
        }
        return false;
    }

    @Override
    public boolean pagarEntrega(Entrega e) {
        double valor = e.getValor();
        Iterator<Localizacion> itL = listaL.iterator();
        while (itL.hasNext()) {
            Localizacion l = (Localizacion) itL.next();
            if (l.getNombre().equals(c.getCiudad())){
                if (this.c.getSaldo() > valor){
                    listaE.ingresarNodo(e);
                    this.c.setSaldo(c.getSaldo()-valor);
                    return true;
                    
                } else {
                    System.out.println("No hay saldo suficiente para realizar esta acción.");
                    return false;
                }

            } else {
                System.out.println("Su ciudad no poseé oficina Starkon, lo sentimos.");
            }

        }
        return false;
    }
    
    @Override
    public boolean recargarSaldo() {
        System.out.println("Ingrese monto a recargar");
        double monto = Double.parseDouble(scanner.nextLine());
        if (monto > 0){
            c.setSaldo(c.getSaldo()+monto);
            return true;
        } else {
            System.out.println("No puede ingresar saldo negativo");
            return false;
        }
    }
    
    @Override
    public String verEntregas() {
        String retorno = "";
        ListaEntrega ler = c.getEntregasRecibidas();
        ListaEntrega lee = c.getEntregasEnviadas();
        for (int i = 0; i < ler.getSize(); i++){
            Entrega e = ler.buscarINodo(i);
            retorno += "Entrega Recibida:\n"+e.toString()+"\n";
            
        }
        retorno += "\n";
        for (int i = 0; i < lee.getSize(); i++){
            Entrega e = lee.buscarINodo(i);
            retorno += "Entrega Enviada:\n"+e.toString()+"\n";
        }
        return retorno;
    }
    
    @Override
    public String entregasTipo() {
        return listaE.entregasTipo();
    }
    
    @Override
    public String entregasLocalizacion() {
        String retorno = "";
        Iterator<Localizacion> itL = listaL.iterator();
        while (itL.hasNext()) {
            Localizacion l = (Localizacion) itL.next();
            retorno += l.entregasLocalizacion()+"\n";
        }
        return retorno;
    }
    
    @Override
    public String entregasCliente() {
        String retorno = "";
        Iterator<Cliente> itC = listaC.iterator();
        while (itC.hasNext()) {
            Cliente c = (Cliente) itC.next();
            ListaEntrega ler = c.getEntregasEnviadas();
            ListaEntrega lee = c.getEntregasRecibidas();
            retorno += "Cliente: "+c.getNombre()+" "+c.getApellido()+"\n"+ler.entregas()+lee.entregas()+"\n";
        }
        return retorno;
    }
    
    @Override
    public String registroGanancias() {
        String retorno = "";
        int gananciaTotal = 0;
        Iterator<Localizacion> itL = listaL.iterator();
        while (itL.hasNext()) {
            Localizacion l = itL.next();
            retorno += "Oficina: "+l.getNombre()+", Ganancia: "+l.getGanancia()+"\n";
            gananciaTotal += l.getGanancia();
        }
        retorno += "Ganancia total: "+gananciaTotal;
        return retorno;
    }

    @Override
    public String obtenerDatosCliente() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String obtenerDatosLocalizacion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String obtenerDatosEntrega() {
        // TODO Auto-generated method stub
        return null;
    }
}

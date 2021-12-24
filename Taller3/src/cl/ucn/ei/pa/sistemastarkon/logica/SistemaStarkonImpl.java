package cl.ucn.ei.pa.sistemastarkon.logica;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
        System.out.println("<==============================> INICIO DE SESIÓN <==============================>\n[1] Iniciar Sesión\n[2] Registrarse\n[3] Salir");
        System.out.print("Ingrese opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1){
            System.out.print("Ingresar rut: ");
            String rut = scanner.nextLine();
            rut = RutUtility.quitarFormatoRut(rut);
            Iterator<Cliente> itC = listaC.iterator();
            while (itC.hasNext()){
                Cliente cliente = itC.next();
                if (cliente.getRut().equals(rut)){
                    this.c = cliente;
                    this.privilegio = 1;
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    return true;
                }else if(rut.equals("admin")){
                    System.out.println("Ingrese contraseña admin: ");
                    String contraseña = scanner.nextLine();
                    if(contraseña.equals("choripan123")){
                        this.privilegio = 2;
                        System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                        return true;
                    }
                } else {
                    System.out.println("No se ha encontrado el rut, desea registrarse?");
                    int opcionR = Integer.parseInt(scanner.nextLine());
                    if (opcionR == 1){
                        System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                        registrarCuenta();
                    }
                }
            }
            
        } else if (opcion == 2){
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            registrarCuenta();
        } else if(opcion == 3){
            this.privilegio = 0;
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            return true;
        }
        return false;
    }

    @Override
    public boolean registrarCuenta(){
        System.out.println("<==============================> REGISTRO CUENTA <==============================>\n");
        System.out.println("Ingresar rut de cuenta a registrar: ");
        String rut = scanner.nextLine();
        rut = RutUtility.quitarFormatoRut(rut);
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
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
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
    public boolean ingresarCliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
        Cliente cliente = new Cliente(rut,nombre,apellido,saldo,ciudad);
        return listaC.add(cliente);
    }

    @Override
    public boolean ingresarEntregaD(int codigo, String rutR, String rutD, int peso, int grosor) {
        Entrega e = new D(codigo,rutR,rutD,peso,grosor);
        Iterator<Cliente> itC = listaC.iterator();
        while (itC.hasNext()){
            Cliente c = (Cliente) itC.next();
            if (c.getRut().equals(rutR)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (c.getCiudad().equals(l.getNombre())){
                        l.getEntregasEnviadas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        c.getEntregasEnviadas().ingresarNodo(e);
                        return listaE.ingresarNodo(e);              
                    } 
                }
                System.out.println("La ciudad del cliente "+c.getRut()+" no poseé oficinas.");
            } else if (c.getRut().equals(rutD)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (c.getCiudad().equals(l.getNombre())){
                        l.getEntregasRecibidas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        c.getEntregasRecibidas().ingresarNodo(e);
                        return listaE.ingresarNodo(e);
                    }
                }
                System.out.println("La ciudad del cliente "+c.getRut()+" no poseé oficinas.");
            } else {
                System.out.println("La entrega "+e.getCodigo()+" poseé el rut "+rutD+" que no existe en la base de datos");
            }
        }
        return false;
    }
    @Override
    public boolean ingresarEntregaE(int codigo, String rutR, String rutD, int peso, int largo, int ancho, int prof) {
        Entrega e = new E(codigo,rutR,rutD,peso,largo,ancho,prof);
        Iterator<Cliente> itC = listaC.iterator();
        while (itC.hasNext()){
            Cliente c = (Cliente) itC.next();
            if (c.getRut().equals(rutR)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (c.getCiudad().equals(l.getNombre())){
                        l.getEntregasEnviadas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        c.getEntregasEnviadas().ingresarNodo(e);
                        return listaE.ingresarNodo(e);                 
                    } 
                }
                System.out.println("La ciudad del cliente "+c.getRut() +" no poseé oficinas.");
            } else if (c.getRut().equals(rutD)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (c.getCiudad().equals(l.getNombre())){
                        l.getEntregasRecibidas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        c.getEntregasRecibidas().ingresarNodo(e);
                        return listaE.ingresarNodo(e);
                    }
                }
                System.out.println("La ciudad del cliente "+c.getRut() +" no poseé oficinas.");
            } else {
                System.out.println("La entrega "+e.getCodigo() +" poseé un rut que no existe en la base de datos");
            }
        }
        return false;
    }
    @Override
    public boolean ingresarEntregaV(int codigo, String rutR, String rutD, String material, int peso) {
        Entrega e = new V(codigo,rutR,rutD,material,peso);
        Iterator<Cliente> itC = listaC.iterator();
        while (itC.hasNext()){
            Cliente c = (Cliente) itC.next();
            if (c.getRut().equals(rutR)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (c.getCiudad().equals(l.getNombre())){
                        l.getEntregasEnviadas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        c.getEntregasEnviadas().ingresarNodo(e);
                        return listaE.ingresarNodo(e);                 
                    } 
                }
                System.out.println("La ciudad del cliente "+c.getRut() +" no poseé oficinas.");
            } else if (c.getRut().equals(rutD)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (c.getCiudad().equals(l.getNombre())){
                        l.getEntregasRecibidas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        c.getEntregasRecibidas().ingresarNodo(e);
                        return listaE.ingresarNodo(e);
                    }
                }
                System.out.println("La ciudad del cliente "+c.getRut() +" no poseé oficinas.");
            } else {
                System.out.println("La entrega "+e.getCodigo() +" poseé un rut que no existe en la base de datos");
            }
        }
        return false;
    }

    @Override
    public void realizarEntrega() {
        System.out.println("<==============================> REALIZAR ENTREGA <==============================>\n");
        System.out.print("Ingrese tipo de entrega a realizar (D, E o V): ");
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
        System.out.print("Ingrese peso (en gramos): ");
            int peso = Integer.parseInt(scanner.nextLine());
            if (peso > 1500){
                System.out.println("El peso es mayor al máximo");
            } else {
                System.out.print("Ingrese grosor (en centimetros): ");
                int grosor = Integer.parseInt(scanner.nextLine());
                if (grosor > 5){
                    System.out.println("El grosor es mayor al máximo");
                } else {
                    System.out.print("Ingresar rut remitente: ");
                    String rutR = scanner.nextLine();
                    rutR = RutUtility.quitarFormatoRut(rutR);
                    if (!this.c.getRut().equals(rutR)){
                        System.out.println("El rut ingresado no es el mismo que posee el usuario");
                    } else {
                        System.out.print ("Ingrese rut destinatario: ");
                        String rutD = scanner.nextLine();
                        rutD = RutUtility.quitarFormatoRut(rutD);
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
        System.out.print("Ingrese peso (en gramos): ");
        int peso = Integer.parseInt(scanner.nextLine());
        if (peso > 50000 || peso < 0){
            System.out.println("El peso es mayor al máximo, o es negativo");
        } else {
            System.out.print("Ingrese largo (en centimetros");
            int largo = Integer.parseInt(scanner.nextLine());
            if (0 > largo || largo > 120){
                System.out.println("El largo es mayor al máximo, o es negativo");
            } else {
                System.out.print("Ingrese ancho (en centimetros)");
                int ancho = Integer.parseInt(scanner.nextLine());
                if (0 > ancho || ancho > 80 ){
                    System.out.println("El ancho es mayor al máximo, o es negativo");
                } else {
                    System.out.print("Ingrese profundidad (en centimetros)");
                    int prof = Integer.parseInt(scanner.nextLine());
                    if (0 > prof || prof > 80){
                        System.out.println("La profundidad es mayor al maximo, o es negativa");
                    } else {
                        System.out.print("Ingresar rut remitente: ");
                        String rutR = scanner.nextLine();
                        rutR = RutUtility.quitarFormatoRut(rutR);
                        if (!this.c.getRut().equals(rutR)){
                            System.out.println("El rut ingresado no es el mismo que posee el usuario");
                        } else {
                            System.out.print("Ingrese rut destinatario: ");
                            String rutD = scanner.nextLine();
                            rutD = RutUtility.quitarFormatoRut(rutD);
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
        System.out.print("Ingrese material: ");
        String material = scanner.nextLine();
        material.toLowerCase();
        if (!material.equals("cuero") || !material.equals("plastico") || !material.equals("tela")) {
            System.out.println("El material no existe en el sistema.");
        } else {
            System.out.print("Ingrese peso (en gramos)");
            int peso = Integer.parseInt(scanner.nextLine());
            if (0 > peso || peso > 2000){
                System.out.println("El peso es mayor al máximo, o es negativo");
            } else {
                System.out.println("Ingresar rut remitente: ");
                String rutR = scanner.nextLine();
                rutR = RutUtility.quitarFormatoRut(rutR);
                if (!this.c.getRut().equals(rutR)){
                    System.out.println("El rut ingresado no es el mismo que posee el usuario");
                } else {
                    System.out.print("Ingrese rut destinatario: ");
                    String rutD = scanner.nextLine();
                    rutD = RutUtility.quitarFormatoRut(rutD);
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
        int valor = e.getValor();
        Iterator<Localizacion> itL = listaL.iterator();
        while (itL.hasNext()) {
            Localizacion l = (Localizacion) itL.next();
            if (l.getNombre().equals(c.getCiudad())){
                if (this.c.getSaldo() > valor){
                    listaE.ingresarNodo(e);
                    this.c.setSaldo(c.getSaldo()-valor);
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    return true;
                    
                } else {
                    System.out.println("No hay saldo suficiente para realizar esta acción, desea recargar?: \n[1] Sí\n[2]No");
                    System.out.print("Ingrese opción: ");
                    int opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion == 1){
                        recargarSaldo();
                        pagarEntrega(e);
                    } else {
                        return false;

                    }
                }

            } else {
                System.out.println("Su ciudad no poseé oficina Starkon, lo sentimos.");
            }

        }
        return false;
    }
    
    @Override
    public boolean recargarSaldo() {
        System.out.print("Ingrese monto a recargar: ");
        int monto = Integer.parseInt(scanner.nextLine());
        if (monto > 0){
            c.setSaldo(c.getSaldo()+monto);
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            return true;
        } else {
            System.out.println("No puede ingresar saldo negativo");
            return false;
        }
    }
    
    @Override
    public String verEntregas() {
        System.out.println("<==============================> DESPLIEGUE ENTREGAS <==============================>\n");
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
        retorno += "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    @Override
    public String entregasTipo() {
        System.out.println("<==============================> DESPLIEGUE ENTREGAS POR TIPO <==============================>\n");
        return listaE.entregasTipo();
    }
    
    @Override
    public String entregasLocalizacion() {
        System.out.println("<==============================> DESPLIEGUE ENTREGAS POR LOCALIZACION <==============================>\n");
        String retorno = "";
        Iterator<Localizacion> itL = listaL.iterator();
        while (itL.hasNext()) {
            Localizacion l = (Localizacion) itL.next();
            retorno += l.entregasLocalizacion()+"\n";
        }
        retorno += "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    @Override
    public String entregasCliente() {
        System.out.println("<==============================> DESPLIEGUE ENTREGAS DE CUENTAS <==============================>\n");
        String retorno = "";
        Iterator<Cliente> itC = listaC.iterator();
        while (itC.hasNext()) {
            Cliente c = (Cliente) itC.next();
            ListaEntrega ler = c.getEntregasEnviadas();
            ListaEntrega lee = c.getEntregasRecibidas();
            retorno += "Cliente: "+c.getNombre()+" "+c.getApellido()+"\n"+ler.entregas()+lee.entregas()+"\n";
        }
        retorno += "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }
    
    @Override
    public String registroGanancias() {
        System.out.println("<==============================> REGISTRO DE GANANCIAS <==============================>\n");
        String retorno = "";
        int gananciaTotal = 0;
        Iterator<Localizacion> itL = listaL.iterator();
        while (itL.hasNext()) {
            Localizacion l = itL.next();
            retorno += "Oficina: "+l.getNombre()+", Ganancia: "+l.getGanancia()+"\n";
            gananciaTotal += l.getGanancia();
        }
        retorno += "Ganancia total: "+gananciaTotal+"\n";
        retorno += "<================================================================================>\n                                        |\n                                        ▼";
        return retorno;
    }

    @Override
    public String obtenerDatosCliente() {
        String retorno = "";
        Iterator<Cliente> itC = listaC.iterator();
        int i = 0;
        while (itC.hasNext()){
            Cliente c = (Cliente) itC.next();
            if (i < listaC.size()-1 ){
                retorno += c.toString()+"\n";
            } else {
                retorno += c.toString();
            }
            i++;
        }
        return retorno;
    }

    @Override
    public String obtenerDatosLocalizacion() {
        String retorno = "";
        Iterator<Localizacion> itL = listaL.iterator();
        int i = 0;
        while (itL.hasNext()) {
            Localizacion l = (Localizacion) itL.next();
            if (i < listaC.size()-1 ){
                retorno += l.toString()+"\n";
            } else {
                retorno += l.toString();
            }
            i++;
        }
        return retorno;
    }

    @Override
    public String obtenerDatosEntrega() {
        String retorno = "";
        for (int i = 0; i < listaE.getSize(); i++){
            Entrega e = listaE.buscarINodo(i);
            if (i < listaE.getSize() -1){
                retorno += e.toStringLectura()+"\n";
            } else {
                retorno += e.toStringLectura();
            }
            
        }
        return retorno;
    }
}

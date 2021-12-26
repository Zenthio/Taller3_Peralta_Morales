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
            boolean encontrado = false;
            while (itC.hasNext()){
                Cliente cliente = itC.next();
                if (cliente.getRut().equals(rut)){
                    this.c = cliente;
                    this.privilegio = 1;
                    encontrado = true;
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    return true;
                }else if(rut.equals("admin")){
                    System.out.println("Ingrese contraseña admin: ");
                    String contraseña = scanner.nextLine();
                    if(contraseña.equals("choripan123")){
                        this.privilegio = 2;
                        System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                        encontrado = true;
                        return true;
                    }
                }
            }
            if (!encontrado){
                System.out.println("No se ha encontrado el rut, desea registrarse? \n[1] Si\n[2] No\n Opcion: ");
                int opcionR = Integer.parseInt(scanner.nextLine());
                if (opcionR == 1){
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    registrarCuenta();
                } else if (opcion == 2){ 
                    iniciarSesion();
                }
                
            }
            
        } else if (opcion == 2){
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            registrarCuenta();
        } else if(opcion == 3){
            this.privilegio = 0;
            System.out.println("<================================================================================>\n                                        |\n                                        ▼");
            return true;
        } else {
            throw new NullPointerException("La opción ingresada es incorrecta, cerrando sistema...\n");
            
        }
        return false;
        
    }

    @Override
    public boolean registrarCuenta(){
        System.out.println("<==============================> REGISTRO CUENTA <==============================>\n");
        System.out.println("Ingresar rut de cuenta a registrar: ");
        String rut = scanner.nextLine();
        rut = RutUtility.quitarFormatoRut(rut);
        boolean noencontrado = false;
        Iterator<Cliente> itC = listaC.iterator();
        while (itC.hasNext()){
            Cliente cliente = (Cliente) itC.next();
            if (cliente.getRut().equals(rut)){
                System.out.println("Rut ya existente en el sistema.");
                noencontrado = true;
                break;
            }
        }
        if (!noencontrado){
            System.out.println("Ingresar nombre de cuenta a registrar: ");
                String nombre = scanner.nextLine();
                System.out.println("Ingresar apellido de cuenta a registrar: ");
                String apellido = scanner.nextLine();
                System.out.println("Ingresar saldo de cuenta a registrar: ");
                int saldo = Integer.parseInt(scanner.nextLine());
                System.out.println("Ingresar ciudad de cuenta a registrar");
                String ciudad = scanner.nextLine();
                ingresarCliente(rut,nombre,apellido,saldo,ciudad);
                System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                return true;
        }
        return false;
    }

    @Override
    public void ingresarLocalizacion(String nombre) {
        Localizacion localizacion = new Localizacion(nombre);
        listaL.add(localizacion);
    }

    @Override
    public void ingresarCliente(String rut, String nombre, String apellido, int saldo, String ciudad) {
        Cliente cliente = new Cliente(rut,nombre,apellido,saldo,ciudad);
        listaC.add(cliente);
    }
    /*
    EVITAR ITERACIONES INNECESARIAS
    */


    @Override
    public void ingresarEntregaD(int codigo, String rutR, String rutD, int peso, int grosor) {
        Entrega e = new D(codigo,rutR,rutD,peso,grosor);
        Iterator<Cliente> itC = listaC.iterator();
        int cont = 0;
        boolean error = true;
        while (itC.hasNext()){
            Cliente cliente = (Cliente) itC.next();
            if (cliente.getRut().equals(rutR)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (cliente.getCiudad().equals(l.getNombre())){
                        l.getEntregasEnviadas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        cliente.getEntregasEnviadas().ingresarNodo(e);
                        cont++;
                        break;           
                    }
                }
            } else if (cliente.getRut().equals(rutD)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (cliente.getCiudad().equals(l.getNombre())){
                        l.getEntregasRecibidas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        cliente.getEntregasRecibidas().ingresarNodo(e);
                        cont++;
                        break;
                    }
                }
                
            }
            if (cont == 2){
                error = false;
                listaE.ingresarNodo(e); 
                break;
            }
        }
        if (error){
            throw new NullPointerException("La entrega con código "+codigo+" no pudo ser ingresada al sistema");
        }
    }
    @Override
    public void ingresarEntregaE(int codigo, String rutR, String rutD, int peso, int largo, int ancho, int prof) {
        Entrega e = new E(codigo,rutR,rutD,peso,largo,ancho,prof);
        Iterator<Cliente> itC = listaC.iterator();
        int cont = 0;
        boolean error = true;
        while (itC.hasNext()){
            Cliente cliente = (Cliente) itC.next();
            if (cliente.getRut().equals(rutR)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (cliente.getCiudad().equals(l.getNombre())){
                        l.getEntregasEnviadas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        cliente.getEntregasEnviadas().ingresarNodo(e);
                         
                        cont++;
                        break;              
                    } 
                }
            } else if (cliente.getRut().equals(rutD)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (cliente.getCiudad().equals(l.getNombre())){
                        l.getEntregasRecibidas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        cliente.getEntregasRecibidas().ingresarNodo(e);
                        
                        cont++;
                        break;
                    }
                }
            } 
            if (cont == 2){
                error = false;
                listaE.ingresarNodo(e);  
                break;
            }
        }
        if (error){
            throw new NullPointerException("La entrega con código "+codigo+" no pudo ser ingresada al sistema");
        }
    }
    @Override
    public void ingresarEntregaV(int codigo, String rutR, String rutD, String material, int peso) {
        Entrega e = new V(codigo,rutR,rutD,material,peso);
        Iterator<Cliente> itC = listaC.iterator();
        int cont = 0;
        boolean error = true;
        while (itC.hasNext()){
            Cliente cliente = (Cliente) itC.next();
            if (cliente.getRut().equals(rutR)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (cliente.getCiudad().equals(l.getNombre())){
                        l.getEntregasEnviadas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        cliente.getEntregasEnviadas().ingresarNodo(e); 
                        cont++;
                        break;             
                    } 
                }
            } else if (cliente.getRut().equals(rutD)){
                Iterator<Localizacion> itL = listaL.iterator();
                while (itL.hasNext()){
                    Localizacion l = itL.next();
                    if (cliente.getCiudad().equals(l.getNombre())){
                        l.getEntregasRecibidas().ingresarNodo(e);
                        l.setGanancia(l.getGanancia()+e.getValor());
                        cliente.getEntregasRecibidas().ingresarNodo(e);

                        cont++;
                        break;
                    }
                }
            } 
            if (cont == 2){
                error = false;
                listaE.ingresarNodo(e);
                break;
            }
        }
        if (error){
            throw new NullPointerException("La entrega con código "+codigo+" no pudo ser ingresada al sistema");
        }
        
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
            throw new NullPointerException("El tipo de entrega a realizar es incorrecto");
        }
    }

    @Override
    public boolean EntregaD(){
        System.out.print("Ingrese peso (en gramos): ");
            int peso = Integer.parseInt(scanner.nextLine());
            if (0 > peso || peso > 1500){
                throw new NullPointerException("El peso es mayor al máximo, o es negativo");
            } else {
                System.out.print("Ingrese grosor (en milimetros): ");
                int grosor = Integer.parseInt(scanner.nextLine());
                if (0 > grosor || grosor > 50){
                    throw new NullPointerException("El grosor es mayor al máximo, o es negativo");
                } else {
                    System.out.print("Ingresar rut remitente: ");
                    String rutR = scanner.nextLine();
                    rutR = RutUtility.quitarFormatoRut(rutR);
                    if (!this.c.getRut().equals(rutR)){
                        throw new NullPointerException("El rut ingresado no es el mismo que posee el usuario");
                    } else {
                        System.out.print ("Ingrese rut destinatario: ");
                        String rutD = scanner.nextLine();
                        rutD = RutUtility.quitarFormatoRut(rutD);
                        Iterator<Cliente> itC = listaC.iterator();
                        boolean encontrado = false;
                        while (itC.hasNext()){
                            Cliente cliente = itC.next();
                            if (cliente.getRut().equals(rutD)){
                                String codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                int codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                                while (true){
                                    if (listaE.buscarNodoC(codigoAleatorio2) == null){
                                        Entrega e = new D(codigoAleatorio2,rutR,rutD,peso,grosor);
                                        encontrado = true;
                                        return pagarEntrega(e);
                                    }
                                    codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                    codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                                }
                            } 
                        }
                        if (!encontrado){
                            throw new NullPointerException("No existe el rut de destinatario ingresado en el sistema");
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
            throw new NullPointerException("El peso es mayor al máximo, o es negativo");
        } else {
            System.out.print("Ingrese largo (en milimetros): ");
            int largo = Integer.parseInt(scanner.nextLine());
            if (0 > largo || largo > 120){
                throw new NullPointerException("El largo es mayor al máximo, o es negativo");
            } else {
                System.out.print("Ingrese ancho (en milimetros): ");
                int ancho = Integer.parseInt(scanner.nextLine());
                if (0 > ancho || ancho > 80 ){
                    throw new NullPointerException("El ancho es mayor al máximo, o es negativo");
                } else {
                    System.out.print("Ingrese profundidad (en milimetros): ");
                    int prof = Integer.parseInt(scanner.nextLine());
                    if (0 > prof || prof > 80){
                        throw new NullPointerException("La profundidad es mayor al maximo, o es negativa");
                    } else {
                        System.out.print("Ingresar rut remitente: ");
                        String rutR = scanner.nextLine();
                        rutR = RutUtility.quitarFormatoRut(rutR);
                        if (!this.c.getRut().equals(rutR)){
                            throw new NullPointerException("El rut ingresado no es el mismo que posee el usuario");
                        } else {
                            System.out.print("Ingrese rut destinatario: ");
                            String rutD = scanner.nextLine();
                            rutD = RutUtility.quitarFormatoRut(rutD);
                            Iterator<Cliente> itC = listaC.iterator();
                            boolean encontrado = false;
                            while (itC.hasNext()){
                                Cliente cliente = itC.next();
                                if (cliente.getRut().equals(rutD)){
                                    String codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                    int codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                                    while (true){
                                        if (listaE.buscarNodoC(codigoAleatorio2) == null){
                                            Entrega e = new E(codigoAleatorio2,rutR,rutD,peso,largo,ancho,prof);
                                            encontrado = true;
                                            return pagarEntrega(e);
                                        }
                                        codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                        codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                                    }
                                } 
                            }
                            if (!encontrado){
                                throw new NullPointerException("No existe el rut de destinatario ingresado en el sistema");
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
        if (material.equals("cuero") || material.equals("plastico") || material.equals("tela")) {
            System.out.print("Ingrese peso (en gramos): ");
            int peso = Integer.parseInt(scanner.nextLine());
            if (0 > peso || peso > 2000){
                throw new NullPointerException("El peso es mayor al máximo, o es negativo");
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
                    boolean encontrado = false;
                    while (it.hasNext()){
                        Cliente clienteFor = (Cliente) it.next();
                        if (clienteFor.getRut().equals(rutD)){
                            String codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                            int codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                            while (true){
                                if (listaE.buscarNodoC(codigoAleatorio2) == null){
                                    Entrega e = new V(codigoAleatorio2,rutR,rutD,material,peso);
                                    encontrado = true;
                                    return pagarEntrega(e);
                                }
                                codigoAleatorio = RandomUtility.obtenerCodigoAleatorio(6);
                                codigoAleatorio2 = Integer.parseInt(codigoAleatorio);
                            }
                        }
                    }
                    if (!encontrado){
                        throw new NullPointerException("No existe el rut de destinatario ingresado en el sistema");
                    }
                    
                }
            }
        } else {
            throw new NullPointerException("El material no existe en el sistema.");
            
        }
        return false;
    }

    @Override
    public boolean pagarEntrega(Entrega e) {
        double valor = e.getValor();
        int valorI = (int) Math.round(valor);
        Iterator<Localizacion> itL = listaL.iterator();
        boolean encontrado = false;
        while (itL.hasNext()) {
            Localizacion l = (Localizacion) itL.next();
            if (l.getNombre().equals(c.getCiudad())){
                encontrado = true;
                if (this.c.getSaldo() > valor){
                    listaE.ingresarNodo(e);
                    this.c.setSaldo(c.getSaldo()-valorI);
                    System.out.println("Se completó la entrega.\n");
                    System.out.println("<================================================================================>\n                                        |\n                                        ▼");
                    return true;
                    
                } else {
                    System.out.println("No hay saldo suficiente para realizar esta acción, desea recargar?\n[1] Sí\n[2]No");
                    System.out.print("\nIngrese opción: ");
                    int opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion == 1){
                        recargarSaldo();
                        pagarEntrega(e);
                    } else if(opcion == 2){
                        return false;

                    } else {
                        throw new NullPointerException("Opción invalida");
                    }
                }

            }

        }
        if (!encontrado){
            throw new NullPointerException("Su ciudad no poseé oficina Starkon, lo sentimos.");
            
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
            throw new NullPointerException("No puede ingresar saldo negativo");
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
        int cont = 0;
        while (itC.hasNext()) {
            Cliente c = (Cliente) itC.next();
            ListaEntrega ler = c.getEntregasEnviadas();
            ListaEntrega lee = c.getEntregasRecibidas();
            if( cont < listaC.size()-1){
                retorno += "\nCliente: "+c.getNombre()+" "+c.getApellido()+"\n\n"+"Entregas recibidas:\n"+ler.entregas()+"Entregas enviadas:"+lee.entregas();    
                retorno += "<================================================================================>\n";
            } else {
                retorno += "\nCliente: "+c.getNombre()+" "+c.getApellido()+"\n\n"+"Entregas recibidas:\n"+ler.entregas()+"Entregas enviadas:"+lee.entregas();
            }
            cont++;
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
            retorno += "Oficina: "+l.getNombre()+" | Ganancia: "+l.getGanancia()+"\n";
            gananciaTotal += l.getGanancia();
        }
        retorno += "Ganancia total: "+gananciaTotal+"\n\n";
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

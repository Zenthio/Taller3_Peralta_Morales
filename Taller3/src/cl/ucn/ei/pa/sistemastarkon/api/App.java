package cl.ucn.ei.pa.sistemastarkon.api;

import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import cl.ucn.ei.pa.sistemastarkon.logica.*;
import cl.ucn.ei.pa.sistemastarkon.utils.RutUtility;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaStarkon sys = new SistemaStarkonImpl();
        lecturaLocalizaciones(sys);
        lecturaClientes(sys);
        lecturaEntregas(sys);
        menu(sys,scanner);
    }

    public static void lecturaEntregas(SistemaStarkon sys) {
        try {
            Scanner arch = new Scanner(new File("Entregas.txt"));
            while (arch.hasNextLine()){
                String line = arch.nextLine();
                String[] datos = line.split(",");
                int codigo = Integer.parseInt(datos[0]);
                String tipo = datos[1];
                String rutR = datos[2];
                rutR = RutUtility.quitarFormatoRut(rutR);
                String rutD = datos[3];
                rutD = RutUtility.quitarFormatoRut(rutD);
                if (tipo.equals("V")){                  
                    String material = datos[4];
                    double peso = Double.parseDouble(datos[5]);
                    sys.ingresarEntregaV(codigo, rutR, rutD, material, peso);
                        
                } else if (tipo.equals("D")){
                    double peso = Double.parseDouble(datos[4]);
                    double grosor = Double.parseDouble(datos[5]);
                    sys.ingresarEntregaD(codigo, rutR, rutD, peso, grosor);
                        
                    
                } else {
                    double peso = Double.parseDouble(datos[4]);
                    double largo = Double.parseDouble(datos[5]);
                    double ancho = Double.parseDouble(datos[6]);
                    double prof = Double.parseDouble(datos[7]);
                    sys.ingresarEntregaE(codigo, rutR, rutD, peso, largo, ancho, prof);
                    
                }
            }
            
        } catch (IOException | NullPointerException e){
               System.out.println(e.getMessage());
            }
        }


    public static void lecturaLocalizaciones(SistemaStarkon sys) {
        try {
            Scanner arch = new Scanner(new File("Localizacion.txt"));
            while (arch.hasNextLine()){
                String line = arch.nextLine();
                String[] datos = line.split(",");
                String nombre = datos[0];
                sys.ingresarLocalizacion(nombre);
                
            }
        } catch (IOException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public static void lecturaClientes(SistemaStarkon sys) {
        try {
            Scanner arch = new Scanner(new File("Cliente.txt"));
            while (arch.hasNextLine()){
                String line = arch.nextLine();
                String[] datos = line.split(",");
                String rut = datos[0];
                rut = RutUtility.quitarFormatoRut(rut);
                String nombre = datos[1];
                String apellido = datos[2];
                int saldo = Integer.parseInt(datos[3]);
                String ciudad = datos[4];
                sys.ingresarCliente(rut, nombre, apellido, saldo, ciudad);
                
                
            }
        } catch (IOException | NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public static void menu(SistemaStarkon sys, Scanner scanner){
        while (true){
            try {
                sys.iniciarSesion();
            } catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
            if (sys.getPrivilegio() == 1){
                menuCliente(sys, scanner);
            }
            if (sys.getPrivilegio() == 2){
                menuAdmin(sys, scanner);
            }
            if (sys.getPrivilegio() == 0){
                break;
            }

        }
        escritura(sys);
    }

    public static void menuCliente(SistemaStarkon sys, Scanner scanner) {

        
        boolean salir = false;
        int opcion;
        do{
            System.out.println("<================================> MENU CLIENTE <================================>\n");
            System.out.println("[1] Realizar Entrega\n[2] Recargar Saldo\n[3] Ver Entregas\n[4] Salir");
            try {
                System.out.print("Ingrese su opci??n: ");
                opcion = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (opcion) {
                    case 1:
                    try {
                        sys.realizarEntrega();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 2:
                    try {
                        sys.recargarSaldo();
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
    
                    case 3:
                    try {
                        System.out.println(sys.verEntregas());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;
        
                    case 4:
                    System.out.println("<================================================================================>\n                                        |\n                                        ???");
                    salir = true;
                    break;
    
                    default:
                    System.out.println("La opci??n ingresada no es correcta");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar solo caracteres num??ricos");
            }
        } while (salir != true);
    }

    public static void menuAdmin(SistemaStarkon sys, Scanner scanner){
        
        boolean salir = false;
        int opcion;

        do {
            System.out.println("<================================> MENU ADMIN <================================>\n");
            System.out.println("[1] Entregas por Tipo\n[2] Entregas por Localizaci??n\n[3] Entregas por Cliente\n[4] Registro de Ganancias\n[5] Salir");

            try {
                System.out.print("Ingrese su opci??n: ");
                opcion = Integer.parseInt(scanner.nextLine());
                System.out.println();
                switch (opcion) {
                    case 1:
                    try {
                        System.out.println(sys.entregasTipo());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 2:
                    try {
                        System.out.println( sys.entregasLocalizacion());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 3:
                    try {
                        System.out.println(sys.entregasCliente());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 4:
                    try {
                        System.out.println(sys.registroGanancias());
                    } catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                    case 5:
                    System.out.println("<================================================================================>\n                                        |\n                                        ???");
                    salir = true;
                    break;

                    default:
                    System.out.println("La opci??n ingresada no es correcta.");
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("Se debe de ingresar s??lo car??cteres num??ricos");
            }
        } while (salir != true);
    }

    public static void escritura(SistemaStarkon sys){
        System.out.println("<==============================> ESCRITURA DE ARCHIVOS <==============================>\n");
        try {
            BufferedWriter personajes = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Cliente.txt")));
            personajes.write(sys.obtenerDatosCliente());
            personajes.close();
        } catch (IOException e){ 
            System.out.println(e.getMessage());
        }
        
        try {
            BufferedWriter clientes = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Localizacion.txt")));
            clientes.write(sys.obtenerDatosLocalizacion());
            clientes.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        try {
            BufferedWriter recaudacion = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Entregas.txt")));
            recaudacion.write(sys.obtenerDatosEntrega());
            recaudacion.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Gracias por usar nuestro sistema, hasta luego! <3");
        System.out.println("<================================================================================>");
    }
}

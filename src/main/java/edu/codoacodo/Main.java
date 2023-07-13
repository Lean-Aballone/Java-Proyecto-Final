package edu.codoacodo;
import edu.codoacodo.service.OradorService;
import edu.codoacodo.service.TicketService;
import edu.codoacodo.infrastucture_persistence.mysql.gestionbd.GestionBD;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private final static OradorService oradorService = new OradorService();
    private final static TicketService ticketService = new TicketService();
    public final static GestionBD bdGestion = new GestionBD();
    private final static Scanner cin = new Scanner(System.in);
    public static String bdName,bdUser,bdPass;
    public static void main(String[] args) {

        Menu menu = new Menu(oradorService, ticketService);
        byte opt;
        do{
            opt = menu.runMenu();
            if(opt ==  8){
                //SQL ENABLED
                bd();
                bdGestion.connectBD();
                bdSettings(bdGestion);
            }

        }while(opt != 0);
    }

    public static void bdSettings(){
        bd();
        //bdGestion.connectBD();
        System.out.println("""
                        Opciones:
                        \t1.Crear Database.
                        \t2.Crear Tabla.
                        \t3.Ingresar Datos.
                        \t0.Salir.
                        """);
        System.out.print("Ingresar Opcion: ");
        byte optdb = cin.nextByte();
        switch (optdb){
            case 0:
                break;
            case 1:
                System.out.println("Creando Base de datos...");
                bdGestion.crearBD();
                break;
            case 2:
                System.out.println("Creando Tabla...");
                bdGestion.crearTabla();
                break;
            case 3:
                byte optIns;
                do {
                    System.out.println("""
                        \t1.Insertar Orador.
                        \t2.Insertar Ticket.
                        \t0.Salir
                        """);
                    optIns = cin.nextByte();
                }while(optIns != 1 && optIns != 2 && optIns != 0);
                if(optIns == 1)bdGestion.insertar();
                if(optIns == 2)bdGestion.insertar();
                break;
            default:
                System.out.println("Ocurrio un error.");
        }
    }

    public static void bdSettings(GestionBD bdGestion){
        System.out.println("""
                        Opciones:
                        \t1.Crear Database.
                        \t2.Crear Tabla.
                        \t3.Ingresar Datos.
                        \t0.Salir.
                        """);
        System.out.print("Ingresar Opcion: ");
        byte optdb = cin.nextByte();
        switch (optdb){
            case 0:
                break;
            case 1:
                System.out.println("Creando Base de datos...");
                bdGestion.crearBD();
                break;
            case 2:
                System.out.println("Creando Tabla...");
                bdGestion.crearTabla();
                break;
            case 3:
                System.out.println("...");
                bdGestion.insertar();
            default:
                System.out.println("Ocurrio un error.");
        }
    }
    public static void bd(){
        Scanner cin = new Scanner(System.in);
        System.out.println("Base de datos: ");
        if (bdName==null){
            System.out.print("Ingresar Nombre de Base de datos:");
            bdName = cin.next();
            Main.bdGestion.setBdName(bdName);
        }
        if(bdUser==null){
            System.out.print("Ingresar Usuario de Base de datos:");
            bdUser = cin.next();
            Main.bdGestion.setBdUser(bdUser);
        }
        if (bdPass==null){
            Console console = System.console();
            if (console == null){
                System.out.print("Ingresar Contraseña de Base de datos:");
                bdPass = cin.next();
            }else{
                bdPass = new String(console.readPassword("Ingresara Contraseña de Base de datos: ", "*"));
            }
            Main.bdGestion.setBdPassword(bdPass);
        }
    }
}
package edu.codoacodo;

import edu.codoacodo.service.OradorService;
import edu.codoacodo.service.TicketService;

import java.util.Scanner;

public class Menu {

    private OradorService oradorService;
    private TicketService ticketService;
    private boolean isSQL;
    private String usingPersistence;
    private String InvalidOpt = "\t\t\t\tLa opcion ingresada no corresponde a un menu accesible.";
    private byte opt;
    Scanner cin = new Scanner(System.in);

    public Menu(OradorService oradorService, TicketService ticketService) {
        this.oradorService = oradorService;
        this.ticketService = ticketService;
        this.usingPersistence = "Almacenamiento en memoria.";
        this.isSQL = false;
        oradorService.setSQL(isSQL);
        ticketService.setSQL(isSQL);
    }

    private byte input(){
        System.out.println("Opciones: ");
        System.out.println("\t1.Seleccionar metodo de persistencia de datos" + "\t\t Metodo Actual: " + usingPersistence);
        System.out.println(
                """
                        \t2.Generacion de datos.
                        \t3.Lectura de datos.
                        \t4.Actualizar dato por id.
                        \t5.Eliminar dato por id.
                        \t6.Gestionar base de datos.
                        \t0.Salir."""
        );
        System.out.print("Ingresar Opcion: ");
        return cin.nextByte();
    }

    private void menuPersistencia(){
        byte optPers;
        if (!isSQL){
            System.out.println(
                    "\nSeleccionar tipo de persistencia de datos:\n"+
                            "\t1.Almacenamiento en memoria.[x]\n" +
                            "\t2.Almacenamiento en base de datos SQL.[ ]\n" +
                            "\t0.Volver"

            );
        }else{
            System.out.println(
                    "\nSeleccionar tipo de persistencia de datos:\n"+
                            "\t1.Almacenamiento en memoria.[ ]\n" +
                            "\t2.Almacenamiento en base de datos SQL.[x]\n"+
                            "\t0.Volver"
            );
        }
        System.out.print("Ingresar Opcion: ");
        optPers = cin.nextByte();
        switch (optPers){
            case 0:
                return;
            case 1:
                if(!this.isSQL) break;
                this.isSQL = false;
                usingPersistence = "Almacenamiento en memoria.";
                oradorService.setSQL(isSQL);
                ticketService.setSQL(isSQL);
                break;
            case 2:
                if(this.isSQL) break;
                this.isSQL = true;
                usingPersistence = "Almacenamiento en base de datos SQL.";
                oradorService.setSQL(isSQL);
                ticketService.setSQL(isSQL);
                break;
            default:
                System.out.println(InvalidOpt);
                menuPersistencia();
        }

    }

    private void menuGenDatos(){
        byte optGen;
        int cantidad;
        System.out.println("""
                \t1.Generar Datos Genericos Orador.
                \t2.Generar Datos Genericos Ticket.
                \t3.Generar Datos Genericos en Orador y Ticket.
                \t0.Volver.""");
        System.out.print("Ingresar Opcion: ");
        optGen = cin.nextByte();
        System.out.print("Ingresar Cantidad: ");
        switch (optGen){

            case 0:
                return;
            case 1:
                cantidad = cin.nextInt();
                GenData dataOrador = new GenData(oradorService);
                dataOrador.genOrador(cantidad);
                break;
            case 2:
                cantidad = cin.nextInt();
                GenData dataTicket = new GenData(ticketService);
                dataTicket.genTicket(cantidad);
                break;
            case 3:
                cantidad = cin.nextInt();
                GenData allData = new GenData(oradorService,ticketService);
                allData.all(cantidad);
                break;
            default:
                System.out.println(InvalidOpt);
                menuGenDatos();
        }
    }

    private void menuGetData(){
        byte optRead;
        byte optOrador;
        byte optTicket;
        int ticketID;
        int oradorID;
        System.out.println("""
                \t1.Imprimir Datos Genericos Orador.
                \t2.Imprimir Datos Genericos Ticket.
                \t3.Imprimir Todos.
                \t0.Volver."""
        );
        System.out.print("Ingresar Opcion: ");
        optRead = cin.nextByte();

        switch (optRead){
            case 0:
                return;
            case 1:
                ReadData readDataOrador = new ReadData(oradorService);
                do{
                    System.out.println(
                            "\t1.Imprimir Todos los datos\n" +
                            "\t2.Imprimir por id"
                    );
                    System.out.print("Ingresar Opcion: ");
                    optOrador = cin.nextByte();
                    if(optOrador == 1)readDataOrador.orador();
                    if(optOrador == 2){
                        System.out.print("Ingresar id: ");
                        oradorID = cin.nextInt();
                        readDataOrador.orador(oradorID);
                    }
                }while(optOrador != 1 && optOrador != 2);
                break;
            case 2:
                ReadData readDataTicket = new ReadData(ticketService);
                do {
                    System.out.println(
                            "\t1.Imprimir Todos los datos\n" + "\t2.Imprimir por id"
                    );
                    System.out.print("Ingresar Opcion: ");
                    optTicket = cin.nextByte();
                    if (optTicket == 1) readDataTicket.ticket();
                    if (optTicket == 2) {
                        System.out.print("Ingresar id: ");
                        ticketID = cin.nextInt();
                        readDataTicket.ticket(ticketID);
                    }
                }while (optTicket != 1 && optTicket != 2);
                break;
            case 3:
                ReadData readAllData = new ReadData(oradorService,ticketService);
                break;
            default:
                System.out.println(InvalidOpt);
                menuGetData();
        }
    }

    private void menuDeleteData(){
        byte opt;
        int id;
        System.out.print("""
                \t1.Lista Oradores.
                \t2.Lista Tickets.
                Ingresar Opcion: """);
        opt = cin.nextByte();
        System.out.print("Ingresar id: ");
        id = cin.nextInt();
        switch (opt){
            case 1:
                oradorService.deleteOrador(id);
                break;
            case 2:
                ticketService.deleteTicket(id);
                break;
            default:
                System.out.println(InvalidOpt);
                menuGetData();
        }


    }

    private void menuUpdateData(){
        byte opt;
        int id;
        System.out.print("""
                \t1.Lista Oradores.
                \t2.Lista Tickets.
                Ingresar Opcion: """);
        opt = cin.nextByte();
        System.out.print("Ingresar id: ");
        id = cin.nextInt();
        switch (opt){
            case 1:
                oradorService.updateOrador(id);
                break;
            case 2:
                ticketService.updateTicket(id);
                break;
            default:
                System.out.println(InvalidOpt);
                menuUpdateData();
        }
    }

    public byte runMenu(){
        opt = input();

        switch (opt){
            case 0:
                return 0;
            case 1:
                menuPersistencia();
                if(isSQL)return 8;
                opt = -1;
                break;
            case 2:
                menuGenDatos();
                opt = -1;
                break;
            case 3:
                menuGetData();
                opt = -1;
                break;
            case 4:
                menuUpdateData();
                opt = -1;
                break;
            case 5:
                menuDeleteData();
                opt = -1;
                break;
            case 6:
                if(isSQL){
                    Main.bdSettings();
                }else {
                    System.out.println("Cambiar Metodo de almacenamiento a 'Base de datos SQL'.");
                }
                break;
            default:
                System.out.println(InvalidOpt);
                opt = runMenu();
        }

        return opt;
    }



}

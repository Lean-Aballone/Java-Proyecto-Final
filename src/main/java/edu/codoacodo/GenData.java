package edu.codoacodo;

import edu.codoacodo.domain.Orador;
import edu.codoacodo.domain.Ticket;
import edu.codoacodo.service.OradorService;
import edu.codoacodo.service.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenData {

    private OradorService oradorService;
    private TicketService ticketService;
    private List<Orador> oradorData = new ArrayList<>();
    private List<Ticket> ticketData = new ArrayList<>();

    public GenData(OradorService oradorService) {
        this.oradorService = oradorService;
    }

    public GenData(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public GenData(OradorService oradorService, TicketService ticketService){
        this.oradorService = oradorService;
        this.ticketService = ticketService;
    }

    public void genOrador(int cantidad){
        for(int i = 1; i <= cantidad; i++){
            oradorData.add(new Orador("Nombre"+i,"Apellido"+i,"ComentarioGenerico"+i));
        }
        saveMethod();
    }

    public void genTicket(int cantidad){
        for(byte i = 1; i <= cantidad; i++){
            ticketData.add(new Ticket("NombreT"+i,"ApellidoT"+i,"Email"+i,i,"Trainee"));
        }
        saveMethod(ticketData);
    }

    private void saveMethod(List<Ticket> ticketData){
        for(int item=0; ticketData.size() > item; item++) {
            ticketService.saveTicket(ticketData.get(item));
        }
        //if(Main.bdGestion.isConnected())Main.bdGestion.setBdTable("");
    }

    private void saveMethod(){
        for(int item=0; oradorData.size() > item; item++) {
            oradorService.saveOrador(oradorData.get(item));
        }
       // if(Main.bdGestion.isConnected())Main.bdGestion.setBdTable("");
    }

    public void all(int cantidad){
        if(Main.bdGestion.isConnected())Main.bdGestion.setBdTable("oradores");
        genOrador(cantidad);
        if(Main.bdGestion.isConnected())Main.bdGestion.setBdTable("tickets");
        genTicket(cantidad);
    }

    public void ingresoManualOrador(){
        Scanner cin = new Scanner(System.in);
        String n,s,c;
        System.out.println("Carga de datos Orador.");
        System.out.print("Ingresar Nombre: ");
        n = cin.nextLine();
        System.out.print("Ingresar Apellido: ");
        s = cin.nextLine();
        System.out.print("Ingresar Comentario: ");
        c = cin.nextLine();
        oradorService.saveOrador(new Orador(n,s,c));
    }

    public void ingresoManualTicket(){
        Scanner cin = new Scanner(System.in);
        String n,s,m,c;
        byte q;
        System.out.println("Carga de datos Ticket.");
        System.out.print("Ingresar Nombre: ");
        n = cin.nextLine();
        System.out.print("Ingresar Apellido: ");
        s = cin.nextLine();
        System.out.print("Ingresar Mail: ");
        m = cin.nextLine();
        System.out.print("Ingresar Cantidad de tickets: ");
        q = cin.nextByte();
        System.out.print("Ingresar Categoria: ");
        c = cin.next();
        ticketService.saveTicket(new Ticket(n,s,m,q,c));
    }


}

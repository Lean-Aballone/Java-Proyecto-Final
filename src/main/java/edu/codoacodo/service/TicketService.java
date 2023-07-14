package edu.codoacodo.service;

import edu.codoacodo.Main;
import edu.codoacodo.domain.Ticket;
import edu.codoacodo.infrastucture_persistence.TicketRepository;

import java.util.Objects;
import java.util.Scanner;

public class TicketService {

    private boolean isSQL;
    private TicketRepository repository = new TicketRepository();

    public void setSQL(boolean SQL) {
        isSQL = SQL;
        repository.setSQL(isSQL);
    }

    private boolean usingTable(){
        if(Objects.equals(Main.bdGestion.getBdTable(),"tickets"))return false;
        Scanner cin = new Scanner(System.in);
        byte opt;
        do{
            System.out.println("Utilizar Tabla Predefinida?");
            System.out.println("\t1.Si\n\t2.No");
            System.out.print("Opcion: ");
            opt = cin.nextByte();
            if(opt == 1) return true;
        }while(opt != 2);
        return false;
    }

    public void saveTicket(Ticket ticket){

        repository.saveTicket(ticket);
    }

    public Ticket loadTicket(int id){
        if(isSQL ){
            if(usingTable()) Main.bdGestion.setBdTable("tickets");
        }
        return repository.loadTicket(id);
    }

    public void printAllTicketList(){
//        if(isSQL){
//            if(usingTable()) Main.bdGestion.setBdTable("tickets");
//        }
        repository.printAllTicketList();}

    public void deleteTicket(int id) {
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("tickets");
        }
        repository.deleteTicket(id);
    }

    public void updateTicket(int id) {
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("tickets");
        }
        repository.updateTicket(id);
    }
}

package edu.codoacodo;

import edu.codoacodo.domain.Orador;
import edu.codoacodo.domain.Ticket;
import edu.codoacodo.service.OradorService;
import edu.codoacodo.service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class GenData {

    private OradorService oradorService;

    private TicketService ticketService;
    private int cantidad;
    private List<Orador> oradorData = new ArrayList<>();
    private List<Ticket> ticketData = new ArrayList<>();

    public GenData(OradorService oradorService) {
        this.oradorService = oradorService;
        //genOrador(cantidad);
    }

    public GenData(TicketService ticketService){
        this.ticketService = ticketService;
        //genTicket(cantidad);
    }

    public GenData(OradorService oradorService, TicketService ticketService){
        this.oradorService = oradorService;
        this.ticketService = ticketService;
        //genOrador(cantidad);
        //genTicket(cantidad);
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
            //System.out.println(oradorData.get(item));
            ticketService.saveTicket(ticketData.get(item));
        }
    }

    private void saveMethod(){
        for(int item=0; oradorData.size() > item; item++) {
            //System.out.println(oradorData.get(item));
            oradorService.saveOrador(oradorData.get(item));
        }
    }

    public void all(int cantidad){
        genOrador(cantidad);
        genTicket(cantidad);

    }


}

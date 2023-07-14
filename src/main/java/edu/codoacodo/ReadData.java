package edu.codoacodo;

import edu.codoacodo.service.OradorService;
import edu.codoacodo.service.TicketService;

public class ReadData {
    private OradorService oradorService;
    private TicketService ticketService;



    public ReadData(OradorService oradorService) {
        this.oradorService = oradorService;
    }

    public ReadData(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public ReadData(OradorService oradorService, TicketService ticketService){
        this.oradorService = oradorService;
        this.ticketService = ticketService;
        System.out.println("Oradores: ");
        orador();
        System.out.println("Tickets:");
        ticket();
    }

    public void orador(){
        oradorService.printAllOradorList();
    }
    public void orador(int id){
        System.out.println(oradorService.loadOrador(id));
    }

    public void ticket(){ ticketService.printAllTicketList(); }
    public void ticket(int id){
        System.out.println(ticketService.loadTicket(id));
    }

}

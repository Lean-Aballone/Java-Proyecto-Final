package edu.codoacodo.service;

import edu.codoacodo.domain.Ticket;
import edu.codoacodo.infrastucture_persistence.TicketRepository;

public class TicketService {

    private boolean isSQL;
    private TicketRepository repository = new TicketRepository();

    public void setSQL(boolean SQL) {
        isSQL = SQL;
        repository.setSQL(isSQL);
    }

    public void saveTicket(Ticket ticket){
        repository.saveTicket(ticket);
    }

    public Ticket loadTicket(int id){
        return repository.loadTicket(id);
    }

    public void printAllTicketList(){repository.printAllTicketList();}

    public void deleteTicket(int id) {
        repository.deleteTicket(id);
    }

    public void updateTicket(int id) {
        repository.updateTicket(id);
    }
}

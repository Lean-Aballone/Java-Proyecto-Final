package edu.codoacodo.infrastucture_persistence;

import edu.codoacodo.domain.Ticket;
import edu.codoacodo.infrastucture_persistence.memory.PersistenceTicketImpl;

public class TicketRepository{

    private boolean isSQL;
    private IPersistenceTicket persistence;

    public void setSQL(boolean isSQL) {
        this.isSQL = isSQL;
        setPersistence();
    }

    private void setPersistence(){
        if(!isSQL){
            persistence = new edu.codoacodo.infrastucture_persistence.memory.PersistenceTicketImpl();
        }else{
            persistence = new edu.codoacodo.infrastucture_persistence.mysql.PersistenceTicketImpl();
        }
    }
    public void saveTicket(Ticket ticket) {
        persistence.saveTicket(ticket);
    }
    public Ticket loadTicket(int id) {
        return persistence.getTicketById(id);
    }
    public void printAllTicketList(){
        persistence.printAllTicketList();
    }

    public void deleteTicket(int id) {
        persistence.deleteTicket(id);
    }

    public void updateTicket(int id) {
        persistence.updateTicket(id);
    }
}

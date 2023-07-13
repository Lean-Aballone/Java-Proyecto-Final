package edu.codoacodo.infrastucture_persistence;

import edu.codoacodo.domain.Ticket;

public interface IPersistenceTicket {
    void saveTicket(Ticket ticket);
    Ticket getTicketById(int id);
    void printAllTicketList();
    void deleteTicket(int id);
    void updateTicket(int id);
}

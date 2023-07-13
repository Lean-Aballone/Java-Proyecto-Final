package edu.codoacodo.infrastucture_persistence.memory;

import edu.codoacodo.domain.Ticket;
import edu.codoacodo.infrastucture_persistence.IPersistenceTicket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenceTicketImpl implements IPersistenceTicket {

    private final List<Ticket> bdTicket = new ArrayList<>();
    private int listSize(){
        return bdTicket.size();
    }
    @Override
    public void saveTicket(Ticket ticket) {
        ticket.setID(listSize());
        bdTicket.add(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        Ticket generic;
        if(bdTicket.size()-1 < id){
            System.out.println("El id no pertenece al limite de la lista.");
            return null;
        }

        try{
            generic = bdTicket.get(id);
        } catch (Exception e){
            System.out.println("El id no pudo ser cargado.");
            e.printStackTrace();
            return null;
        }
        if(generic != null) System.out.println("Ticket Cargado Correctamente.");
        return generic;
    }

    @Override
    public void printAllTicketList(){
        for (int i = 0; i < bdTicket.size();i++){
            System.out.println(bdTicket.get(i));
        }
    }

    @Override
    public void deleteTicket(int id){
        bdTicket.remove(id);
    }

    private byte updateTicketSelector(Scanner cin){
        System.out.println("""
                \t1.Modificar Nombre.
                \t2.Modificar Apellido.
                \t3.Modificar Email.
                \t4.Modificar Cantidad.
                \t5.Modificar Categoria.
                \t6.Modificar ID.
                \t0.Volver.
                """);
        System.out.print("Ingresar Opcion: ");

        return cin.nextByte();
    }

    @Override
    public void updateTicket(int id){
        Scanner cin = new Scanner(System.in);
        Ticket edit = bdTicket.get(id);
        boolean exit = false;
        while (!exit){
            switch (updateTicketSelector(cin)){
                case 0:
                    exit = true;
                    break;
                case 1:
                    System.out.print("Ingresar Nombre:");
                    edit.setName(cin.next());
                    bdTicket.set(id,edit);
                    System.out.println("Nombre Modificado Correctamente.");
                    break;
                case 2:
                    System.out.print("Ingresar Apellido:");
                    edit.setSurname(cin.next());
                    bdTicket.set(id,edit);
                    System.out.println("Apellido Modificado Correctamente.");
                    break;
                case 3:
                    System.out.print("Ingresar Email:");
                    edit.setEmail(cin.next());
                    bdTicket.set(id,edit);
                    System.out.println("Email Modificado Correctamente.");
                    break;
                case 4:
                    System.out.print("Ingresar Cantidad:");
                    edit.setQuantity(cin.nextByte());
                    bdTicket.set(id,edit);
                    System.out.println("Cantidad Modificada Correctamente.");
                    break;
                case 5:
                    System.out.print("Ingresar Categoria:");
                    edit.setCategory(cin.next());
                    bdTicket.set(id,edit);
                    System.out.println("Categoria Modificada Correctamente.");
                    break;
                case 6:
                    System.out.print("Ingresar ID:");
                    edit.setID(cin.nextInt());
                    bdTicket.set(id,edit);
                    System.out.println("ID Modificado Correctamente.");
                    break;
                default:
                    System.out.println("\"\\t\\t\\t\\tLa opcion ingresada no corresponde a un menu accesible.\"");
                    updateTicketSelector(cin);
            }
        }

    }

}

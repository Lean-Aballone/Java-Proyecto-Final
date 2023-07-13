package edu.codoacodo.infrastucture_persistence.memory;

import edu.codoacodo.domain.Orador;
import edu.codoacodo.infrastucture_persistence.IPersistenceOrador;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersistenceOradorImpl implements IPersistenceOrador {
    private final List<Orador> bdOrador = new ArrayList<>();

    private int listSize(){
        return bdOrador.size();
    }
    @Override
    public void saveOrador(Orador orador) {
        orador.setID(listSize());
        bdOrador.add(orador);
    }

    @Override
    public Orador getOradorById(int id) {
        Orador generic;
        if(bdOrador.size()-1 < id || id < 0){
            System.out.println("El id no pertenece al limite de la lista.");
            return null;
        }

        try{
            generic = bdOrador.get(id);
        } catch (Exception e){
            System.out.println("El id no pudo ser cargado.");
            e.printStackTrace();
            return null;
        }
        if(generic != null) System.out.println("Orador Cargado Correctamente.");
        return generic;
    }

    @Override
    public void printAllOradorList() {
        for (int i = 0; i < bdOrador.size(); i++) {
            System.out.println(bdOrador.get(i));
        }
    }

    @Override
    public void deleteOrador(int id) {
        bdOrador.remove(id);
    }
    private byte updateOradorSelector(Scanner cin){
        System.out.println("""
                \t1.Modificar Nombre.
                \t2.Modificar Apellido.
                \t3.Modificar Comentario.
                \t4.Modificar ID.
                \t0.Volver.
                """);
        System.out.print("Ingresar Opcion: ");

        return cin.nextByte();
    }
    @Override
    public void updateOrador(int id) {
        Scanner cin = new Scanner(System.in);
        Orador edit = bdOrador.get(id);
        boolean exit = false;
        while(!exit) {
            switch (updateOradorSelector(cin)) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    System.out.print("Ingresar Nombre: ");
                    edit.setName(cin.next());
                    bdOrador.set(id, edit);
                    System.out.println("Nombre Modificado Correctamnte.");
                    break;
                case 2:
                    System.out.print("Ingresar Apellido: ");
                    edit.setSurname(cin.next());
                    bdOrador.set(id, edit);
                    System.out.println("Apellido Modificado Correctamnte.");
                    break;
                case 3:
                    System.out.print("Ingresar Comentario: ");
                    edit.setComment(cin.next());
                    bdOrador.set(id, edit);
                    System.out.println("Comentario Modificado Correctamnte.");
                    break;
                case 4:
                    System.out.print("Ingresar ID: ");
                    edit.setID(cin.nextInt());
                    bdOrador.set(id, edit);
                    System.out.println("ID Modificado Correctamnte.");
                    break;
                default:
                    System.out.println("\"\\t\\t\\t\\tLa opcion ingresada no corresponde a un menu accesible.\"");
                    updateOradorSelector(cin);
            }
        }
    }

}

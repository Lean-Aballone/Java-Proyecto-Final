package edu.codoacodo.service;

import edu.codoacodo.Main;
import edu.codoacodo.domain.Orador;
import edu.codoacodo.infrastucture_persistence.OradorRepository;

import java.util.Scanner;

public class OradorService {

    private boolean isSQL;

    private OradorRepository repository = new OradorRepository();;
    public void setSQL(boolean SQL) {
        isSQL = SQL;
        repository.setSQL(isSQL);
    }

    private boolean usingTable(){
        Scanner cin = new Scanner(System.in);
        byte opt;
        do{
            System.out.println("Utilizar Tabla Predefinida?");
            System.out.println("\t1.Si\n\t2.No");
            System.out.print("Opcion: ");
            opt = cin.nextByte();
            if(opt == 1) return true;
        }while (opt !=2);
        return false;
    }

    public void saveOrador(Orador orador){
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("oradores");
        }
        repository.saveOrador(orador);
    }

    public Orador loadOrador(int id){
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("oradores");
        }
        return repository.loadOrador(id);
    }

    public void printAllOradorList(){
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("oradores");
        }
        repository.printAllOradorList();
    }

    public void deleteOrador(int id){
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("oradores");
        }
        repository.deleteOrador(id);
    }

    public void updateOrador(int id) {
        if(isSQL){
            if(usingTable()) Main.bdGestion.setBdTable("oradores");
        }
        repository.updateOrador(id);
    }
}

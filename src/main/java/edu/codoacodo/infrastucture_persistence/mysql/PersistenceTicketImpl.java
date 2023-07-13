package edu.codoacodo.infrastucture_persistence.mysql;

import edu.codoacodo.Main;
import edu.codoacodo.domain.Ticket;
import edu.codoacodo.infrastucture_persistence.IPersistenceTicket;
import edu.codoacodo.infrastucture_persistence.mysql.gestionbd.GestionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistenceTicketImpl implements IPersistenceTicket {
    private GestionBD bd = Main.bdGestion;
    @Override
    public void saveTicket(Ticket ticket) {
        String dataToSave = bd.insertar();
        if(dataToSave == null)return;
        //dataToSave += "(nombre,apellido,email,cantidad,categoria,date) VALUES";
        try {
            Statement statement = bd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(dataToSave);
            //resultSet.moveToInsertRow();
            //resultSet.updateObject(ticket);
            //statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Ticket getTicketById(int id) {
        System.out.println("Hola Mysql");
        return null;
    }

    @Override
    public void printAllTicketList() {
        System.out.println("Hola Mysql");
    }

    @Override
    public void deleteTicket(int id) {
        System.out.println("Delete mysql");
    }

    @Override
    public void updateTicket(int id) {
        System.out.println("Update mysql");
    }
}

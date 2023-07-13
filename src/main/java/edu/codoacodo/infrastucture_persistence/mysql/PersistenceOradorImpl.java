package edu.codoacodo.infrastucture_persistence.mysql;

import edu.codoacodo.Main;
import edu.codoacodo.domain.Orador;
import edu.codoacodo.infrastucture_persistence.IPersistenceOrador;
import edu.codoacodo.infrastucture_persistence.mysql.gestionbd.GestionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistenceOradorImpl implements IPersistenceOrador {
    private GestionBD bd = Main.bdGestion;


    private void gestionBD(){

    }

    @Override
    public void saveOrador(Orador orador) {

        String dataToSave = bd.insertar();
        if(dataToSave == null)return;
        //dataToSave += "(nombre,apellido,comment) VALUES";
        try {
            Statement statement = bd.getConnection().createStatement();
            //ResultSet resultSet = statement.executeUpdate();
            statement.execute("USE " + bd.getBdName());
            String test = "INSERT INTO "+ bd.getBdTable() +
                    "(nombre,apellido,comment) VALUES ('" + orador.getName()+"','" +
                    orador.getSurname()+"','"+ orador.getComment()+"')";
            statement.executeUpdate(test);
//            PreparedStatement preparedStatement = bd.getConnection().prepareStatement(dataToSave+" (nombre,apellido,comment) VALUES");
//            preparedStatement.setString(0,"nombre");
//            preparedStatement.setString(1,"nomabre");
//            preparedStatement.setString(3,"text");
//            int asd = preparedStatement.executeUpdate();


//            resultSet.moveToInsertRow();
//            resultSet.updateString("nombre",orador.getName());
//            resultSet.updateString("apellido",orador.getSurname());
//            resultSet.updateString("comment",orador.getComment());
//            resultSet.insertRow();
        }catch (SQLException e){
            e.printStackTrace();
        }


        //System.out.println("Saved Mysql");
    }

    @Override
    public Orador getOradorById(int id) {

        System.out.println("Get Mysql");
        return null;
    }

    @Override
    public void printAllOradorList() {
        System.out.println("PrintALl Mysql");

    }
    @Override
    public void deleteOrador(int id) {
        System.out.println("Delete Mysql");
    }

    @Override
    public void updateOrador(int id) {
        System.out.println("Update Mysql");
    }
}

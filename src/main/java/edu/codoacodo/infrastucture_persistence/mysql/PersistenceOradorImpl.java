package edu.codoacodo.infrastucture_persistence.mysql;

import edu.codoacodo.Main;
import edu.codoacodo.domain.Orador;
import edu.codoacodo.infrastucture_persistence.IPersistenceOrador;
import edu.codoacodo.infrastucture_persistence.mysql.gestionbd.GestionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PersistenceOradorImpl implements IPersistenceOrador {
    private GestionBD bd = Main.bdGestion;

    @Override
    public void saveOrador(Orador orador) {
        if(!bd.isConnected()){
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "INSERT INTO "+ bd.getBdTable() +
                    "(nombre,apellido,comment) VALUES ('" + orador.getName()+"','" +
                    orador.getSurname()+"','"+ orador.getComment()+"')";
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Orador getOradorById(int id) {
        if(!bd.isConnected()){
            System.out.println("\t\tConexion con base de datos no establecida.");
            return null;
        }
        bd.checker(bd);
        Orador generic = new Orador();
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "SELECT * FROM " + bd.getBdTable() + " WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(SQL);
            if(!resultSet.next()){
                System.out.println("\t\tEl ID: " + id + " no pertence a un orador en lista." );
            }else {
                generic.setID(resultSet.getInt("id"));
                generic.setName(resultSet.getString("nombre"));
                generic.setSurname(resultSet.getString("apellido"));
                generic.setComment(resultSet.getString("comment"));
                generic.setRegistration(resultSet.getObject("date", LocalDateTime.class));
                return generic;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void printAllOradorList() {
        if(!bd.isConnected()){
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "SELECT * FROM " + bd.getBdTable();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                System.out.println(
                        "Orador{" +
                                "name='" + resultSet.getString("nombre") + '\'' +
                                ", surname='" + resultSet.getString("apellido") + '\'' +
                                ", comment='" + resultSet.getString("comment") + '\'' +
                                ", registration=" + resultSet.getObject("date", LocalDateTime.class) +
                                ", ID=" + resultSet.getInt("id") +
                                '}'
                );
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


    }
    @Override
    public void deleteOrador(int id) {
        if(!bd.isConnected()){
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "DELETE FROM " + bd.getBdTable() + " WHERE id = " + id;
            int result = statement.executeUpdate(SQL);
            if(result == 0){
                System.out.println("\t\tEl ID: " + id + " no pertence a un orador en lista." );
                return;
            }
            System.out.println("Orador Eliminado.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrador(int id) {
        if(!bd.isConnected()){
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            byte opt;
            String SQL;
            do {
                System.out.println("ID a modificar: " + id + "\nOpciones: ");
                System.out.println("""
                        \t1.Modificar Nombre.
                        \t2.Modificar Apellido.
                        \t3.Modificar Comentario.
                        \t0.Salir.""");
                Scanner cin = new Scanner(System.in);
                System.out.print("Ingresar Opcion: ");
                opt = cin.nextByte();
                switch (opt){
                    case 0:break;
                    case 1:
                        System.out.print("Ingresar Nuevo Nombre: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET nombre = '" + cin.nextLine() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un orador en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    case 2:
                        System.out.print("Ingresar Nuevo Apellido: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET apellido = '" + cin.nextLine() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un orador en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    case 3:
                        System.out.print("Ingresar Nuevo Comentario: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET comment = '" + cin.nextLine() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un orador en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    default:
                        System.out.println("El numero Ingresado no pertenece a una opcion elegible.");
                }

            }while (opt != 0);



        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

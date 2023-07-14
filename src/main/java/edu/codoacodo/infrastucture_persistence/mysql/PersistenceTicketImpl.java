package edu.codoacodo.infrastucture_persistence.mysql;

import edu.codoacodo.Main;
import edu.codoacodo.domain.Ticket;
import edu.codoacodo.infrastucture_persistence.IPersistenceTicket;
import edu.codoacodo.infrastucture_persistence.mysql.gestionbd.GestionBD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PersistenceTicketImpl implements IPersistenceTicket {
    private GestionBD bd = Main.bdGestion;

    @Override
    public void saveTicket(Ticket ticket) {
        if (!bd.isConnected()) {
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "INSERT INTO " + bd.getBdTable() +
                    "(nombre,apellido,email,cantidad,categoria) VALUES ('"
                    + ticket.getName() + "','"
                    + ticket.getSurname() + "','"
                    + ticket.getEmail() + "','"
                    + ticket.getQuantity() + "','"
                    + ticket.getCategory() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getTicketById(int id) {
        if (!bd.isConnected()) {
            System.out.println("\t\tConexion con base de datos no establecida.");
            return null;
        }
        bd.checker(bd);
        Ticket generic = new Ticket();
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "SELECT * FROM " + bd.getBdTable() + " WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(SQL);
            if (!resultSet.next()) {
                System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista.");
            } else {
                generic.setID(resultSet.getInt("id"));
                generic.setName(resultSet.getString("nombre"));
                generic.setSurname(resultSet.getString("apellido"));
                generic.setEmail(resultSet.getString("email"));
                generic.setQuantity(resultSet.getByte("cantidad"));
                generic.setCategory(resultSet.getString("categoria"));
                generic.setRegistration(resultSet.getObject("date", LocalDateTime.class));
                return generic;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void printAllTicketList() {
        if (!bd.isConnected()) {
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "SELECT * FROM " + bd.getBdTable();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                System.out.println(
                        "Ticket{" +
                                "name='" + resultSet.getString("nombre") + '\'' +
                                ", surname='" + resultSet.getString("apellido") + '\'' +
                                ", email='" + resultSet.getString("email") + '\'' +
                                ", quantity=" + resultSet.getByte("cantidad") +
                                ", category='" + resultSet.getString("categoria") + '\'' +
                                ", registration=" + resultSet.getObject("date", LocalDateTime.class) +
                                ", ID=" + resultSet.getInt("id") +
                                '}'

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteTicket(int id) {
        if (!bd.isConnected()) {
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            String SQL = "DELETE FROM " + bd.getBdTable() + " WHERE id = " + id;
            int result = statement.executeUpdate(SQL);
            if (result == 0) System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista.");
            if (result != 0) System.out.println("Ticket Eliminado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTicket(int id) {
        if (!bd.isConnected()) {
            System.out.println("\t\tConexion con base de datos no establecida.");
            return;
        }
        bd.checker(bd);
        try {
            Statement statement = bd.getConnection().createStatement();
            statement.execute("USE " + bd.getBdName());
            byte opt;
            byte opt_aux;
            String SQL = "";
            String categoria = "";
            do{
                System.out.println("ID a modificar: " + id + "\nOpciones: ");
                System.out.println("""
                        \t1.Modificar Nombre.
                        \t2.Modificar Apellido.
                        \t3.Modificar Mail.
                        \t4.Modificar Cantidad.
                        \t5.Modificar Categoria
                        \t0.Salir.""");
                Scanner cin = new Scanner(System.in);
                System.out.print("Ingresar Opcion: ");
                opt = cin.nextByte();
                cin.nextLine();
                switch (opt){
                    case 0: break;
                    case 1:
                        System.out.print("Ingresar Nuevo Nombre: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET nombre = '" + cin.nextLine() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    case 2:
                        System.out.print("Ingresar Nuevo Apellido: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET apellido = '" + cin.nextLine() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    case 3:
                        System.out.print("Ingresar Nuevo Mail: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET email = '" + cin.nextLine() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    case 4:
                        System.out.print("Ingresar Nueva Cantidad: ");
                        SQL = "UPDATE " + bd.getBdTable() + " SET cantidad = '" + cin.nextByte() + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    case 5:
                        System.out.println("""
                                Seleccionar Categoria:
                                \t\t1.Sin Categoria.
                                \t\t2.Estudiante.
                                \t\t3.Trainee.
                                \t\t4.Junior.
                                \t\t0.Cancelar.""");
                        System.out.print("Ingresar Opcion: ");
                        opt_aux = cin.nextByte();
                        cin.nextLine();
                        switch (opt_aux){
                            case 0: break;
                            case 1:
                                categoria = "";
                                break;
                            case 2:
                                categoria = "Estudiante";
                                break;
                            case 3:
                                categoria = "Trainee";
                                break;
                            case 4:
                                categoria = "Junior";
                                break;
                            default:
                                System.out.println("El numero Ingresado no pertenece a una opcion elegible.");
                                opt_aux = 0;
                        }
                        if (opt_aux == 0) break;
                        SQL = "UPDATE " + bd.getBdTable() + " SET categoria = '" + categoria + "' WHERE id = " + id;
                        if(statement.executeUpdate(SQL) == 0){
                            System.out.println("\t\tEl ID: " + id + " no pertence a un ticket en lista." );
                            return;
                        }
                        System.out.println("Dato Actualizado Correctamente.");
                        break;
                    default:
                        System.out.println("El numero Ingresado no pertenece a una opcion elegible.");
                }
            }while(opt != 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
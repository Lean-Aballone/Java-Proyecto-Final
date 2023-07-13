package edu.codoacodo.infrastucture_persistence.mysql.gestionbd;

import edu.codoacodo.Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrearBD{
    public static void crearDatabase(Connection connection, String bdName){
        String create = "CREATE DATABASE " + bdName;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String oradoresTable(String table){
        return """
                (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nombre VARCHAR(50) NOT NULL,
                    apellido VARCHAR(50) NOT NULL,
                    comment TEXT,
                    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;
    }
    private static String ticketsTable(String table){
        return """
                (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    nombre VARCHAR(50) NOT NULL,
                    apellido VARCHAR(50) NOT NULL,
                    email VARCHAR(50) NOT NULL,
                    cantidad TINYINT NOT NULL,
                    categoria VARCHAR(10),
                    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;
    }
    private static byte tableSwitch(String table){
        Scanner cin = new Scanner(System.in);
        boolean exit;
        System.out.println("""
                Seleccionar tipo de datos a almacenar.
                \t1.Oradores.
                \t2.Tickets.
                \t0.Salir.""");
        System.out.print("Ingresar Opcion: ");
        return cin.nextByte();
    }
    public static void crearTable(Connection connection, String bdTable){
        if(bdTable == null){
            Scanner cin = new Scanner(System.in);
            System.out.print("Ingresar Nombre de la tabla: ");
            bdTable = cin.next();
            Main.bdGestion.setBdTable(bdTable);
        }
        String table = "CREATE TABLE " + bdTable;
        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("USE "+Main.bdGestion.getBdName());
            byte opt = tableSwitch(table);
            if(opt == 1 || opt == 2 ){
                if(opt == 1){
                    table += oradoresTable(table);
                }else{
                    table += ticketsTable(table);
                }
                statement.executeUpdate(table);
                Main.bdGestion.setBdTable(null);
                bdTable = null;
            }else if(opt != 0){
                System.out.println("\t\t\tOpcion Invalida. Volver a intentar.\n");
                crearTable(connection,bdTable);
            }
//            statement.close();
//            connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

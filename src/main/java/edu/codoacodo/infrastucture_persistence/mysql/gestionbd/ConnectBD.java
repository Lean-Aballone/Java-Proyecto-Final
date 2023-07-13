package edu.codoacodo.infrastucture_persistence.mysql.gestionbd;

import edu.codoacodo.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectBD {
    private static String URL = "jdbc:mysql://localhost:3306/";
    public static Connection connect(String Nombre, String Password) throws SQLException {
        if(Nombre.isEmpty())Nombre = "root";
        if(Password.isEmpty())Password = "root";
        Main.bdGestion.setConnection(DriverManager.getConnection(URL,Nombre,Password));
        return DriverManager.getConnection(URL,Nombre,Password);
    }
    public static void setURL(String URL) {
        ConnectBD.URL = URL;
    }
}

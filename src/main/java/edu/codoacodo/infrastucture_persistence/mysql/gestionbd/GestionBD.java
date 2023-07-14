package edu.codoacodo.infrastucture_persistence.mysql.gestionbd;

import edu.codoacodo.Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionBD{


    private String bdName;
    private String bdURL;
    private String bdUser;
    private String bdPassword;
    private boolean isConnected;
    private Connection connection;
    private String bdTable;

    public GestionBD(){
    }
    public GestionBD(String bdName, String bdUser, String bdPassword){
        this.bdName = bdName;
        this.bdUser = bdUser;
        this.bdPassword = bdPassword;
        isConnected = false;
    }


    public void connectBD(){
        Scanner cin = new Scanner(System.in);
        System.out.println("""
                \t1.Utilizar URL Predeterminada.
                \t2.Ingresar URL.
                \t0.Volver.""");
        System.out.print("Ingresar Opcion: ");
        byte opt = cin.nextByte();
        if(opt == 0)return;
        if(opt == 2){
            System.out.print("Ingresar URL: ");
            this.bdURL = cin.next();
            ConnectBD.setURL(this.bdURL);
        }else if(opt != 1){
            System.out.println("\t\t\tOpcion Invalida.");
            connectBD();
        }
        try {
            this.connection = ConnectBD.connect(bdUser,bdPassword);
            isConnected = true;
        }catch (SQLException e){
            isConnected = false;
            e.printStackTrace();
        }
    }
    public void crearBD(){
        if (isConnected && !(bdName == null)){
            CrearBD.crearDatabase(connection,bdName);
        }else{
            if(bdName == null)return;
            connectBD();
        }
    }
    public void crearTabla(){
        if(isConnected){
            CrearBD.crearTable(connection,bdTable);
        }else{
            connectBD();
        }
    }

    public void checker(GestionBD bd){
        Scanner cin = new Scanner(System.in);
        if(bd.getBdName() == null){
            System.out.print("Ingresar base de datos a utilizar:");
            Main.bdGestion.setBdName(cin.next());
        }
        if(Main.bdGestion.getBdTable() == null){
            System.out.print("Ingresar Tabla a utilizar:");
            Main.bdGestion.setBdTable(cin.next());
        }
    }



    public void setBdName(String bdName) {
        this.bdName = bdName;
    }

    public void setBdURL(String bdURL) {
        this.bdURL = bdURL;
    }

    public void setBdUser(String bdUser) {
        this.bdUser = bdUser;
    }

    public void setBdPassword(String bdPassword) {
        this.bdPassword = bdPassword;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setBdTable(String bdTable) {
        this.bdTable = bdTable;
    }
    public String getBdName() {
        return bdName;
    }

    public String getBdURL() {
        return bdURL;
    }

    public String getBdUser() {
        return bdUser;
    }

    public String getBdPassword() {
        return bdPassword;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getBdTable() {
        return bdTable;
    }
}

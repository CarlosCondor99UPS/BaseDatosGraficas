package ec.edu.ups.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseDeDatos {

    
    private Connection conexionDb;
    private String url;
    private String user;
    private String password;

    public BaseDeDatos(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BaseDeDatos() {
    }

    public Connection getConexionDb() {
        return conexionDb;
    }

    public void setConexionDb(Connection conexionDb) {
        this.conexionDb = conexionDb;
    }
    
    public void conectar(){
        try {
            conexionDb=DriverManager.getConnection(url,user,password);
           if (conexionDb.isValid(5000)){
               System.out.println("Conexion Exitosa");
           }
        } catch (SQLException ex) {
            ex.printStackTrace();        
        }
    }
    public void desconectar(){
        try {
            if(conexionDb.isClosed()){
                conexionDb.close();
                System.out.println("Conexion Cerrada");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
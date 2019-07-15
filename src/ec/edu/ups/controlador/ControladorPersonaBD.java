package ec.edu.ups.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import ec.edup.ups.modelo.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author tians
 */
public class ControladorPersonaBD {
    private BaseDeDatos bd;

    public ControladorPersonaBD() {
        String url = "jdbc:postgresql://localhost:5432/MiBase";
        String user = "postgres";
        String password = "bowi8998";
        bd=new BaseDeDatos(url,user,password);
    }
    
    public void create(Persona persona){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String fechaBD = format.format(persona.getFechaNacimiento());
        String sql="INSERT INTO \"PERSONA\" VALUES('"+persona.getCedula()+"','"+persona.getNombre()+"','"
                                                    + persona.getApellido()+"',"+persona.getEdad()+",'"
                                                    + fechaBD+"','"+persona.getCelular()+"',"+persona.getSalario()+")";
        System.out.println(sql);
        bd.conectar();
        try{
            Statement sta=bd.getConexionDb().createStatement();
            sta.execute(sql);
            bd.desconectar();
        }catch (SQLException ex){
            System.out.println("Error"+ex);
            JOptionPane.showMessageDialog(null, "El usuario ya existe");
        }
    }
    public void eliminar(String cedula){
        String sql="DELETE FROM \"PERSONA\" WHERE \"PER_CEDULA\"='"+cedula+"'";
        System.out.println(sql);
        bd.conectar();
        try{
            Statement sta=bd.getConexionDb().createStatement();
            sta.execute(sql);
            bd.desconectar();
        }catch (SQLException ex){
            System.out.println("Error"+ex);
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    }
    public Persona buscar(String cedula){
        Persona persona=new Persona();
        String sql="SELECT * FROM \"PERSONA\" WHERE \"PER_CEDULA\"='"+cedula.trim()+"'";
        bd.conectar();
        try{
            Statement sta=bd.getConexionDb().createStatement();
            ResultSet rs=sta.executeQuery(sql);
            rs.next();
            persona.setCedula(rs.getString("PER_CEDULA"));
            persona.setNombre(rs.getString("PER_NOMBRE"));
            persona.setApellido(rs.getString("PER_APELLIDO"));
            persona.setEdad(rs.getInt("PER_EDAD"));
            persona.setFechaNacimiento(rs.getDate("PER_FECHA_NACIMIENTO"));
            persona.setCelular(rs.getString("PER_CELULAR"));
            persona.setSalario(rs.getDouble("PER_SALARIO"));
            persona.setGenero(rs.getString("PER_GENERO"));
            bd.desconectar();
            return persona;
        }catch (SQLException ex){
            System.out.println("Error"+ex);
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
        return null;
    }
}

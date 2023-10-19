/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.util.*;



 /***@author Snaider
 */
public class DataBase {
    //Cadena de conexión de la BD publicación en MYSQL
    private final String URL="jdbc:mysql://localhost:3307/Publicación";
    private final String user="Elnoni";
    private final String password="12345snaijder";
    
    private Connection conexion; //Para conexión con la BD

public DataBase() {
    try{
    conexion=DriverManager.getConnection(URL,user,password);
    System.out.println("Conexión Establecida");
    
    }catch (SQLException e){
   System.out.println("Error de conexión");
   e.printStackTrace();
   }
}
    //metodo para actualizar datos en la BD
public int Actualizar (String consulta){ 
    try{ 
        Statement st=conexion.createStatement();
        return st.executeUpdate(consulta);
        
    }catch (SQLException e) {
        e.printStackTrace();
        }
        return 0; 
    }
private List OrganizarDatos(ResultSet rs){
    List filas=new ArrayList();
    try{
        
        
        int numColumnas=rs.getMetaData() .getColumnCount();
        while(rs.next()){
            Map<String, Object> renglon=new HashMap();
            for(int i=1; i<=numColumnas; i++) {
                
                String nombreCampo=rs.getMetaData().getColumnName(i);
                Object valor=rs.getObject(nombreCampo);
                renglon.put(nombreCampo, valor);
                
            }
            filas.add(renglon);
        }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    return filas;
    }
public List Listar(String consulta){
    ResultSet rs=null;
    List resultado=new ArrayList();
    try{
        Statement st=conexion.createStatement();
        rs=st.executeQuery(consulta);
        resultado=OrganizarDatos(rs);
        
    }catch(SQLException e){
        System.out.println("No se realizó la consulta");
        e.printStackTrace();
        
    }
return resultado;
}
public void cerrarConexion(){
    try{
        conexion.close();
    }catch(SQLException e){
        e.printStackTrace();
    }
}


}
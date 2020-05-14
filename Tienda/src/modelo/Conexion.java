
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion { /* El conector a la base de datos, areas que se tienen desde la base de datos   */
    
    private final String base = "tienda";
    private final String user = "root";
    private final String password = "informaticdv2016";
    private final String url = "jdbc:mysql://localhost:3306/" + base; /*link de la brse de Datos  */
    private Connection con = null;
    
    public Connection getConexion()
    {
        
        try{
            Class.forName("com.mysql.jdbc.Driver"); /* Crea una instancia y cargua la clase en la memoria, almacena la referencia a la variable  */
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
            
        } catch(SQLException e) /*Nos oermite controlar el SQLException en caso de algun error   */
        {
            System.err.println(e); /* Existen varios ClassLoader diferentes para la aplicacion
            y diferentes pokiticas de carga de classes*/
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
      return con;  
    }

}

//DOCUMENTACIÓN POR: DIEGO VÁSQUEZ//

/*CLASE SQLUsuarios
    ¿Cuál es la funcionalidad de esta clase?
    Esta clase hace un enlace entre los atributos de la clase Usuarios y la Base de Datos,
    realiza las diversas transacciones de SQL, almacena y manipula información de los 
    diversos usuarios. 
*/

package modelo;

/*LIBRERIAS DE UTILIDAD*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class SqlUsuarios extends Conexion {

    /*MÉTODO REGISTRAR
        Tipo: Booleano
        Parámetros: Atributos de la Clase Usuario
        Funcionalidad:
        Su objetivo es Registrar; el método registrar contiene una instrucción SQL
        la cual también recibe como parámetros los atributos de la clase Usuario
        los cuales son llamados por el parámetro de este método "usr" de tipo Clase Usuario
        el capturador (try-catch) si realiza exitosamente la transacción retorna true lo cual 
        a su vez el método retornará true y en caso contario false. */
    
    public boolean registrar(Usuarios usr) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuarios (usuario, password, nombre, email, idTipo) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getEmail());
            ps.setInt(5, usr.getIdTipo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    /*MÉTODO LOGIN
        Tipo: Booleano
        Parámetros: Atributos de Clase Usuario
        Funcionalidad:
        Su objetivo es: Logear; realiza un recorrido a lo largo de la tabla usuarios y verifica si
        las credenciales ingresadas se encuentran o no en la base de datos.*/
    
    public boolean login(Usuarios usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT id, usuario, password, nombre, idTipo FROM usuarios WHERE usuario = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getPassword().equals(rs.getString(3))) {
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setIdTipo(rs.getInt(5));
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    /*MÉTODO existeUsuario
    Tipo: Entero
    Parametros: usuario, tipo String
    Funcionalidad:
    Su objetivo Verificar si un Usuario ya existe en la BD;  
    */
    public int existeUsuario(String usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();

    }

}


package modelo;
/*Herbert Leonel Dominguez Chavez 9959-19-5644*/
//* Se crea el constructor de Usuarios*//
public class Usuarios {
    //* se crea los atributos del constructor para procesar la informacion*//
    private int id;
    private String usuario;
    private String password;
    private String nombre;
    private String email;
    private String last_session;
    private int idTipo;
//*se crea funciones getters an setters de los atributos ya creados para obtener y cambiar información*//
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_session() {
        return last_session;
    }

    public void setLast_session(String last_session) {
        this.last_session = last_session;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    

}

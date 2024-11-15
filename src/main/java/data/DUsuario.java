package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DUsuario {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DUsuario() {
        this.connection = new databaseConnection(
                ConfigDb.getUser(),
                ConfigDb.getPassword(),
                ConfigDb.getHost(),
                ConfigDb.getPort(),
                ConfigDb.getDbName());
    }

    public void disconnect() {
        if (connection != null)
            connection.closeConnection();
    }

    // Método para guardar un nuevo usuario
    public String save(
            String nombre,
            String apellido,
            String ci,
            String direccionDomicilio,
            String email,
            String fechaNacimiento,
            String password,
            int rolId,
            Integer garanteId) throws SQLException {

        String query = "INSERT INTO Usuario(Nombre, Apellido, CI, Direccion_Domicilio, email, Fecha_Nacimiento, password, rol_id, garante_id) "
                + "values(?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, ci);
        ps.setString(4, direccionDomicilio);
        ps.setString(5, email);
        ps.setString(6, fechaNacimiento);
        ps.setString(7, password);
        ps.setInt(8, rolId);
        ps.setObject(9, garanteId, java.sql.Types.INTEGER); // Puede ser null si no hay garante

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice:" + "El usuario no se pudo insertar");
            throw new SQLException();
        }

        return "El usuario se insertó con éxito";
    }

    // Método para actualizar un usuario
    public String update(int id, String nombre, String apellido, String ci, String direccionDomicilio,
                         String email, String fechaNacimiento, String password, int rolId, Integer garanteId) throws SQLException {

        String query = "UPDATE Usuario SET Nombre=?, Apellido=?, CI=?, Direccion_Domicilio=?, email=?, Fecha_Nacimiento=?, password=?, rol_id=?, garante_id=? WHERE id=?";

        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, apellido);
        ps.setString(3, ci);
        ps.setString(4, direccionDomicilio);
        ps.setString(5, email);
        ps.setString(6, fechaNacimiento);
        ps.setString(7, password);
        ps.setInt(8, rolId);
        ps.setObject(9, garanteId, java.sql.Types.INTEGER); // Puede ser null si no hay garante
        ps.setInt(10, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice:" + "El usuario no se pudo actualizar");
            return "El usuario no se pudo actualizar";
        }
        return "El usuario se actualizó con éxito";
    }

    // Método para eliminar un usuario
    public String delete(int id) throws SQLException {
        String query = "DELETE FROM Usuario WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DUsuario.java dice: "
                    + "El usuario no se pudo eliminar, delete()");
            return "El usuario no se pudo eliminar";
        }
        return "El usuario se eliminó con éxito";
    }

    // Método para obtener todos los usuarios
    public List<String[]> findAll() throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            usuarios.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Nombre"),
                    set.getString("Apellido"),
                    set.getString("CI"),
                    set.getString("Direccion_Domicilio"),
                    set.getString("email"),
                    set.getString("Fecha_Nacimiento"),
                    set.getString("password"),
                    String.valueOf(set.getInt("rol_id")),
                    String.valueOf(set.getInt("garante_id"))
            });
        }
        return usuarios;
    }

    // Método para encontrar un usuario por id
    public String[] findOne(int id) throws SQLException {
        String[] usuario = null;
        String query = "SELECT * FROM Usuario WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            usuario = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Nombre"),
                    set.getString("Apellido"),
                    set.getString("CI"),
                    set.getString("Direccion_Domicilio"),
                    set.getString("email"),
                    set.getString("Fecha_Nacimiento"),
                    set.getString("password"),
                    String.valueOf(set.getInt("rol_id")),
                    String.valueOf(set.getInt("garante_id"))
            };
        }
        return usuario;
    }
}

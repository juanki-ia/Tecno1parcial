package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DServicio {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DServicio() {
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

    public String save(String nombre, String descripcion) throws SQLException {
        String query = "INSERT INTO Servicio(Nombre, Descripcion) values(?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, descripcion);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DServicio.java dice:" + "El servicio no se pudo insertar");
            throw new SQLException();
        }
        return "El servicio se insertó con éxito";
    }

    public String update(int id, String nombre, String descripcion) throws SQLException {
        String query = "UPDATE Servicio SET Nombre=?, Descripcion=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, nombre);
        ps.setString(2, descripcion);
        ps.setInt(3, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DServicio.java dice:" + "El servicio no se pudo actualizar");
            return "El servicio no se pudo actualizar";
        }
        return "El servicio se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM Servicio WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DServicio.java dice:" + "El servicio no se pudo eliminar");
            return "El servicio no se pudo eliminar";
        }
        return "El servicio se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> servicios = new ArrayList<>();
        String query = "SELECT * FROM Servicio";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            servicios.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Nombre"),
                    set.getString("Descripcion")
            });
        }
        return servicios;
    }

    public String[] findOne(int id) throws SQLException {
        String[] servicio = null;
        String query = "SELECT * FROM Servicio WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            servicio = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Nombre"),
                    set.getString("Descripcion")
            };
        }
        return servicio;
    }
}

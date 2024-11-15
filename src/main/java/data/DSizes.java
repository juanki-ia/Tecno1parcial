package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DSizes {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DSizes() {
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

    public String save(String name) throws SQLException {
        String query = "INSERT INTO sizes(name) VALUES(?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, name);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DSizes.java dice:" + "El tamaño no se pudo insertar");
            throw new SQLException();
        }
        return "El Tamaño se inserto con exito";
    }

    public String update(int id, String name) throws SQLException {
        String query = "UPDATE sizes SET name=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(2, id);
        ps.setString(1, name);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DSizes.java dice:" + "El tamaño no se pudo actualizar");
            return "El Tamaño no se pudo actualizar";
        }
        return "El Tamaño se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM sizes WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DSizes.java dice:"
                    + "El tamaño no se pudo eliminar, delete()");
            return "El Tamaño no se pudo eliminar ";
        }
        return "El Tamaño se elimino con exito ";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> sizes = new ArrayList<>();
        String query = "SELECT * FROM sizes";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            sizes.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name")
            });
        }
        return sizes;
    }

    public String[] findOne(int id) throws SQLException {
        String[] size = null;
        String query = "SELECT * FROM sizes WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            size = new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("name")
            };
        }
        return size;
    }

}
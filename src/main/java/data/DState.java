package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DState {

    private databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DState() {
        
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
        String query = "INSERT INTO states(name) values(?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, name);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DState.java dice:" + "El Estado no se pudo insertar");
            throw new SQLException();
        }
        return "El Estado se inserto con exito";
    }

    public String update(int id, String name) throws SQLException {
        String query = "UPDATE states SET name=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DState.java dice:" + "El Estado no se pudo modificar");
            // throw new SQLException();
        }
        return "El Estado se modifico con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM states WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DState.java dice:" + "ocurrio un error al eliminar un estado, delete()");
            // throw new SQLException();
            return "El Estado no se pudo eliminar";
        }
        return "El Estado se elimino con exito";
    }


    public List<String[]> findAll() throws SQLException {
        List<String[]> states = new ArrayList<>();
        String query = "SELECT * FROM states";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            states.add(new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("name"),
            });
        }
        return states;
    }


    public String[] findOne(int id) throws SQLException {
        String[] states = null;
        String query = "SELECT * FROM states WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
            if (set.next()) {
            states = new String[] {
                String.valueOf(set.getInt("id")),
                set.getString("name")
            };
        }
        return states;
    }


    public void cerrarConexion() {
        connection.closeConnection();
    }
}


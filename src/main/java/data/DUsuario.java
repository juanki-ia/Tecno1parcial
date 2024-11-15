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

    // id, name, email, password, phone, address
    public String save(
            String name,
            String email,
            String password,
            int phone,
            String address,
            String role) throws SQLException {

        String query = "INSERT INTO users(name,email,password,phone,address,role) "
                + "values(?,?,?,?,?,?)";

        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setInt(4, phone);
        ps.setString(5, address);
        ps.setString(6, role);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice:" + "El usuario no se pudo insertar");
            throw new SQLException();
        }

        return "El usuario se inserto con exito";
    }

    public String update(int id, String name, String email, String password,
            int phone, String address, String role) throws SQLException {

        String query = "UPDATE users SET name=?, email=?,password=?, phone=?, address=?, role=?, WHERE id=?";

        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setInt(4, phone);
        ps.setString(5, address);
        ps.setString(6, role);
        ps.setInt(7, id);
        

        if (ps.executeUpdate() == 0) {
            System.err.println("class DUsuario.java dice:" + "El usuario no se pudo actualizar");
            return "El usuario no se pudo actualizar";
            // throw new SQLException();
        }
        return "El usuario se actualizo con exito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DUsuario.java dice: "
                    + "El usuario no se pudo eliminar, delete()");
            return "El usuario no se pudo eliminar";
            // throw new SQLException();
        }
        return "El usuario se elimino con exito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> usuarios = new ArrayList<>();
        String query = "SELECT * FROM users";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            usuarios.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    set.getString("email"),
                    set.getString("password"),
                    String.valueOf(set.getInt("phone")),
                    set.getString("address"),
                    set.getString("role")

            });
        }
        return usuarios;
    }

    public String[] findOne(int id) throws SQLException {
        String[] usuario = null;
        String query = "SELECT  * FROM users WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            usuario = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    set.getString("email"),
                    set.getString("password"),
                    String.valueOf(set.getInt("phone")),
                    set.getString("address"),
                    set.getString("role")

            };
        }
        return usuario;
    }

}
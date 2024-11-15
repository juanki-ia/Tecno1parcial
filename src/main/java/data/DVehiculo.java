package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import PostgreSQL.databaseConnection;

public class DVehiculo {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DVehiculo() {
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

    public String save(String modelo, String marca, String color, String matricula, int usuarioId) throws SQLException {
        String query = "INSERT INTO Vehiculo(Modelo, Marca, Color, Matricula, usuario_id) values(?,?,?,?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, modelo);
        ps.setString(2, marca);
        ps.setString(3, color);
        ps.setString(4, matricula);
        ps.setInt(5, usuarioId);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DVehiculo.java dice:" + "El vehículo no se pudo insertar");
            throw new SQLException();
        }
        return "El vehículo se insertó con éxito";
    }

    public String update(int id, String modelo, String marca, String color, String matricula, int usuarioId) throws SQLException {
        String query = "UPDATE Vehiculo SET Modelo=?, Marca=?, Color=?, Matricula=?, usuario_id=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, modelo);
        ps.setString(2, marca);
        ps.setString(3, color);
        ps.setString(4, matricula);
        ps.setInt(5, usuarioId);
        ps.setInt(6, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DVehiculo.java dice:" + "El vehículo no se pudo actualizar");
            return "El vehículo no se pudo actualizar";
        }
        return "El vehículo se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM Vehiculo WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DVehiculo.java dice:" + "El vehículo no se pudo eliminar");
            return "El vehículo no se pudo eliminar";
        }
        return "El vehículo se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> vehiculos = new ArrayList<>();
        String query = "SELECT * FROM Vehiculo";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            vehiculos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Modelo"),
                    set.getString("Marca"),
                    set.getString("Color"),
                    set.getString("Matricula"),
                    String.valueOf(set.getInt("usuario_id"))
            });
        }
        return vehiculos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] vehiculo = null;
        String query = "SELECT * FROM Vehiculo WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            vehiculo = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Modelo"),
                    set.getString("Marca"),
                    set.getString("Color"),
                    set.getString("Matricula"),
                    String.valueOf(set.getInt("usuario_id"))
            };
        }
        return vehiculo;
    }
}

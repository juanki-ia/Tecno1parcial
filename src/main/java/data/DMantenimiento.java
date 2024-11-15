package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DMantenimiento {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DMantenimiento() {
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

    public String save(String descripcion, int vehiculoId, String fecha) throws SQLException {
        String query = "INSERT INTO Mantenimiento(Descripcion, vehiculo_id, Fecha) values(?,?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, descripcion);
        ps.setInt(2, vehiculoId);
        ps.setString(3, fecha);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DMantenimiento.java dice:" + "El mantenimiento no se pudo insertar");
            throw new SQLException();
        }
        return "El mantenimiento se insertó con éxito";
    }

    public String update(int id, String descripcion, int vehiculoId, String fecha) throws SQLException {
        String query = "UPDATE Mantenimiento SET Descripcion=?, vehiculo_id=?, Fecha=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setString(1, descripcion);
        ps.setInt(2, vehiculoId);
        ps.setString(3, fecha);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DMantenimiento.java dice:" + "El mantenimiento no se pudo actualizar");
            return "El mantenimiento no se pudo actualizar";
        }
        return "El mantenimiento se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM Mantenimiento WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DMantenimiento.java dice:" + "El mantenimiento no se pudo eliminar");
            return "El mantenimiento no se pudo eliminar";
        }
        return "El mantenimiento se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> mantenimientos = new ArrayList<>();
        String query = "SELECT * FROM Mantenimiento";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            mantenimientos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Descripcion"),
                    String.valueOf(set.getInt("vehiculo_id")),
                    set.getString("Fecha")
            });
        }
        return mantenimientos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] mantenimiento = null;
        String query = "SELECT * FROM Mantenimiento WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            mantenimiento = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("Descripcion"),
                    String.valueOf(set.getInt("vehiculo_id")),
                    set.getString("Fecha")
            };
        }
        return mantenimiento;
    }
}

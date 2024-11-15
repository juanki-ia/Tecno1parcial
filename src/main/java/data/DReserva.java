package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DReserva {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DReserva() {
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

    public String save(int vehiculoId, int servicioId, String fecha, String hora) throws SQLException {
        String query = "INSERT INTO Reserva(vehiculo_id, servicio_id, Fecha, Hora) values(?,?,?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setInt(1, vehiculoId);
        ps.setInt(2, servicioId);
        ps.setString(3, fecha);
        ps.setString(4, hora);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DReserva.java dice:" + "La reserva no se pudo insertar");
            throw new SQLException();
        }
        return "La reserva se insertó con éxito";
    }

    public String update(int id, int vehiculoId, int servicioId, String fecha, String hora) throws SQLException {
        String query = "UPDATE Reserva SET vehiculo_id=?, servicio_id=?, Fecha=?, Hora=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setInt(1, vehiculoId);
        ps.setInt(2, servicioId);
        ps.setString(3, fecha);
        ps.setString(4, hora);
        ps.setInt(5, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DReserva.java dice:" + "La reserva no se pudo actualizar");
            return "La reserva no se pudo actualizar";
        }
        return "La reserva se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM Reserva WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DReserva.java dice:" + "La reserva no se pudo eliminar");
            return "La reserva no se pudo eliminar";
        }
        return "La reserva se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> reservas = new ArrayList<>();
        String query = "SELECT * FROM Reserva";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            reservas.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("vehiculo_id")),
                    String.valueOf(set.getInt("servicio_id")),
                    set.getString("Fecha"),
                    set.getString("Hora")
            });
        }
        return reservas;
    }

    public String[] findOne(int id) throws SQLException {
        String[] reserva = null;
        String query = "SELECT * FROM Reserva WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            reserva = new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("vehiculo_id")),
                    String.valueOf(set.getInt("servicio_id")),
                    set.getString("Fecha"),
                    set.getString("Hora")
            };
        }
        return reserva;
    }
}


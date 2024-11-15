package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DPago {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DPago() {
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

    public String save(int reservaId, double cantidad, String fecha) throws SQLException {
        String query = "INSERT INTO Pago(reserva_id, Cantidad, Fecha) values(?,?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setInt(1, reservaId);
        ps.setDouble(2, cantidad);
        ps.setString(3, fecha);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DPago.java dice:" + "El pago no se pudo insertar");
            throw new SQLException();
        }
        return "El pago se insertó con éxito";
    }

    public String update(int id, int reservaId, double cantidad, String fecha) throws SQLException {
        String query = "UPDATE Pago SET reserva_id=?, Cantidad=?, Fecha=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);

        ps.setInt(1, reservaId);
        ps.setDouble(2, cantidad);
        ps.setString(3, fecha);
        ps.setInt(4, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DPago.java dice:" + "El pago no se pudo actualizar");
            return "El pago no se pudo actualizar";
        }
        return "El pago se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM Pago WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        if (ps.executeUpdate() == 0) {
            System.err.println("class DPago.java dice:" + "El pago no se pudo eliminar");
            return "El pago no se pudo eliminar";
        }
        return "El pago se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> pagos = new ArrayList<>();
        String query = "SELECT * FROM Pago";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();

        while (set.next()) {
            pagos.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("reserva_id")),
                    String.valueOf(set.getDouble("Cantidad")),
                    set.getString("Fecha")
            });
        }
        return pagos;
    }

    public String[] findOne(int id) throws SQLException {
        String[] pago = null;
        String query = "SELECT * FROM Pago WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            pago = new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("reserva_id")),
                    String.valueOf(set.getDouble("Cantidad")),
                    set.getString("Fecha")
            };
        }
        return pago;
    }
}

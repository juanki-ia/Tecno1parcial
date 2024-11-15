package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;
public class DReporte {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DReporte() {
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

    public List<String[]> generateReport(String fechaInicio, String fechaFin) throws SQLException {
        List<String[]> reportes = new ArrayList<>();
        String query = "SELECT * FROM Reserva WHERE Fecha BETWEEN ? AND ?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, fechaInicio);
        ps.setString(2, fechaFin);

        ResultSet set = ps.executeQuery();
        while (set.next()) {
            reportes.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    String.valueOf(set.getInt("vehiculo_id")),
                    String.valueOf(set.getInt("servicio_id")),
                    set.getString("Fecha"),
                    set.getString("Hora")
            });
        }
        return reportes;
    }
}

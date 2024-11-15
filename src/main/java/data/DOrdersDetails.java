package data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import PostgreSQL.databaseConnection;

public class DOrdersDetails {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DOrdersDetails(){
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

    public String save(int amount, Double subtotal, int id_order, int id_pizza) throws SQLException {
        String query = "INSERT INTO orders_details(amount,subtotal,id_order,id_pizza) VALUES(?,?,?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, amount);
        ps.setDouble(2, subtotal);
        ps.setInt(3, id_order);
        ps.setInt(4, id_pizza);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DOrdersDetails.java dice:" + "El Detalle no se pudo insertar");
            throw new SQLException();
        }
        return "El Detalle se inserto con exito";
    }
}

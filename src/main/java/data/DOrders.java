package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import PostgreSQL.databaseConnection;

public class DOrders {

    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DOrders() {

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

    public int save(Double total, int id_payment_method, int id_user, int id_state) throws SQLException {
        int orderId = -1;
        String query = "INSERT INTO orders(total, created_at, update_at, id_payment_method, id_user, id_state) VALUES(?, ?, ?, ?, ?, ?) RETURNING id";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        LocalDate now = LocalDate.now();
        ps.setDouble(1, total);
        ps.setDate(2, Date.valueOf(now)); // created_at
        ps.setDate(3, Date.valueOf(now)); // updated_at
        ps.setInt(4, id_payment_method);
        ps.setInt(5, id_user);
        ps.setInt(6, id_state);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                orderId = rs.getInt("id");
            }
        }
        if (orderId == -1) {
            System.err.println("class DOrders.java dice: El pedido no se pudo insertar");
            throw new SQLException("El pedido no se pudo insertar");
        }
    
        return orderId;
    }
    

    public List<String[]> findAll()throws SQLException{
        List<String[]> orders = new ArrayList<>();
        String query = "SELECT * FROM orders;";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            orders.add(new String[]{
                String.valueOf(set.getInt("id")),
                String.valueOf(set.getInt("total")),
                set.getString("created_at"),
                set.getString("update_at"),
                String.valueOf(set.getInt("id_payment_method")),
                String.valueOf(set.getInt("id_user")),
                String.valueOf(set.getInt("id_state")),
            });
        }
        return orders;
        
    }

    public String update(int orderId, int newStateId) throws SQLException {
        String query = "UPDATE orders SET id_state = ? WHERE id = ?";
        try (PreparedStatement ps = connection.connection().prepareStatement(query)) {
            ps.setInt(1, newStateId);
            ps.setInt(2, orderId);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("class DOrders.java dice: El estado de la orden no se pudo actualizar");
                return "El estado de la orden no se pudo actualizar";
            }
            return "El Estado de la orden actualizado con éxito";
        }
    }
    
}

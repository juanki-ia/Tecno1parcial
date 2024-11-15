package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DPaymentMethods {

    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DPaymentMethods() {

        this.connection = new databaseConnection(
                ConfigDb.getUser(),
                ConfigDb.getPassword(),
                ConfigDb.getHost(),
                ConfigDb.getPort(),
                ConfigDb.getDbName()
                );
    }

    public void disconnect() {
        if (connection != null)
            connection.closeConnection();
    }

    public String save(String name) throws SQLException {
        String query = "INSERT INTO payment_methods(name) "
                + "values(?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, name);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPayment.java dice:" + "El Metodo de Pago no se pudo insertar");
            return "El Metodo de Pago no se pudo insertar";
            // throw new SQLException();
        }

        return "El Metodo de Pago se inserto con exito";
    }

    public String[] findOne(int id) throws SQLException {

        String[] payment = null;
        String query = "SELECT  * FROM payment_methods WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);

        ResultSet set = ps.executeQuery();
        if (set.next()) {
            payment = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
            };
        }
        return payment;

    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> allPayments = new ArrayList<>();
        String query = "SELECT * FROM payment_methods";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            allPayments.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name")
            });
        }

        return allPayments;
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM payment_methods WHERE id=?";
        String deletePayment = "El Metodo de Pago se elimino con exito";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("Class DPayment.java dice: "
                    + "El Metodo de Pago no se pudo eliminar, delete()");
            return "El Metodo de Pago no se pudo eliminar ";
            // throw new SQLException();
        }
        return deletePayment;
    }

    public String update(int id, String name) throws SQLException {
        String query = "UPDATE payment_methods SET name=? WHERE id=?";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, id);

        if (ps.executeUpdate() == 0) {
            System.out.println("El Metodo de Pago no se pudo actualizar");
            return "El Metodo de Pago no se pudo actualizar";
        }

        return "El Metodo de Pago se actualizo con exito";
    }

}

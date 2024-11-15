package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PostgreSQL.databaseConnection;

public class DPizzas {
    public databaseConnection connection;
    ConfigDB ConfigDb = new ConfigDB();

    public DPizzas() {
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

    public String save(
        String name,
        Double price,
        String imagen_url,
        String description, 
        Boolean available,
        int id_size,int id_category) throws SQLException {
        String query = "INSERT INTO pizzas(name,price,imagen_url,description,available,id_size,id_category)" +
        "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.connection().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setString(3, imagen_url);
        ps.setString(4, description);
        ps.setBoolean(5, available);
        ps.setInt(6, id_size);
        ps.setInt(7, id_category);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DSizes.java dice:" + "La Pizza no se pudo insertar");
            throw new SQLException();
        }
        return "La Pizza Se inserto con exito";
    }

    public String update(int id, String name, Double price, String imagen_url, String description, Boolean available,
                    int id_size, int id_category) throws SQLException {
        String query = "UPDATE pizzas SET name=?, price=?, imagen_url=?, description=?, available=?, id_size=?, id_category=? WHERE id=?";
        try (Connection conn = connection.connection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setString(3, imagen_url);
            ps.setString(4, description);
            ps.setBoolean(5, available);
            ps.setInt(6, id_size);
            ps.setInt(7, id_category);
            ps.setInt(8, id); // El ID se establece al final para el WHERE
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("class DSizes.java dice: ocurrió un error al actualizar la pizza");
                return "La Pizza no se pudo actualizar";
            }
            return "La Pizza se actualizo con exito";
        }
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM pizzas WHERE id=?";
        try (Connection conn = connection.connection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, id);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("Class DSizes.java dice: ocurrió un error al eliminar una pizza, delete()");
                return "La Pizza no se pudo eliminar";
            }
            
            return "La Pizza se elimino con exito";
        }
    }


    public List<String[]> findAll() throws SQLException {
        List<String[]> pizzas = new ArrayList<>();
        String query = "SELECT * FROM pizzas";
        try (Connection conn = connection.connection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet set = ps.executeQuery()) {
            
            while (set.next()) {
                pizzas.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    set.getBigDecimal("price").toString(),
                    set.getString("imagen_url"),
                    set.getString("description"),
                    String.valueOf(set.getBoolean("available")),
                    String.valueOf(set.getInt("id_size")),
                    String.valueOf(set.getInt("id_category"))
                });
            }
        }
        return pizzas;
    }    

    public String[] findOne(int id) throws SQLException {
        String[] pizza = null;
        String query = "SELECT * FROM pizzas WHERE id=?";
        try (Connection conn = connection.connection();
             PreparedStatement ps = conn.prepareStatement(query)) {
             
            ps.setInt(1, id);
            try (ResultSet set = ps.executeQuery()) {
                if (set.next()) {
                    pizza = new String[] {
                        String.valueOf(set.getInt("id")),
                        set.getString("name"),
                        set.getBigDecimal("price").toString(),
                        set.getString("imagen_url"),
                        set.getString("description"),
                        String.valueOf(set.getBoolean("available")),
                        String.valueOf(set.getInt("id_size")),
                        String.valueOf(set.getInt("id_category"))
                    };
                }
            }
        }
        return pizza;
    }   

    public Double getPrice(int id) throws SQLException{
        String query = "SELECT price FROM pizzas WHERE id=?";
        try (Connection conn = connection.connection();
        PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            try (ResultSet set = ps.executeQuery()){
                if (set.next()) {
                    return set.getDouble("price");
                }
            }
        }
        return null;
    }
}
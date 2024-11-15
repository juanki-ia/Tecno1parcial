package business;

import java.sql.SQLException;
import java.util.List;

import data.DPaymentMethods;

public class BPaymentMethods {
    private DPaymentMethods dPaymentMethods;

    public BPaymentMethods() {
        this.dPaymentMethods = new DPaymentMethods();
    }

    
    public String save(String name) {
        try {
            return dPaymentMethods.save(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Método de pago no se pudo guardar:" + e.getMessage();
        }
    }

    
    public String update(int id, String name) {
        try {
            return dPaymentMethods.update(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Método de pago no se pudo actualizar:" + e.getMessage();
        }
    }

    
    public String delete(int id) {
        try {
            return dPaymentMethods.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Método de pago no se pudo eliminar:" + e.getMessage();
        }
    }

   
    public List<String[]> findAll() {
        try {
            return dPaymentMethods.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   
    public String[] findOnePay(int id) {
        try {
            return dPaymentMethods.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

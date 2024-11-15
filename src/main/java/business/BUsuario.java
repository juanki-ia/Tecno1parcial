package business;

import java.sql.SQLException;
import java.util.List;

import data.DUsuario;

public class BUsuario {
    private DUsuario dUsuario;

    public BUsuario(){
        this.dUsuario = new DUsuario();
    }

    public String save(String name, String email, String password, int phone, String address, String role) {
        try {
            return dUsuario.save(name, email, password, phone, address, role);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String name, String email, String password, int phone, String address, String role) {
        try {
            return dUsuario.update(id, name, email, password, phone, address, role);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dUsuario.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Usuario no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dUsuario.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dUsuario.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

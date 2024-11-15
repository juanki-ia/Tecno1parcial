package business;

import java.sql.SQLException;
import java.util.List;

import data.DSizes;

public class BSizes {
    private DSizes dSizes;

    public BSizes(){
        this.dSizes = new DSizes();
    }

    public String save(String name) {
        try {
            return dSizes.save(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Tamaño no se pudo guardar:" + e.getMessage();
        }
    }

    public String update(int id, String name) {
        try {
            return dSizes.update(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Tamaño no se pudo actualizar:" + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dSizes.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Tamaño no se pudo eliminar:" + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dSizes.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dSizes.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

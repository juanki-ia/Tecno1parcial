package business;

import java.sql.SQLException;
import java.util.List;

import data.DCategorie;

public class BCategorie {
    private DCategorie dCategorie;

    public BCategorie() {
        this.dCategorie = new DCategorie();
    }

    
    public String save(String name) {
        try {
            System.out.println("BCATEGORIES");
            System.out.println(name);
            return dCategorie.save(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La categoria no se pudo guardar:" + e.getMessage();
        }
    }

    
    public String update(int id, String name) {
        try {
            return dCategorie.update(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La categoria no se pudo actualizar:" + e.getMessage();
        }
    }

    
    public String delete(int id) {
        try {
            return dCategorie.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La categoria no se pudo eliminar:" + e.getMessage();
        }
    }

   

    public List<String[]> findAll() {
        try {
            return dCategorie.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   
    public String[] findOne(int id) {
        try {
            return dCategorie.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


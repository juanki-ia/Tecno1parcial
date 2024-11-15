package business;

import java.sql.SQLException;
import java.util.List;

import data.DState;

public class BState {
    private DState dState;

    public BState() {
        this.dState = new DState();
    }

    public String save(String name) {
        try {
            return dState.save(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Estado no se pudo guardar:" + e.getMessage();
        }
    }

  
    public String update(int id, String name) {
        try {
            return dState.update(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Estado no se pudo actualizar: " + e.getMessage();
        }
    }


    public String delete(int id) {
        try {
            return dState.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El Estado no se pudo eliminar: " + e.getMessage();
        }
    }

    
    public List<String[]> findAll() {
        try {
            return dState.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public String[] findOne(int id) {
        try {
            return dState.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

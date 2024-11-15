package command;

import java.util.List;

import business.BCategorie;
                                            
public class HandleCategories {
    public static String save(String params){

        String response = "";

        if(params instanceof String){
            BCategorie categories = new BCategorie();
            response = categories.save(params);
        }else{
            response = "HandleCategories.java dice: Ocurrió un error al ejecutar el método save "
            + "(el parámetro contiene mas argumentos de los que necesita el método save)";
        }
        return response;
    }

    public static String update(String params){
        
        String response = "";
        if(isValidFormat(params)){
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String categoryUpdate = parts[1];
            BCategorie categorie = new BCategorie();
            response = categorie.update(id, categoryUpdate);
            return response;
        }else{
            response = "HandleCategories.java dice: Ocurrió un error al ejecutar el método update "
            + "(los parámetros recibidos no cumplen con el formato que necesita el método save)";
            return response;
        }
    }

    public static String delete(String params){
        String response = "";
        if(isOnlyNumbers(params)){
            int idToDelete = Integer.parseInt(params);
            BCategorie category = new BCategorie();
            response = category.delete(idToDelete);
            return response;
        }else{
            response = "HandleCategories.java dice: Ocurrió un error al ejecutar el método delete "
            + "(El parámetro recibido no es un número, el método delete solamente recibe números)";
            return response;
        }
    }

    public static String findAll(){
        BCategorie category = new BCategorie();
        List<String[]> categories = category.findAll();
        if(categories != null){
            String concatenatedCategories = concatenateCategories(categories);
            return concatenatedCategories;
        }else{
            return "Categorias vacías";
        }
    }

    public static String findOne(String params){
        String response = "";
        if(isOnlyNumbers(params)){
            int idToFind = Integer.parseInt(params);
            BCategorie category = new BCategorie();
            String[] categoryFound = category.findOne(idToFind);
            response = java.util.Arrays.toString(categoryFound);
            return response;
        }else{
            response = "HandleCategories.java dice: Ocurrió un error al ejecutar el método findOne "
            + "(El parámetro recibido no es un número, el método findOne solamente recibe números)";
            return response;
        }
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z]+$";
        return input.matches(regex);
    }

    public static boolean isOnlyNumbers(String cadena) {
        return cadena.matches("^[0-9]+$");
    }

    public static String concatenateCategories(List<String[]> categories) {
        StringBuilder concatenated = new StringBuilder();
        String delimiter = ", "; // Delimitador entre categorías

        for (String[] categoryArray : categories) {
            for (String category : categoryArray) {
                if (concatenated.length() > 0) {
                    concatenated.append(delimiter);
                }
                concatenated.append(category);
            }
        }

        return concatenated.toString();
    }
}


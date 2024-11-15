package command;

import java.util.List;

import business.BPizzas;

public class HandlePizzas {

    public static String save(String params){

        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 6) {
            String name = partsOfParams[0];
            Double price = Double.parseDouble(partsOfParams[1]);
            String imageUrl = partsOfParams[2];
            String description = partsOfParams[3];
            int idSize = Integer.parseInt(partsOfParams[4]);
            int idCategory = Integer.parseInt(partsOfParams[5]);
            BPizzas pizzas = new BPizzas();
            String response = pizzas.save(name, price, imageUrl, description, idSize, idCategory);
            return response;
        }
        return "HandlePizzas.java dice: Ocurrió un error al ejecutar el método save" + 
        "(Número de parámetros incorrecto.)";
    }

    public static String update(String params){

        String[] partsOfParams = params.split(",\\s*");
        if (partsOfParams.length == 8){
            int id = Integer.parseInt(partsOfParams[0]);
            String name = partsOfParams[1];
            Double price = Double.parseDouble(partsOfParams[2]);
            String imageUrl = partsOfParams[3];
            String description = partsOfParams[4];
            boolean available = Boolean.parseBoolean(partsOfParams[5]);
            int idSize = Integer.parseInt(partsOfParams[6]);
            int idCategory = Integer.parseInt(partsOfParams[7]);
            BPizzas pizzas = new BPizzas();
            String response = pizzas.update(id, name, price, imageUrl, description, available, idSize, idCategory);
            return response;
        }
        return "HandlePizzas.java dice: Ocurrió un error al ejecutar el método update" + 
        "(Número de parámetros incorrecto.)";
    }

    public static String delete(String params){
        String response = "";
        if(isOnlyNumbers(params)){
            int idToDelete = Integer.parseInt(params);
            BPizzas pizzas = new BPizzas();
            response = pizzas.delete(idToDelete);
            return response;
        }else{
            response = "HandlePizzas.java dice: Ocurrió un error al ejecutar el método delete "
            + "(El parámetro recibido no es un número, el método delete solamente recibe números)";
            return response;
        }
    }

    public static String findAll(){
        BPizzas pizzas = new BPizzas();
        List<String[]> pizzasResults = pizzas.findAll();
        String result = convertListToString(pizzasResults);
        return result;
    }

    public static String findOne(String params){
        String response = "";
        if(isOnlyNumbers(params)){
            int idToFind = Integer.parseInt(params);
            BPizzas pizzas = new BPizzas();
            String[] pizzaFound = pizzas.findOne(idToFind);
            response = java.util.Arrays.toString(pizzaFound);
            return response;
        }else{
            response = "HandleCategories.java dice: Ocurrió un error al ejecutar el método findOne "
            + "(El parámetro recibido no es un número, el método findOne solamente recibe números)";
            return response;
        }
    }

    public static boolean isOnlyNumbers(String cadena) {
        return cadena.matches("^[0-9]+$");
    }

    public static String convertListToString(List<String[]> list) {
        if (list == null) {
            return "Error: No se pudieron obtener los datos.";
        }

        StringBuilder result = new StringBuilder();
        String delimiter = ", "; // Delimitador entre elementos

        for (String[] array : list) {
            if (array != null) {
                result.append(java.util.Arrays.toString(array)).append("\n");
            }
        }

        return result.toString();
    }
}

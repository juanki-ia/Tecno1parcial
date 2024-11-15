package command;

import business.BSizes;

public class HandleSizes {
    
    public static String save(String params){
        String response = "";
        if(params instanceof String){
            BSizes sizes = new BSizes();
            response = sizes.save(params);
        }else{
            response = "HandleSizes.java dice: Ocurrió un error al ejecutar el método save "
            + "(el parámetro contiene mas argumentos de los que necesita el método save)";
        }
        return response;
    }


    public static String update(String params){
        
        String response = "";
        if(isValidFormat(params)){
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String sizeUpdate = parts[1];
            BSizes size = new BSizes();
            response = size.update(id, sizeUpdate);
            return response;
        }else{
            response = "HandleSizes.java dice: Ocurrió un error al ejecutar el método update "
            + "(los parámetros recibidos no cumplen con el formato que necesita el método save)";
            return response;
        }
    }

    public static String delete(String idStr) {
        String response = "";
        try {
            int id = Integer.parseInt(idStr.trim()); // Convertir el ID de String a int
            BSizes size = new BSizes();
            response = size.delete(id); // Llamar al método delete de BState
        } catch (NumberFormatException e) {
            response = "HandleSizes.java dice: El ID debe ser numérico y válido.";
        }
        return response;
    }
    

    public static String findOne(String idStr) {
        String response = "";
        try {
            int id = Integer.parseInt(idStr.trim());
            BSizes size = new BSizes();
            String[] sizeDetails = size.findOne(id);
            if (sizeDetails == null || sizeDetails.length == 0) {
                response = "No se encontró el estado con ID: " + id;
            } else {
                response = "ID: " + sizeDetails[0] + ", Nombre: " + sizeDetails[1];
            }
        } catch (NumberFormatException e) {
            response = "HandleSizes.java dice: El ID debe ser numérico.";
        }
        return response;
    }
    

    public static String findAll() {
        String response = "";
        BSizes size = new BSizes();
        java.util.List<String[]> sizes = size.findAll();
        if (sizes == null || sizes.isEmpty()) {
            response = "No hay estados registrados.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String[] sizeDetails : sizes) {
                sb.append("ID: ").append(sizeDetails[0]).append(", Nombre: ").append(sizeDetails[1]).append("\n");
            }
            response = sb.toString();
        }
        return response;
    }
    

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z\\s]+$"; // Permite nombres con espacios
        return input.matches(regex);
    }
}


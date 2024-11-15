package command;

import business.BState;

public class HandleStates {

    public static String save(String params){

        String response = "";

        if(params instanceof String){
            BState states = new BState();
            response = states.save(params);
        }else{
            response = "HandleStates.java dice: Ocurrió un error al ejecutar el método save "
            + "(el parámetro contiene mas argumentos de los que necesita el método save)";
        }
        return response;
    }


    public static String update(String params){
        
        String response = "";
        if(isValidFormat(params)){
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String stateUpdate = parts[1];
            BState state = new BState();
            response = state.update(id, stateUpdate);
            return response;
        }else{
            response = "HandleStates.java dice: Ocurrió un error al ejecutar el método update "
            + "(los parámetros recibidos no cumplen con el formato que necesita el método save)";
            return response;
        }
    }

    public static String delete(String idStr) {
        String response = "";
        try {
            int id = Integer.parseInt(idStr.trim()); // Convertir el ID de String a int
            BState state = new BState();
            response = state.delete(id); // Llamar al método delete de BState
        } catch (NumberFormatException e) {
            response = "HandleStates.java dice: El ID debe ser numérico y válido.";
        }
        return response;
    }
    

    public static String findOne(String idStr) {
        String response = "";
        try {
            int id = Integer.parseInt(idStr.trim());
            BState state = new BState();
            String[] stateDetails = state.findOne(id);
            if (stateDetails == null || stateDetails.length == 0) {
                response = "No se encontró el estado con ID: " + id;
            } else {
                response = "ID: " + stateDetails[0] + ", Nombre: " + stateDetails[1];
            }
        } catch (NumberFormatException e) {
            response = "HandleStates.java dice: El ID debe ser numérico.";
        }
        return response;
    }
    

    public static String findAll() {
        String response = "";
        BState state = new BState();
        java.util.List<String[]> states = state.findAll();
        if (states == null || states.isEmpty()) {
            response = "No hay estados registrados.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String[] stateDetails : states) {
                sb.append("ID: ").append(stateDetails[0]).append(", Nombre: ").append(stateDetails[1]).append("\n");
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

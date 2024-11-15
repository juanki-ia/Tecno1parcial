package command;

import java.util.List;
import java.util.StringJoiner;

import business.BCategorie;
import business.BOrders;

public class HandleOrders {

    public static String save(String params) {
        String response = "";
        BOrders order = new BOrders();
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int id_user = Integer.parseInt(parts[0]);
            int id_pizza = Integer.parseInt(parts[1]);
            int pizzasQuantity = Integer.parseInt(parts[2]);
            int id_payment_method = Integer.parseInt(parts[3]);

            response = order.save(id_user, id_pizza, pizzasQuantity, id_payment_method);
            return response;
        } else {
            response = "HandleOrder.java dice: Ocurrió un error al ejecutar el método save "
                    + "(los parámetros recibidos no cumplen con el formato que necesita el método save): " + params;
            return response;
        }
    }

    public static String update(String params) {
        String response = "";
        BOrders order = new BOrders();
        if (isValidFormat(params)) {
            String[] parts = params.split(", ");
            int idOrder = Integer.parseInt(parts[0]);
            int newStateId = Integer.parseInt(parts[1]);

            response = order.update(idOrder,newStateId);
            return response;
        } else {
            response = "HandleOrder.java dice: Ocurrió un error al ejecutar el método update "
                    + "(los parámetros recibidos no cumplen con el formato que necesita el método update): " + params;
            return response;
        }
    }

    public static String findAll() {
        BOrders orderB = new BOrders();
        List<String[]> res = orderB.findAll();
        if(res !=null){
            String concatenateOrders = concatenateOrders(res);
            System.out.println(concatenateOrders);
            return concatenateOrders;
        }else{
            
            return "categorias vacias";
        }
    }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+(, \\d+)*$";
        return input.matches(regex);
    }

    public static String concatenateOrders(List<String[]> orders) {
        StringBuilder concatenated = new StringBuilder();
        String delimiter = ", ";
    
        for (String[] order : orders) {
            for (String orderData : order) {
                if (concatenated.length() > 0) {
                    concatenated.append(delimiter);
                }
                concatenated.append(orderData);
            }
        }
        return concatenated.toString();
    }
    
}

package command;

import java.util.List;

import business.BCategorie;
import business.BPaymentMethods;

public class HandlePaymentMethods {

    public static String save(String params) {
        BPaymentMethods payment = new BPaymentMethods();
        String res = " ";
        if (params instanceof String) {
            String name = params;
            res = payment.save(name);
        } else {
            res = "HandlePaymentMethods : error en el metodo save, params no admitido: " + params;
        }
        return res;

    }

    public static String update(String params) {
        BPaymentMethods payment = new BPaymentMethods();
        String res = "";
        // if (isValidFormat(params)) {
        // String[] parts = params.split(", ");
        // int id = Integer.parseInt(parts[0]);
        // String newName = parts[1];
        // res = payment.update(id, newName);
        // } else {
        // res = "Handle Payment method: error en el metodo update, params: " + params;
        // }
        try {
            String[] parts = params.split(", ");
            int id = Integer.parseInt(parts[0]);
            String newName = parts[1];
            res = payment.update(id, newName);
            return res;
        } catch (Exception e) {
            // TODO: handle exception
            res = "Handle Payment method: error en el metodo update, params: " + params;
            return res;
        }

    }

    public static String delete(String params) {
        String res = "";
        BPaymentMethods payment = new BPaymentMethods();
        try {
            int id = Integer.parseInt(params);
            res = payment.delete(id);
        } catch (NumberFormatException e) {
            res = "Handler Payment Methods Error: El parámetro proporcionado no es un número entero válido.";
        }
        return res;
    }

    public static String findOne(String params) {
        String res = "";
        BPaymentMethods payment = new BPaymentMethods();
        try {
            int id = Integer.parseInt(params);
            String pay[] = payment.findOnePay(id);
            res = java.util.Arrays.toString(pay);
            return res;
        } catch (NumberFormatException e) {
            res = "Handler Payment Methods Error: El parámetro proporcionado no es un número entero válido.";
        }
        return res;
    }

    public static String findAll() {
        BPaymentMethods payment = new BPaymentMethods();
        List<String[]> payments = payment.findAll();
        if (payments != null) {
            String concatenatedPayments = concatenatePayment(payments);
            return concatenatedPayments;
        } else {
            return "Categorias vacías";
        }
    }

    public static String concatenatePayment(List<String[]> payments) {
        StringBuilder concatenated = new StringBuilder();
        String delimiter = ", ";

        for (String[] payment : payments) {
            for (String paymentData : payment) {
                if (concatenated.length() > 0) {
                    concatenated.append(delimiter);
                }
                concatenated.append(paymentData);
            }
        }
        return concatenated.toString();
    }

    // public static boolean isValidFormat(String input) {
    // String regex = "^\\d+(, \\d+)*$";
    // return input.matches(regex);
    // }

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z]+$";
        return input.matches(regex);
    }
}

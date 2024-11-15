package command;

import java.util.List;

import business.BUsuario;

public class HandleUsuarios {

   
    public static String save(String params) {
        // Dividir la cadena de entrada en parámetros basándose en comas seguidas de cero o más espacios
        String[] partsOfParams = params.split(",\\s*");
    
        // Asegurar que todos los parámetros necesarios están presentes
        if (partsOfParams.length == 6) {
            try {
                String name = partsOfParams[0];
                String email = partsOfParams[1];
                String password = partsOfParams[2];
                int phone = Integer.parseInt(partsOfParams[3]); // Esto puede lanzar NumberFormatException si no es un número
                String address = partsOfParams[4];
                String role = partsOfParams[5];
    
                BUsuario usuarios = new BUsuario();
                return usuarios.save(name, email, password, phone, address, role);
            } catch (NumberFormatException e) {
                return "Error: El teléfono debe ser un número válido. " + e.getMessage();
            } catch (Exception e) {
                return "Error al procesar los parámetros: " + e.getMessage();
            }
        } else {
            return "HandleUsuarios.java dice: Ocurrió un error al ejecutar el método save " +
                   "(Número de parámetros incorrecto. Esperados: 6, Recibidos: " + partsOfParams.length + ")";
        }
    }
    
    
    

   
    public static String update(String params) {
        // Primero, validar los parámetros
        if (!isValidFormat(params)) {
            return "Error: Formato incorrecto o datos incompletos para actualizar.";
        }
    
        // Dividir los parámetros asegurando que sean exactamente 7
        String[] parts = params.split(",\\s*");
        if (parts.length != 7) {
            return "Error: Número incorrecto de argumentos proporcionados. Esperados: 7, recibidos: " + parts.length;
        }
    
        try {
            int id = Integer.parseInt(parts[0]);  // ID del usuario
            String name = parts[1];  // Nombre del usuario
            String email = parts[2];  // Email del usuario
            String password = parts[3];  // Contraseña del usuario
            int phone = Integer.parseInt(parts[4]);  // Teléfono del usuario
            String address = parts[5];  // Dirección del usuario
            String role = parts[6];  // Rol del usuario
    
            BUsuario usuario = new BUsuario();
            return usuario.update(id, name, email, password, phone, address, role);
        } catch (NumberFormatException e) {
            return "Error: Fallo al convertir el ID o teléfono a número. " + e.getMessage();
        } catch (Exception e) {
            return "Error general al actualizar el usuario: " + e.getMessage();
        }
    }
    

    
    public static String delete(String idStr) {
        try {
            int userId = Integer.parseInt(idStr.trim());  // Convertir el ID de String a int
            BUsuario usuario = new BUsuario();
            return usuario.delete(userId);  // Llamar al método delete de BUsuario
        } catch (NumberFormatException e) {
            return "HandleUsuarios.java dice: El ID debe ser numérico y válido. " + e.getMessage();
        }
    }
    

    
    public static String findOne(String idStr) {
        try {
            int userId = Integer.parseInt(idStr.trim());  // Convertir el ID de String a int
            BUsuario usuario = new BUsuario();
            String[] userDetails = usuario.findOne(userId);
            if (userDetails == null || userDetails.length == 0) {
                return "No se encontró el usuario con ID: " + idStr;
            }
            return String.join(", ", userDetails);  // Concatenar la información del usuario
        } catch (NumberFormatException e) {
            return "Error: el ID debe ser numérico. " + e.getMessage();
        }
    }
    
    

 
    public static String findAll() {
        BUsuario usuario = new BUsuario();
        try {
            List<String[]> users = usuario.findAll();
            if (users.isEmpty()) {
                return "No hay usuarios registrados.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] user : users) {
                sb.append("Usuario: ").append(String.join(", ", user)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar usuarios: " + e.getMessage();
        }
    }
    

    

    public static boolean isValidFormat(String input) {
        String regex = "^\\d+, [a-zA-Z \\-']+?, [a-zA-Z0-9@.]+, \\w+, \\d+, [a-zA-Z0-9, \\-']+?, \\w+$";
        return input.matches(regex);
    }
    
    
}

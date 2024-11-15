package command;

import java.util.HashMap;
import java.util.Map;

public class CommandInterpreter {

    private static final Map<String, String[]> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("categories", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("orders", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("paymentmethods", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("pizzas", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("sizes", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("usuarios", new String[] { "save", "update", "findOne", "findAll", "delete" });
        COMMANDS.put("states", new String[] { "save", "update", "findOne", "findAll", "delete" });
    }

    public static String interpret(String subject) {
        subject = subject.replaceAll("[^a-zA-Z0-9\\s\\(\\),./@]", "");
        // Recortar espacios en blanco innecesarios fuera de los paréntesis
        subject = subject.replaceAll("\\s+", " ").trim();

        System.out.println("Subject luego de formatear: " + subject);
        if (subject.equals("help")) {
            return getHelpMessage();
        }

        String pattern = "(\\w+)\\s+(\\w+)\\s*\\((.*)\\)";
        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = regex.matcher(subject);

        if (!matcher.matches()) {
            return "Comando no reconocido. Por favor, asegúrate de seguir la estructura: {caso_de_uso} comando (parametros)";
        }

        String useCase = matcher.group(1).trim();
        String command = matcher.group(2).trim();
        String params = matcher.group(3).trim();

        if (!COMMANDS.containsKey(useCase)) {
            return "Caso de uso '" + useCase
                    + "' no reconocido. Usa 'HELP' para ver la lista de casos de uso y comandos.";
        }

        boolean commandExists = false;
        for (String validCommand : COMMANDS.get(useCase)) {
            if (validCommand.equals(command)) {
                commandExists = true;
                break;
            }
        }

        if (!commandExists) {
            return "Comando '" + command + "' no reconocido para el caso de uso '" + useCase
                    + "'. Usa 'HELP' para ver la lista de comandos.";
        }

        // Empezar el switch
        System.out.println(useCase);
        System.out.println(useCase.toLowerCase());
        switch (useCase.toLowerCase()) {
            case "categories":
                if (command.equals("save")) {
                    return HandleCategories.save(params);
                } else if (command.equals("update")) {
                    return HandleCategories.update(params);
                } else if (command.equals("delete")) {
                    return HandleCategories.delete(params);
                } else if (command.equals("findAll")) {
                    return HandleCategories.findAll();
                } else if (command.equals("findOne")) {
                    return HandleCategories.findOne(params);
                }
            case "orders":
                if (command.equals("save")) {
                    System.out.println("PARAMS SAVE ORDERS");
                    System.out.println(params);
                    return HandleOrders.save(params);
                } else if (command.equals("update")) {
                    return HandleOrders.update(params);
                } else if (command.equals("findAll")) {
                    System.out.println("find all orders execute");
                    return HandleOrders.findAll();
                }

            case "sizes":
                if (command.equals("save")) {
                    return HandleSizes.save(params);
                } else if (command.equals("update")) {
                    return HandleSizes.update(params);
                } else if (command.equals("delete")) {
                    return HandleSizes.delete(params);
                } else if (command.equals("findOne")) {
                    return HandleSizes.findOne(params);
                } else if (command.equals("findAll")) {
                    return HandleSizes.findAll();
                }

            case "states":
                if (command.equals("save")) {
                    return HandleStates.save(params);
                } else if (command.equals("update")) {
                    return HandleStates.update(params);
                } else if (command.equals("delete")) {
                    return HandleStates.delete(params);
                } else if (command.equals("findOne")) {
                    return HandleStates.findOne(params);
                } else if (command.equals("findAll")) {
                    return HandleStates.findAll();
                }

            case "usuarios":
                if (command.equals("save")) {
                    return HandleUsuarios.save(params);
                } else if (command.equals("update")) {
                    return HandleUsuarios.update(params);
                } else if (command.equals("delete")) {
                    return HandleUsuarios.delete(params);
                } else if (command.equals("findOne")) {
                    return HandleUsuarios.findOne(params);
                } else if (command.equals("findAll")) {
                    return HandleUsuarios.findAll();
                }

            case "paymentmethods":
                if (command.equals("save")) {
                    return HandlePaymentMethods.save(params);
                } else if (command.equals("update")) {
                    return HandlePaymentMethods.update(params);
                } else if (command.equals("delete")) {
                    return HandlePaymentMethods.delete(params);
                } else if (command.equals("findOne")) {
                    return HandlePaymentMethods.findOne(params);
                } else if (command.equals("findAll")) {
                    return HandlePaymentMethods.findAll();
                }

            case "pizzas":
                if (command.equals("save")) {
                    return HandlePizzas.save(params);
                } else if (command.equals("update")) {
                    return HandlePizzas.update(params);
                } else if (command.equals("delete")) {
                    return HandlePizzas.delete(params);
                } else if (command.equals("findAll")) {
                    return HandlePizzas.findAll();
                } else if (command.equals("findOne")) {
                    return HandlePizzas.findOne(params);
                }
            default:
                return "No se reconoce " + useCase + " como un caso de uso";
        }
    }

    private static String getHelpMessage() {
        String helpMessage = "**************** SISTEMA VIA MAIL PIZZERÍA ALEJANDRINI **************** \r\n" + //
                        "\r\n" + //
                        "        Utiliza 'help' para visualizar la lista de comandos \r\n" + //
                        "\r\n" + //
                        "        La estructura para enviar comandos es la siguiente: casoDeUso metodo (parametros, del , metodo) \r\n" + //
                        "        A continuación la lista de los casos de uso y los comandos disponibles:\r\n" + //
                        "\r\n" + //
                        "        CU: pizzas\r\n" + //
                        "        - save (name, price, image/url.jpg, descripcion sin comas ni acentos, idSize, idCategory)\r\n" + //
                        "        - update (id, name, price, image/url.jpg, descripcion sin comas ni acentos, boolean true o false, idSize, idCategory)\r\n" + //
                        "        - findOne (idToFind)\r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "        - delete (idToDelete)\r\n" + //
                        "\r\n" + //
                        "        CU: sizes\r\n" + //
                        "        - save (size)\r\n" + //
                        "        - update (id, sizeUpdate)\r\n" + //
                        "        - findOne (idToFind)\r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "        - delete (idToDelete)\r\n" + //
                        "\r\n" + //
                        "        CU: paymentmethods\r\n" + //
                        "        - save (name)\r\n" + //
                        "        - update (id, newName)\r\n" + //
                        "        - findOne (id)\r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "        - delete (id)\r\n" + //
                        "\r\n" + //
                        "        CU: orders\r\n" + //
                        "        - save (id_user, id_pizza, pizzasQuantity, id_payment_method)\r\n" + //
                        "        - update (idOrder,newStateId) \r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "\r\n" + //
                        "        CU: categories\r\n" + //
                        "        - save (categorie)\r\n" + //
                        "        - update (id, categoryUpdate)\r\n" + //
                        "        - findOne (idToFind)\r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "        - delete (idToDelete)\r\n" + //
                        "\r\n" + //
                        "        CU: usuarios\r\n" + //
                        "        - save (name, email, password, phone, direccion sin comas ni acentos, role)\r\n" + //
                        "        - update (id, name, email, password, phone, direccion sin comas ni acentos, role)\r\n" + //
                        "        - findOne (userId)\r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "        - delete (userId)\r\n" + //
                        "\r\n" + //
                        "        CU: states\r\n" + //
                        "        - save (estado)\r\n" + //
                        "        - update (id, stateUpdate)\r\n" + //
                        "        - findOne (id)\r\n" + //
                        "        - findAll (all) -- escribir tal cual\r\n" + //
                        "        - delete (id)\r\n" + //
                        "        ";
        return helpMessage.toString();
    }
}

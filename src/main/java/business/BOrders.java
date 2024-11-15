package business;

import java.sql.SQLException;
import java.util.List;

import data.DOrders;
import data.DOrdersDetails;
import data.DPizzas;

public class BOrders {
    private DOrders dOrders;
    private DOrdersDetails dOrdersDetails;
    private DPizzas dPizzas;
    private int orderState;

    public BOrders(){
        this.dOrders = new DOrders();
        this.dPizzas = new DPizzas();
        this.dOrdersDetails = new DOrdersDetails();
        this.orderState = 1;
    }

    public String save(int id_user, int id_pizza, int pizzasQuantity, int id_payment_method) {
        try {
            Double pizzaPrice = this.dPizzas.getPrice(id_pizza);
            Double total = pizzaPrice * pizzasQuantity;
            int orderId = dOrders.save(total, id_payment_method, id_user, orderState);
            dOrdersDetails.save(pizzasQuantity, pizzaPrice, orderId, id_pizza);
            return "El pedido se guardó correctamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pedido no se pudo guardar:" + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dOrders.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String update(int orderId, int newStateId) {
        try {
            return dOrders.update(orderId, newStateId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pedido no se pudo actualizar:" + e.getMessage();
        }
    }
}

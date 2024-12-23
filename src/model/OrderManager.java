package model;


import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order>allOrder;
    
    public OrderManager() {
        this.allOrder = new ArrayList<>();
    }
    
    public boolean addOrder(Order order){
        if (!allOrder.contains(order)){
            allOrder.add(order);
            return true;
        }
        return false;
    }
    public void addAllOrder(List<Order>list){
        for (Order order : list){
            if (addOrder(order)){
                order.getCustomer().totalBill(order.totalPrice());
            }
        }
    }
}

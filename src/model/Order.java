package model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int idOrder;
    private LocalDate orderDate;
    private Customer customer;
    private List<OrderBook> listOrder;
    
    public Order(int idOrder, List<OrderBook> listOrder) {
        this.idOrder = idOrder;
        this.listOrder = listOrder;
    }
}

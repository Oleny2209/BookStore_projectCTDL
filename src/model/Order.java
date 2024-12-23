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
    
    public int getIdOrder() {
        return idOrder;
    }
    
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
    
    public LocalDate getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<OrderBook> getListOrder() {
        return listOrder;
    }
    
    public void setListOrder(List<OrderBook> listOrder) {
        this.listOrder = listOrder;
    }
    
    public double totalPrice(){
        return listOrder.stream().mapToDouble(OrderBook::getPriceBook).sum();
    }
}

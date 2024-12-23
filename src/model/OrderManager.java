package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> allOrders;
    
    public OrderManager() {
        this.allOrders = new ArrayList<>();
    }
    
    public List<Order> getAllOrders() {
        return allOrders;
    }
    
    //Tao addBookToOrder de them sach vao hoa don
    public void addBookToOrder(int orderId, Book book, int quantity) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.addBook(book, quantity);
        } else {
            throw new IllegalArgumentException("Book's ID " + orderId + " not found.");
        }
    }
    
    //Tao removeBookFromOrder de xoa sach khoi hoa don
    public void removeBookFromOrder(int orderId, Book book) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.removeBook(book);
        } else {
            throw new IllegalArgumentException("Order's ID " + orderId + " not found.");
        }
    }
    
    //Tao removeOrder dua vao ID
    public void removeOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            this.allOrders.remove(order);
        } else {
            throw new IllegalArgumentException("Order's ID " + orderId + " not found.");
        }
    }
    
    //Tao createOrder de tao hoa don
    public Order createOrder(int idOrder, LocalDate orderDate, LocalDate deliveryDate, Customer customer, List<OrderBook> orderBooks) {
        Order newOrder = new Order(idOrder, orderDate, deliveryDate, customer, orderBooks);
        allOrders.add(newOrder);
        return newOrder;
    }
    
    //Tao calculateTotalPrice tinh tong tien
    public double calculateTotalPrice(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            return order.calculateTotalPrice();
        } else {
            throw new IllegalArgumentException("Order's ID " + orderId + " not found.");
        }
    }
    
    //Tao calculatePriceAfterDiscount de tinh tong tien sau khi giam gia
    public double calculatePriceAfterDiscount(int orderId, double discount) {
        Order order = findOrderById(orderId);
        if (order != null) {
            return order.calculatePriceAfterDiscount(discount);
        } else {
            throw new IllegalArgumentException("Order's ID " + orderId + " not found.");
        }
    }
    
    public Order findOrderById(int orderId) {
        for (Order order : allOrders) {
            if (order.getIdOrder() == orderId) {
                return order;
            }
        }
        return null;
    }
    
    public void addOrder(Order order) {
        if (!allOrders.contains(order)) {
            allOrders.add(order);
        }
    }
    
    public void removeOrderBook(String idBook, int idOrder) {
        findOrderById(idOrder).removeBookByID(idBook);
    }
}

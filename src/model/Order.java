package model;


import java.time.LocalDate;
import java.util.List;

public class Order {
    private int idOrder;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Customer customer;
    private List<OrderBook> listOrder;
    
    public Order(int idOrder, LocalDate orderDate, LocalDate deliveryDate, Customer customer, List<OrderBook> listOrder) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.customer = customer;
        this.listOrder = listOrder;
    }
    
    //Tao addBook de them sach vao list
    public void addBook(Book book, int quantity) {
        if (book == null || quantity <= 0) {
            throw new IllegalArgumentException("Nhap sai sach hoac so luong sai");
        }
        for (OrderBook orderBook : listOrder) {
            if (orderBook.getBook().equals(book)) {
                orderBook.setQuantity(orderBook.getQuantity() + quantity);
                return;
            }
        }
        listOrder.add(new OrderBook(book, quantity));
    }
    
    //Tao removeBook de xoa sach khoi list
    public void removeBook(Book book) {
        for (int i = 0; i < listOrder.size(); i++) {
            if (listOrder.get(i).getBook().equals(book)) {
                listOrder.remove(i);
                return;
            }
        }
    }
    
    //Tao calculateTotalPrice de tinh tong tien hoa don
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (OrderBook orderBook : listOrder) {
            totalPrice += orderBook.getBook().getPrice() * orderBook.getQuantity();
        }
        return totalPrice;
    }
    
    //Tao calculatePriceAfterDiscount
    public double calculatePriceAfterDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Phan tram giam gia phai tu 0 den 100");
        }
        double totalPrice = calculateTotalPrice();
        return totalPrice - (totalPrice * discount / 100);
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

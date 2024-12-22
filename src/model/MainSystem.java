package model;


import util.FileLoader;

public class MainSystem {
    private static MainSystem instance;
    private BookManager bookManager;
    private OrderManager orderManager;
    private CustomerManager customerManager;
    public MainSystem(){
        bookManager = new BookManager(FileLoader.loadBook());
        orderManager = new OrderManager();
        customerManager = new CustomerManager();
    }
    
    public static MainSystem getInstance(){
        if (instance == null){
            instance = new MainSystem();
        }
        return instance;
    }
    
    public OrderManager getOrderManager() {
        return orderManager;
    }
    
    public CustomerManager getCustomerManager() {
        return customerManager;
    }
    
    public BookManager getBookManager() {
        return bookManager;
    }
    
    public void createBook(Book book, int quantity) {
        bookManager.addBook(book,quantity);
    }
    
    public void removeBook(String id) {
        bookManager.removeBook(id);
    }
}

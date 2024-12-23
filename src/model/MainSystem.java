package model;


import util.FileLoader;

import java.util.Set;

public class MainSystem {
    private static MainSystem instance;
    private BookManager bookManager;
    private OrderManager orderManager;
    private CustomerManager customerManager;
    public MainSystem(){
        bookManager = new BookManager(FileLoader.loadBook());
        orderManager = new OrderManager();
        customerManager = new CustomerManager(FileLoader.loadCustomer());
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

    public Customer findCustomer(String supposeId, String supposeName, String supposePhone){
        return customerManager.findCustomer(supposeId, supposeName, supposePhone);
    }

    public void addCustomer(Customer supposeCustomer){
        customerManager.addCustomer(supposeCustomer);
    }

    public void removeCustomer(String supposeIdCustomer, String supposeName, String supposePhone) {
        customerManager.removeCustomer(supposeIdCustomer, supposeName, supposePhone);
    }

    public void resetListCustomer(){
        customerManager.resetListCustomer();
    }

    public void updateListCustomer(Set<Customer> newData){
        customerManager.updateListCustomer(newData);
    }

    public void confirmUpdateCustomer(String otherId, String otherName, String otherPhone){
        customerManager.confirmUpdateCustomer(otherId, otherName, otherPhone);
    }
}

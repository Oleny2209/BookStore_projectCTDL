package model;

import java.util.Set;

public class Model implements IModel{
    private MainSystem mainSystem;
    public Model(MainSystem mainSystem){
        this.mainSystem = mainSystem;
    }
    
    public MainSystem getMainSystem() {
        return mainSystem;
    }
    
    @Override
    public void createBook(Book book, int quantity) {
       this.mainSystem.createBook(book,quantity);
    }
    
    @Override
    public void removeBook(String id) {
        this.mainSystem.removeBook(id);
    }

    @Override
    public void addCustomer(Customer supposeCustomer){
        this.mainSystem.addCustomer(supposeCustomer);
    }

    @Override
    public void removeCustomer(String supposeIdCustomer, String supposeName, String supposePhone) {
        this.mainSystem.removeCustomer(supposeIdCustomer, supposeName, supposePhone);
    }

    @Override
    public void resetListCustomer(){
        this.mainSystem.resetListCustomer();
    }

    @Override
    public void updateListCustomer(Set<Customer> newData){
        this.mainSystem.updateListCustomer(newData);
    }

    @Override
    public void confirmUpdateCustomer(String otherId, String otherName, String otherPhone){
        this.mainSystem.confirmUpdateCustomer(otherId, otherName, otherPhone);
    }
    
    @Override
    public void confirmUpdateCustomer(CustomerManager modelTMP) {
        this.mainSystem.setCustomerManager(modelTMP);
    }
    
    @Override
    public Customer findCustomer(String supposeId, String supposeName, String supposePhone){
        return this.mainSystem.findCustomer(supposeId, supposeName, supposePhone);
    }
    
    @Override
    public void addOrder(Order order) {
        this.mainSystem.getOrderManager().addOrder(order);
    }
    
    @Override
    public void removeBookInOrder(String id, int idOrder) {
        this.mainSystem.removeBookInOrder(id,idOrder);
    }
    
}

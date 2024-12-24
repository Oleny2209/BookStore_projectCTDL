package modelTMP;

import java.util.Set;

public interface IModel {
    void createBook(Book book,int quantity);
    
    void removeBook(String id);
    
    MainSystem getMainSystem();

    void addCustomer(Customer supposeCustomer);

    void removeCustomer(String supposeIdCustomer, String supposeName, String supposePhone);

    void resetListCustomer();

    void updateListCustomer(Set<Customer> newData);

    void confirmUpdateCustomer(String otherId, String otherName, String otherPhone);
    
    void confirmUpdateCustomer(CustomerManager modelTMP);

    Customer findCustomer(String supposeId, String supposeName, String supposePhone);
    
    void addOrder(Order order);
    
    void removeBookInOrder(String idBook, int idOrder);
}

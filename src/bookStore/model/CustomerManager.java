package bookStore.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerManager {
    private Set<Customer> listCustomer;

    public CustomerManager() {
        this.listCustomer = new HashSet<>();
    }

    public boolean addCustomer(Customer cus) {
        return listCustomer.add(cus);
    }
    // Tìm kiếm
    Set<Customer> findCustomer(String supposeId, String supposeName, int supposePhone) {
        return listCustomer.stream()
                .filter(customer -> customer.getIdCustomer().equals(supposeId) || customer.getName().equals(supposeName) || customer.getPhone() == supposePhone)
                .collect(Collectors.toSet());
    }

    // Thêm khách hàng
    boolean addCustomer(String supposeIdCustomer, String supposeName, int supposePhone) {
        return listCustomer.add(new Customer(supposeIdCustomer, supposeName, supposePhone, new ArrayList<>()));
    };

    // Xoá khách hàng
    Set<Customer> removeCustomer(String supposeIdCustomer, String supposeName, int supposePhone) {
        return listCustomer.stream()
                .filter(customer -> !(customer.getIdCustomer().equals(supposeIdCustomer) || customer.getName().equals(supposeName) || customer.getPhone() == supposePhone))
                .collect(Collectors.toSet());
    };

    // Xoá danh sách
    void resetListCustomer(){
        listCustomer.clear();
    }

    // Cập nhật danh sách
    Set<Customer> updateListCustomer(Set<Customer> newData){
        listCustomer.clear();
        listCustomer.addAll(newData);
        return listCustomer;
    }
}

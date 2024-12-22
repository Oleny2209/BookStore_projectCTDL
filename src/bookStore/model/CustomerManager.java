package bookStore.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerManager {
    private Set<Customer> listCustomer;

    public CustomerManager() {
        this.listCustomer = new HashSet<>();
    }

    public boolean addCustomer(Customer cus) {
        return listCustomer.add(cus);
    }
    // Tìm kiếm
    Set<Customer> findCustomer(String supposeId, String supposeName, String supposePhone) {
        return listCustomer.stream()
                .filter(customer -> customer.getIdCustomer().equals(supposeId) || customer.getName().equals(supposeName) || customer.getPhone().equals(supposePhone))
                .collect(Collectors.toSet());
    }

    // Thêm khách hàng
    boolean addCustomer(String supposeIdCustomer, String supposeName, String supposePhone) {
        return listCustomer.add(new Customer(supposeIdCustomer, supposeName, supposePhone, new ArrayList<>()));
    };

    // Xoá khách hàng
    Set<Customer> removeCustomer(String supposeIdCustomer, String supposeName, int supposePhone) {
        return listCustomer.stream()
                .filter(customer -> !(customer.getIdCustomer().equals(supposeIdCustomer) || customer.getName().equals(supposeName) || customer.getPhone().equals(supposePhone)))
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

    // Xác nhận thay đổi
    void confirmUpdateCustomer(String otherName, String otherPhone){
        Set<Customer> foundCustomers = findCustomer(null, null, null);
        if(foundCustomers.isEmpty()){
            System.out.println("Không hiển thị bất cứ ai");
        }
        // Lấy khách hàng đầu tiên trong tập kết quả
        Customer customer = foundCustomers.iterator().next();

        // Cập nhật thông tin
        if (otherName != null && !otherName.trim().isEmpty()) {
            customer.setName(otherName.trim());
        }
        // Kiểm tra xem nếu SĐT bắt đầu bằng 0 và nó có 10 index số (theo chuẩn VN)
        if (otherPhone.startsWith("0") && otherPhone.length() == 10) {
            customer.setPhone(otherPhone);
        }
    }
}

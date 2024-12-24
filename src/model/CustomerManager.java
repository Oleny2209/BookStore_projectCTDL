package model;

import java.util.Set;

public class CustomerManager {
    private Set<Customer> listCustomer;

    public CustomerManager(Set<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }

    public Set<Customer> getListCustomer() {
        return listCustomer;
    }
    // Tìm kiếm
    public Customer findCustomer(String supposeId, String supposeName, String supposePhone) {
        return listCustomer.stream()
                .filter(customer ->
                                (customer.getIdCustomer().equals(supposeId)) ||
                                (customer.getName().equals(supposeName)) ||
                                (customer.getPhone().equals(supposePhone))
                )
                .findFirst()
                .orElse(null);
    }

    // Thêm khách hàng
    public void addCustomer(Customer supposeCustomer) {
        listCustomer.add(supposeCustomer);
    }

    // Xoá khách hàng
    public void removeCustomer(String supposeIdCustomer, String supposeName, String supposePhone) {
        listCustomer.removeIf(customer -> customer.getIdCustomer().equals(supposeIdCustomer) || customer.getName().equals(supposeName) || customer.getPhone().equals(supposePhone));
    }

    // Xoá danh sách
    public void resetListCustomer(){
        listCustomer.clear();
    }

    // Cập nhật danh sách
    public void updateListCustomer(Set<Customer> newData){
        listCustomer.clear();
        listCustomer.addAll(newData);
    }

    // Xác nhận thay đổi
    public void confirmUpdateCustomer(String otherId, String otherName, String otherPhone){
        Customer foundCustomers = findCustomer(null, null, null);
        if(foundCustomers == null){
            System.out.println("Không hiển thị bất cứ ai");
        } else {
            // Cập nhật tên khách
            if (otherName != null && !otherName.trim().isEmpty()) {
                foundCustomers.setName(otherName.trim());
            }
            // Cập nhật SĐT khách
            if (otherPhone.startsWith("0") && otherPhone.length() == 10) {
                foundCustomers.setPhone(otherPhone);
            }
            // Cập nhật ID khách
            if (otherId != null && otherId.length() == 7){
                foundCustomers.setIdCustomer(otherId);
            }
        }
    }
}

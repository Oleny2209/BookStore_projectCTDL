package model;

import java.time.LocalDate;
import java.util.List;

public class Customer {
    private String idCustomer;
    private String name;
    private String phone;
    private LocalDate birth;
    private String type;
    private double totalMoney;
    private List<Order> orderByAcc;
    
    public Customer(String idCustomer, String name, LocalDate birth, String type, String phone, double totalMoney) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.type = type;
        this.totalMoney = totalMoney;
    }
    
    public String getIdCustomer() {
        return idCustomer;
    }
    
    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDate getBirth() {
        return birth;
    }
    
    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getTotalMoney() {
        return totalMoney;
    }
    
    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public List<Order> getOrderByAcc() {
        return orderByAcc;
    }
    
    public void setOrderByAcc(List<Order> orderByAcc) {
        this.orderByAcc = orderByAcc;
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer='" + idCustomer + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                ", type='" + type + '\'' +
                ", totalMoney='" + totalMoney + '\'' +
                ", orderByAcc=" + orderByAcc +
                '}';
    }
}

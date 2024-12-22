package model;

import java.util.HashSet;

import java.util.List;

public class Customer {
    private String idCustomer;
    private String name;
    private String phone;
    private List<Order>orderByAcc;
    
    public Customer(String idCustomer, String name, String phone, List<Order> orderByAcc) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.orderByAcc = orderByAcc;
    }
    
    public boolean checkIDCustomer(String id){
        return id.equals(idCustomer);
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

    public List<Order> getOrderByAcc() {
        return orderByAcc;
    }

    public void setOrderByAcc(List<Order> orderByAcc) {
        this.orderByAcc = orderByAcc;
    }


    @Override
    public boolean equals(Object obj){
        if (obj == null || obj.getClass().equals(this.getClass())){
            return false;
        }else {
            Customer that = (Customer) obj;
            return this.idCustomer.equals(that.idCustomer) &&
                    this.name.equals(that.name) &&
                    this.phone == that.phone &&
                    new HashSet<>(this.orderByAcc).containsAll(that.orderByAcc);

        }
    }
    
    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer='" + idCustomer + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", orderByAcc=" + orderByAcc +
                '}';
    }
}

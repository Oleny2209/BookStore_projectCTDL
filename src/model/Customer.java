package model;

import java.util.List;

public class Customer {
    private String idCustomer;
    private String name;
    private int phone;
    private List<Order>orderByAcc;
    
    public Customer(String idCustomer, String name, int phone, List<Order> orderByAcc) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.orderByAcc = orderByAcc;
    }
    
    public boolean checkIDCustomer(String id){
        return id.equals(idCustomer);
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
                    this.orderByAcc.containsAll(that.orderByAcc);
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

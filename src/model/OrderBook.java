package model;


public class OrderBook {
    private Book book;
    private int quantity;
    
    public OrderBook(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
    
}

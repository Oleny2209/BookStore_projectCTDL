package modelTMP;


public class OrderBook {
    private Book book;
    private int quantity;
    
    public OrderBook(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }
    public Book getBook() {
        return book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPriceBook(){
        return this.book.getPrice()*this.quantity;
    }
}

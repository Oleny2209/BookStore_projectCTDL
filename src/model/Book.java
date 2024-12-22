package model;

public class Book {
    private String idBook;
    private String title;
    private double price;
    private String type;
    private String author;
    private String publish;
    private int yearRelease;
    
    public Book(String idBook, String title, double price, String type, String author, String publish, int yearRelease) {
        this.idBook = idBook;
        this.title = title;
        this.price = price;
        this.type = type;
        this.author = author;
        this.publish = publish;
        this.yearRelease = yearRelease;
    }
    //GET & SET method
    public String getIdBook() {
        return idBook;
    }
    
    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getPublish() {
        return publish;
    }
    
    public void setPublish(String publish) {
        this.publish = publish;
    }
    
    public int getYearRelease() {
        return yearRelease;
    }
    
    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "idBook='" + idBook + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", pulish='" + publish + '\'' +
                ", yearRelease=" + yearRelease +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (null == obj || obj.getClass().equals(this.getClass())){
            return false;
        }else {
            Book that = (Book) obj;
            return this.idBook.equals(that.idBook) &&
                    this.title.equals(that.title) &&
                    this.price == that.price &&
                    this.author.equals(that.author) &&
                    this.publish.equals(that.publish) &&
                    this.yearRelease == that.yearRelease;
        }
    }
}

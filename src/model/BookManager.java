package model;

import java.util.*;


public class BookManager {
    private Map<Book, Integer> listBook;
    
    public BookManager(Map<Book, Integer> listBook) {
        this.listBook = listBook;
    }
    
    //Add books to the list
    //if the book is not there then add it to the list
    //if the book is there then add it
    public void addBook(Book book, int n) {
        if (!(listBook.containsKey(book))) {

            listBook.put(book, n);
        } else {
            listBook.put(book,listBook.getOrDefault(book,0)+n);
        }
    }
    
    //Check if this book is still available
    public boolean isAvailable(String idBook) {
        Book book = null;
        if ((book = findBookByID(idBook)) != null) {
            return (listBook.getOrDefault(book, 0) > 0);
        } else return false;
    }
    //Method used to find book by id of book
    public Book findBookByID(String idBook) {
        for (Book book : listBook.keySet()) {
            if (book.getIdBook().equals(idBook)) return book;
        }
        return null;
    }
    
    public Book findBook(String idb){
        for (Book book: listBook.keySet()){
            if (book.getIdBook().equals(idb))return book;
        }
        return null;
    }
    
    public Map<Book, Integer> getListBook(){
        return this.listBook;
    }
    
    public void removeBook(String valueAt){
        listBook.remove(findBook(valueAt));
    }
}

package model;

import util.FileLoader;

import java.util.*;
import java.util.stream.Collectors;


public class BookManager {
    private Map<Book, Integer> listBook;
    
    public BookManager(Map<Book, Integer> listBook) {
        this.listBook = listBook;
    }
    public BookManager(BookManager bookManagerOther){
        this.listBook = bookManagerOther.getListBook();
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
    
    public Map<Book,Integer> findBooksByID(String idBook) {
        return listBook.entrySet().stream().filter(entry -> entry.getKey().getIdBook().equals(idBook))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    public Map<Book,Integer> findBookByTitle(String title){
        return listBook.entrySet().stream().filter(entry -> entry.getKey().getTitle().equals(title))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    
    public Map<Book,Integer> findBookByAuthor(String author){
        return listBook.entrySet().stream().filter(entry -> entry.getKey().getAuthor().equals(author))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    
    public Map<Book,Integer> findBookBetweenYear(int startYear, int endYear){
        return listBook.entrySet().stream()
                .filter(entry -> entry.getKey().getYearRelease() >= startYear && entry.getKey().getYearRelease() <= endYear)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    
    public Map<Book,Integer> findBookByType(String type){
        return listBook.entrySet().stream().filter(entry -> entry.getKey().getType().equals(type))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
    
    public Map<Book, Integer> getListBook(){
        return this.listBook;
    }
    
    public void setListBook(Map<Book, Integer> listBook) {
        this.listBook = listBook;
    }
    
    public void removeBook(String valueAt){
        listBook.remove(findBookByID(valueAt));
    }
    
    public static void main(String[] args) {
        BookManager bookManager = new BookManager(FileLoader.loadBook());
        System.out.println(bookManager.findBookByType("Light novel"));
    }
}

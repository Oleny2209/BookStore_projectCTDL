package model;

public interface IModel {
    void createBook(Book book,int quantity);
    void removeBook(String id);
    MainSystem getMainSystem();
}

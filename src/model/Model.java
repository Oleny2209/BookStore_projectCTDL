package model;

public class Model implements IModel{
    private MainSystem mainSystem;
    public Model(MainSystem mainSystem){
        this.mainSystem = mainSystem;
    }
    
    public MainSystem getMainSystem() {
        return mainSystem;
    }
    
    @Override
    public void createBook(Book book, int quantity) {
       this.mainSystem.createBook(book,quantity);
    }
    
    @Override
    public void removeBook(String id) {
        this.mainSystem.removeBook(id);
    }
}

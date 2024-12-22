package view;

import model.BookManager;

public class SupportEvent {
    public static BookManager getBookManager(){
        return MainFrame.bookManager;
    }
}

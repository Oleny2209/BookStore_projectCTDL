package bookStore.view;

import bookStore.model.BookManager;

public class SupportEvent {
    public static BookManager getBookManager(){
        return MainFrame.bookManager;
    }
}

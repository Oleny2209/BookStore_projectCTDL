package view;

import model.IModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookStoreManagerPanel extends JPanel {
    CardLayout card;
    JPanel mainPanel, bookPanel, mainMenuPanel,orderPanel,customerPanel;
    
    //them model vao constructor
    public BookStoreManagerPanel(IModel model) throws IOException {
        
        setLayout(new BorderLayout());
        
        card = new CardLayout();
        
        mainPanel = new JPanel(card);
        mainMenuPanel = new MainMenuPanel();
        bookPanel = new BookManagerPanel(model);
        orderPanel = new OrderManagerPanel(model);
        customerPanel = new CustomerManagerPanel(model);
        
        mainPanel.add("mainPanel",mainMenuPanel);
        mainPanel.add("bookPanel",bookPanel);
        mainPanel.add("orderPanel",orderPanel);
        mainPanel.add("customerPanel",customerPanel);

        showLayout("mainPanel");
        
        add(mainPanel,BorderLayout.CENTER);
        add(createSidePanel(),BorderLayout.WEST);
        
        
    }
    public void showLayout(String nameLayout){
        card.show(mainPanel,nameLayout);
    }
    
    public JPanel createSidePanel(){
        JPanel res = new JPanel();
        res.setLayout(new GridLayout(10,1));
        res.setBorder(BorderFactory.createMatteBorder(0,0,0,2,new Color(0,0,0)));
        JButton btn = new JButton();
        List<String> stringList = new ArrayList<>();
        stringList.add("Quản Lí Sách");
        stringList.add("Quản Lí Khách Hàng");
        stringList.add("Quản Lí Hóa Đơn");
        for (int i = 0; i < stringList.size();i++){
            btn = new JButton(stringList.get(i));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setHorizontalAlignment(SwingConstants.CENTER);
            btn.setBackground(new Color(255,250,250));
            btn.setPreferredSize(new Dimension(180,20));
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 15));
            btn.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
            switch (i){
                case 0: btn.addActionListener(e -> this.showLayout("bookPanel"));
                case 1: btn.addActionListener(e -> this.showLayout("customerPanel"));
                case 2: btn.addActionListener(e -> this.showLayout("orderPanel"));
            }
            res.add(btn);
        }
        return res;
    }
    
    public static class MainMenuPanel extends JPanel{
        public MainMenuPanel(){
            setLayout(new BorderLayout());
            
            JLabel label = new JLabel("Ứng Dụng Quản Lí Bán Sách",JLabel.CENTER);
            label.setFont(new Font(null,1,20));
            add(label,BorderLayout.CENTER);
        }
    }
}

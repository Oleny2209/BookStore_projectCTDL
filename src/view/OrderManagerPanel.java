package view;

import model.Book;
import model.BookManager;
import model.IModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class OrderManagerPanel extends JPanel {
    JButton btnCreateOrder,btnSearchOrder, buttonAddBook, buttonFind;
    JButton btnRemoveOrder, btnChangeOrder, btnTotal;
    JTextField textIDOrder, textNameCus, textIDC, textDateOrder, textDelivery;//textField cua Panel thong tin hoa don
    JTextField textIDB,textTitle,textAuthor, textPulish, textPrice, textYearPub;//textField cua Panel Thong tin sach trong hoa don
    JTextField txFindIDBook, txFindTitle, txFindStartYearPub, txFindEndYearPub, txFindAuthor;//TF dung cho tim kiem
    JButton btnFind;
    JTextField textTotalPrice, textDiscount, textPriceAfterDiscount;//TF tinh tien
    JComboBox<String> comboFindType;
    DefaultTableModel modelOrder;
    JTable table;
    JScrollPane scrollPane;
    
    public OrderManagerPanel(IModel model){
        setLayout(new BorderLayout());
        JPanel orderInformationPanel = new OrderInformationPanel();
        JPanel listInOrderPanel = new ListInOrderPanel(model);
        
        add(listInOrderPanel,BorderLayout.CENTER);
        add(orderInformationPanel,BorderLayout.NORTH);
    }
    public class OrderInformationPanel extends JPanel{
        public OrderInformationPanel(){
            setLayout(new BorderLayout());
            setSize(new Dimension(400,300));
            TitledBorder titledBorder = new TitledBorder("Hóa Đơn Bán Hàng");
            titledBorder.setTitleFont(new Font("Arial",Font.BOLD,20));
            setBorder(titledBorder);
            
            JPanel insertFormPanel = new JPanel(new GridLayout(3,4,5,5));
            
            JLabel idLabel = new JLabel("Mã Hóa Đơn:");
            textIDOrder = new JTextField("");
            insertFormPanel.add(idLabel);
            insertFormPanel.add(textIDOrder);
            insertFormPanel.add(new JPanel());
            insertFormPanel.add(new JPanel());
            
            
            JLabel nameCusLabel = new JLabel("Tên Khách Hàng:");
            textNameCus = new JTextField("");
            insertFormPanel.add(nameCusLabel);
            insertFormPanel.add(textNameCus);
            
            JLabel idCusLabel = new JLabel("Mã Khách Hàng: ");
            textIDC = new JTextField("");
            insertFormPanel.add(idCusLabel);
            insertFormPanel.add(textIDC);
            
            JLabel orderDateLabel = new JLabel("Ngày đặt hàng:");
            textDateOrder = new JTextField("");
            insertFormPanel.add(orderDateLabel);insertFormPanel.add(textDateOrder);
            
            JLabel deliveryDateLabel = new JLabel("Ngày giao hàng: ");
            textDelivery = new JTextField("");
            insertFormPanel.add(deliveryDateLabel);insertFormPanel.add(textDelivery);
            
            setLayout(new BorderLayout());
            
            JPanel boundPanel = new JPanel(new FlowLayout());
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1,2));
            btnCreateOrder = new JButton("Tạo Hóa đơn");
            btnSearchOrder = new JButton("Tìm Hóa Đơn");
            
            buttonPanel.add(btnCreateOrder);
            buttonPanel.add(btnSearchOrder);
            
            boundPanel.add(buttonPanel);
            add(boundPanel,BorderLayout.SOUTH);
            add(insertFormPanel,BorderLayout.CENTER);
        }
    }
    
    public class ListInOrderPanel extends JPanel{
        public ListInOrderPanel(IModel model){
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createMatteBorder(1,0,1,0,new Color(0,0,0)));
            
            //Panel the hien thong tin sach, tim sach, them sach
            JPanel boundInfoBookPanel = new JPanel(new BorderLayout());
            JPanel insertBookPanel = new JPanel();
            insertBookPanel.setLayout(new GridLayout(6,2));
            TitledBorder titledBorder = new TitledBorder("Thông Tin Sách");
            titledBorder.setTitleFont(new Font("Arial",Font.BOLD,15));
            insertBookPanel.setBorder(titledBorder);
            
            JLabel idBookLabel = new JLabel("ID Sách:",JLabel.LEFT);
            textIDB = new JTextField("");
            
            JLabel titleLabel = new JLabel("Tên Sách");
            textTitle = new JTextField("");
            
            JLabel authorLabel = new JLabel("Tác Giả:");
            textAuthor = new JTextField("");
            
            JLabel publishLabel = new JLabel("Nhà Xuất Bản");
            textPulish = new JTextField("");
            
            JLabel priceLabel = new JLabel("Giá Sách");
            textPrice = new JTextField("");
            
            JLabel yearPubLabel = new JLabel("Năm Xuất Bản: ");
            textYearPub = new JTextField();
            
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JPanel boundButtonPanel = new JPanel(new GridLayout(1,2,5,5));
            buttonPanel.add(boundButtonPanel);
            
            buttonAddBook = new JButton("Thêm");
            buttonFind = new JButton("Tìm");
            boundButtonPanel.add(buttonAddBook);
            boundButtonPanel.add(buttonFind);
            
            insertBookPanel.add(idBookLabel);
            insertBookPanel.add(textIDB);
            insertBookPanel.add(titleLabel);
            insertBookPanel.add(textTitle);
            insertBookPanel.add(authorLabel);
            insertBookPanel.add(textAuthor);
            insertBookPanel.add(publishLabel);
            insertBookPanel.add(textPulish);
            insertBookPanel.add(priceLabel);
            insertBookPanel.add(textPrice);
            insertBookPanel.add(yearPubLabel);
            insertBookPanel.add(textYearPub);
            
            boundInfoBookPanel.add(insertBookPanel,BorderLayout.NORTH);
            boundInfoBookPanel.add(buttonPanel,BorderLayout.CENTER);
            boundInfoBookPanel.add(new FindPanel(model),BorderLayout.SOUTH);
            add(boundInfoBookPanel,BorderLayout.WEST);
            
            JPanel orderInfoPanel = new JPanel(new BorderLayout());
            TitledBorder titledBorder1 = new TitledBorder("Hóa Đơn");
            titledBorder1.setTitleFont(new Font("Arial",Font.BOLD,15));
            orderInfoPanel.setBorder(titledBorder1);
            
            //Panel chua ban hoa don
            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BorderLayout());
            String[]columns = {"STT","Mã Sách","Tên Sách","Tác Giả","Đơn Giá","Số Lượng","Thành Tiền"};
            modelOrder = new DefaultTableModel(columns,0);
            table = new JTable(modelOrder);
            table.getTableHeader().setReorderingAllowed(false);
            table.setEnabled(false);
            scrollPane = new JScrollPane(table);
            
            tablePanel.add(scrollPane);
            orderInfoPanel.add(tablePanel);
            
            //panel thong ke tien
            JPanel boundStatsPanel = new JPanel(new BorderLayout());
            
            JPanel statsPanel = new JPanel(new GridLayout(1,6));
            JLabel labelTotal = new JLabel("Tổng Thành Tiền");
            textTotalPrice = new JTextField();
            
            JLabel discountLabel = new JLabel("Giảm Giá");
            textDiscount = new JTextField();
            
            JLabel totalPriceLabel = new JLabel("Giá sau khi giảm");
            textPriceAfterDiscount = new JTextField();
            
            statsPanel.add(labelTotal);statsPanel.add(textTotalPrice);
            statsPanel.add(discountLabel);statsPanel.add(textDiscount);
            statsPanel.add(totalPriceLabel);statsPanel.add(textPriceAfterDiscount);
            boundStatsPanel.add(statsPanel,BorderLayout.CENTER);
            
            JPanel statusButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            btnRemoveOrder = new JButton("Xóa");
            btnChangeOrder = new JButton("Sửa");
            btnTotal = new JButton("Thanh Toán");
            
            statusButtonPanel.add(btnRemoveOrder);
            statusButtonPanel.add(btnChangeOrder);
            statusButtonPanel.add(btnTotal);
            boundStatsPanel.add(statusButtonPanel,BorderLayout.SOUTH);
            
            tablePanel.add(boundStatsPanel,BorderLayout.SOUTH);
            add(orderInfoPanel,BorderLayout.CENTER);
        }
    }
    public class FindPanel extends JPanel {
        public FindPanel(IModel model) {
            setLayout(new BorderLayout());
            JPanel boundFindPanel = new JPanel(new BorderLayout());
            TitledBorder titledBorder = new TitledBorder("Tìm Kiếm Sách");
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 15));
            setBorder(titledBorder);
            
            JPanel insertPanel = new JPanel(new GridLayout(6, 2, 5, 5));
            JLabel idFindLabel = new JLabel("Mã Sách");
            txFindIDBook = new JTextField();
            
            JLabel titleFindLabel = new JLabel("Tên Sách");
            txFindTitle = new JTextField();
            
            JLabel typeFindLabel = new JLabel("Thể Loại");
            String[] type = {"", "Light novel", "Triết học", "Truyện tranh", "Khoa học", "Tâm lý học", "Thơ kịch"};
            comboFindType = new JComboBox<String>(type);
            
            JLabel authorFindLabel = new JLabel("Tên Tác Giả");
            txFindAuthor = new JTextField();
            
            JLabel yearStartPubFindLabel = new JLabel("Năm Xuất Bản Từ:");
            txFindStartYearPub = new JTextField();
            
            JLabel yearEndPubFindLabel = new JLabel("Đến:");
            txFindEndYearPub = new JTextField();
            
            
            insertPanel.add(idFindLabel);
            insertPanel.add(txFindIDBook);
            insertPanel.add(titleFindLabel);
            insertPanel.add(txFindTitle);
            insertPanel.add(typeFindLabel);
            insertPanel.add(comboFindType);
            insertPanel.add(authorFindLabel);
            insertPanel.add(txFindAuthor);
            insertPanel.add(yearStartPubFindLabel);
            insertPanel.add(txFindStartYearPub);
            insertPanel.add(yearEndPubFindLabel);
            insertPanel.add(txFindEndYearPub);
            
            JPanel buttonFindPanel = new JPanel(new GridLayout(1, 1));
            btnFind = new JButton("Tìm");
            btnFind.setBackground(new Color(255, 250, 250));
            btnFind.setFocusable(false);
            btnFind.addActionListener(e -> {
                if (e.getSource().equals(btnFind)) {
                    
                    
                    BookManager category = new BookManager(model.getMainSystem().getBookManager());
                    
                    Map<Book, Integer> resultFind = new TreeMap<Book,Integer>((o1, o2) -> o1.getIdBook().compareTo(o2.getIdBook()));
                    if (!txFindIDBook.getText().trim().isEmpty()) {
                        String idBook = txFindIDBook.getText().trim();
                        resultFind.putAll(category.findBooksByID(idBook));
                    } else {
                        if (!txFindStartYearPub.getText().trim().isEmpty() && !txFindEndYearPub.getText().trim().isEmpty()) {
                            resultFind = new TreeMap<Book,Integer>((o1, o2) -> o1.getIdBook().compareTo(o2.getIdBook()));
                            int start = Integer.parseInt(txFindStartYearPub.getText().trim());
                            int end = Integer.parseInt(txFindEndYearPub.getText().trim());
                            resultFind.putAll(category.findBookBetweenYear(start, end));
                            category.setListBook(resultFind);
                        }
                        if (!txFindTitle.getText().trim().isEmpty()) {
                            resultFind = new TreeMap<Book,Integer>((o1, o2) -> o1.getIdBook().compareTo(o2.getIdBook()));
                            String title = txFindTitle.getText().trim();
                            resultFind.putAll(category.findBookByTitle(title));
                            category.setListBook(resultFind);
                        }
                        if (!Objects.requireNonNull(comboFindType.getSelectedItem()).toString().isEmpty()) {
                            resultFind = new TreeMap<Book,Integer>((o1, o2) -> o1.getIdBook().compareTo(o2.getIdBook()));
                            String typeBook = Objects.requireNonNull(comboFindType.getSelectedItem()).toString();
                            resultFind.putAll(category.findBookByType(typeBook));
                            category.setListBook(resultFind);
                        }
                        if (!txFindAuthor.getText().trim().isEmpty()) {
                            resultFind = new TreeMap<Book,Integer>((o1, o2) -> o1.getIdBook().compareTo(o2.getIdBook()));
                            String author = txFindAuthor.getText().trim();
                            resultFind.putAll(category.findBookByAuthor(author));
                            category.setListBook(resultFind);
                        }
                        
                    }
                    updateTableFind(resultFind);
                    
                    
                }
            });
            buttonFindPanel.add(btnFind);
            
            boundFindPanel.add(buttonFindPanel, BorderLayout.CENTER);
            boundFindPanel.add(insertPanel, BorderLayout.NORTH);
            
            
            add(boundFindPanel, BorderLayout.NORTH);
        }
        
        void updateTableFind(Map<Book, Integer> mapBook) {
            while (modelOrder.getRowCount() > 0) modelOrder.removeRow(0);
            for (Map.Entry<Book, Integer> entry : mapBook.entrySet()) {
                String idBook = entry.getKey().getIdBook();
                String title = entry.getKey().getTitle();
                String type = entry.getKey().getType();
                String author = entry.getKey().getAuthor();
                String publish = entry.getKey().getPublish();
                int yearRelease = entry.getKey().getYearRelease();
                int quantity = entry.getValue();
                double price = entry.getKey().getPrice();
                
                modelOrder.addRow(new Object[]{modelOrder.getRowCount() + 1, idBook, title, type, author, publish, yearRelease, price, quantity});
            }
            System.out.println("Complete Updated Find");
        }
    }
}

package view;

import model.*;
import util.AnalyzeDate;
import util.TextPrompt;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderManagerPanel extends JPanel {
    JButton btnCreateOrder,btnSearchOrder, buttonAddBook, buttonFind, buttonFindCustomer;
    JButton btnRemoveOrder, btnTotal;
    JTextField textIDOrder, textNameCus, textIDCustomer, textDateOrder, textDelivery;//textField cua Panel thong tin hoa don
    JTextField textIDB,textTitle,textAuthor, textPulish, textPrice, textYearPub, textType, textQuantity;//textField cua Panel Thong tin sach trong hoa don
    JTextField textTotalPrice, textDiscount, textPriceAfterDiscount;
    static Order oderRealTime;
    //textField cua panel tinh tien
    DefaultTableModel modelOrder;
    JTable table;
    JScrollPane scrollPane;
    int countOrder =1;
    public OrderManagerPanel(IModel model){
        setLayout(new BorderLayout());
        JPanel orderInformationPanel = new OrderInformationPanel(model);
        JPanel listInOrderPanel = new ListInOrderPanel(model);
        
        add(listInOrderPanel,BorderLayout.CENTER);
        add(orderInformationPanel,BorderLayout.NORTH);
    }
    public class OrderInformationPanel extends JPanel{
        public OrderInformationPanel(IModel model){
            setLayout(new BorderLayout());
            setSize(new Dimension(400,300));
            TitledBorder titledBorder = new TitledBorder("Hóa Đơn Bán Hàng");
            titledBorder.setTitleFont(new Font("Arial",Font.BOLD,20));
            setBorder(titledBorder);
            
            JPanel insertFormPanel = new JPanel(new GridLayout(2,6,5,5));
            
            JLabel idLabel = new JLabel("Mã Hóa Đơn:");
            textIDOrder = new JTextField(countOrder++ +"");
            insertFormPanel.add(idLabel);
            insertFormPanel.add(textIDOrder);
            
            JLabel nameCusLabel = new JLabel("Tên Khách Hàng:");
            textNameCus = new JTextField("");
            insertFormPanel.add(nameCusLabel);
            insertFormPanel.add(textNameCus);
            
            JLabel idCusLabel = new JLabel("Mã Khách Hàng: ");
            textIDCustomer = new JTextField("");
            insertFormPanel.add(idCusLabel);
            insertFormPanel.add(textIDCustomer);
            
            insertFormPanel.add(new JPanel());
            insertFormPanel.add(new JPanel());
            JLabel orderDateLabel = new JLabel("Ngày đặt hàng:");
            textDateOrder = new JTextField(10);
            TextPrompt tp1 = new TextPrompt("dd / mm / yyyy", textDateOrder);
            insertFormPanel.add(orderDateLabel);insertFormPanel.add(textDateOrder);
            
            JLabel deliveryDateLabel = new JLabel("Ngày giao hàng: ");
            textDelivery = new JTextField(8);
            TextPrompt tp2 = new TextPrompt("dd / mm / yyyy", textDelivery);
            insertFormPanel.add(deliveryDateLabel);insertFormPanel.add(textDelivery);
            
            setLayout(new BorderLayout());
            
            JPanel boundPanel = new JPanel(new FlowLayout());
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1,3,5,5));
            btnCreateOrder = new JButton("Tạo Hóa đơn");
            btnSearchOrder = new JButton("Tìm Hóa Đơn");
            buttonFindCustomer = new JButton("Tìm Khách Hàng");
            
            btnCreateOrder.addActionListener(e -> {
                    try {
                        int idOrder = Integer.parseInt(textIDOrder.getText().trim());
                        String nameCus = textNameCus.getText().trim();
                        String idCustomer = textIDCustomer.getText().trim();
                        LocalDate orderDate = AnalyzeDate.convertDayFomart(textDateOrder.getText().trim());
                        LocalDate deliveryDate = AnalyzeDate.convertDayFomart(textDelivery.getText().trim());
                        List<OrderBook>list = new ArrayList<>();
                        model.findCustomer(idCustomer,nameCus,"").setTotalMoney(12*12);
                        Order newOrder = new Order(idOrder, orderDate, deliveryDate, model.findCustomer(idCustomer, nameCus, ""), new ArrayList<>());
                        oderRealTime = newOrder;
                        System.out.println(model.getMainSystem().getOrderManager().getAllOrders().size());
                        model.addOrder(newOrder);
                        JOptionPane.showMessageDialog(null, "Đã tạo thành công hóa đơn với ID:" + idOrder,
                                "Thông Báo", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin của hóa đơn",
                                "Nhắc nhở", JOptionPane.ERROR_MESSAGE);
                    }
                updateTable(model,Integer.parseInt(textIDOrder.getText().trim()));
            });
            btnSearchOrder.addActionListener(e -> {
                    updateTable(model,Integer.parseInt(textIDOrder.getText().trim()));
            });
            
            buttonFindCustomer.addActionListener(e ->{
                    String nameCus = textNameCus.getText().trim();
                    String idCustomer = textIDCustomer.getText().trim();
                    Customer cus = model.findCustomer(idCustomer,"","");
                    textNameCus.setText(cus.getName());
                    textIDCustomer.setText(cus.getIdCustomer());
            });
            
            buttonPanel.add(btnCreateOrder);
            buttonPanel.add(btnSearchOrder);
            buttonPanel.add(buttonFindCustomer);
            
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
            insertBookPanel.setLayout(new GridLayout(4,4,5,5));
            TitledBorder titledBorder = new TitledBorder("Thông Tin Sách");
            titledBorder.setTitleFont(new Font("Arial",Font.BOLD,15));
            insertBookPanel.setBorder(titledBorder);
            
            JLabel idBookLabel = new JLabel("ID Sách:",JLabel.LEFT);
            textIDB = new JTextField(SwingConstants.CENTER);
            
            JLabel titleLabel = new JLabel("Tên Sách");
            textTitle = new JTextField(SwingConstants.CENTER);
            textTitle.setFont(new Font("Arial",Font.PLAIN,11));
            textTitle.setEditable(false);
            
            JLabel typeLabel = new JLabel("Thể Loại");
            textType = new JTextField(SwingConstants.CENTER);
            textType.setEditable(false);
            
            JLabel authorLabel = new JLabel("Tác Giả:");
            textAuthor = new JTextField(SwingConstants.CENTER);
            textAuthor.setEditable(false);
            
            JLabel publishLabel = new JLabel("Nhà Xuất Bản");
            textPulish = new JTextField(SwingConstants.CENTER);
            textPulish.setEditable(false);
            
            JLabel priceLabel = new JLabel("Giá Sách");
            textPrice = new JTextField(SwingConstants.CENTER);
            textPrice.setEditable(false);
            
            JLabel yearPubLabel = new JLabel("Năm Xuất Bản: ");
            textYearPub = new JTextField(SwingConstants.CENTER);
            textYearPub.setEditable(false);
            
            JLabel quantityLabel = new JLabel("Số lượng:");
            textQuantity = new JTextField(SwingConstants.CENTER);
            
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JPanel boundButtonPanel = new JPanel(new GridLayout(1,2,5,5));
            buttonPanel.add(boundButtonPanel);
            
            buttonAddBook = new JButton("Thêm");
            buttonFind = new JButton("Tìm");
            
            buttonFind.addActionListener(e ->{
                    Book book = model.getMainSystem().getBookManager().findBookByID(textIDB.getText().trim());
                    textTitle.setText(book.getTitle());
                    textType.setText(book.getType());
                    textAuthor.setText(book.getAuthor());
                    textYearPub.setText(""+book.getYearRelease());
                    textPrice.setText(""+book.getPrice());
                    textPulish.setText(book.getPublish());
            });
            
            buttonAddBook.addActionListener(e -> {
                    String idBook = textIDB.getText().trim();
                    String title = textTitle.getText().trim();
                    String type = textType.getText().trim();
                    String author = textAuthor.getText().trim();
                    String publish = textPulish.getText().trim();
                    int yearRelease = Integer.parseInt(textYearPub.getText().trim());
                    int quantity = Integer.parseInt(textQuantity.getText().trim());
                    double price = Double.parseDouble(textPrice.getText().trim());
                    
                    BookManager bookManager = model.getMainSystem().getBookManager();
                    Book key = bookManager.findBookByID(idBook);
                    try {
                        if (quantity > bookManager.findBooksByID(idBook).get(key)){
                            JOptionPane.showMessageDialog(null,"Đã vượt quá số lượng trong kho","Nhắc nhở",JOptionPane.ERROR_MESSAGE);
                        }else {
                            Book newBook = new Book(idBook, title, price, type, author, publish, yearRelease);
                            oderRealTime.getListOrder().add(new OrderBook(newBook,quantity));
                            textIDB.setText("");
                            textTitle.setText("");
                            textType.setText("");
                            textAuthor.setText("");
                            textPulish.setText("");
                            textYearPub.setText("");
                            textQuantity.setText("");
                            textPrice.setText("");
                        }
                        updateTable(model,Integer.parseInt(textIDOrder.getText().trim()));
                        
                        double totalPrice = model.getMainSystem().getOrderManager().calculateTotalPrice(Integer.parseInt(textIDOrder.getText().trim()));
                        textTotalPrice.setText(totalPrice+"");
                    }catch (NullPointerException exception){
                        JOptionPane.showMessageDialog(null,"Vui lòng tạo hóa đơn trước khi thêm sách vào hóa đơn","Nhắc nhở",JOptionPane.ERROR_MESSAGE);
                        
                    }
            });
            
            boundButtonPanel.add(buttonAddBook);
            boundButtonPanel.add(buttonFind);
            
            insertBookPanel.add(idBookLabel);
            insertBookPanel.add(textIDB);
            insertBookPanel.add(titleLabel);
            insertBookPanel.add(textTitle);
            insertBookPanel.add(typeLabel);
            insertBookPanel.add(textType);
            insertBookPanel.add(authorLabel);
            insertBookPanel.add(textAuthor);
            insertBookPanel.add(publishLabel);
            insertBookPanel.add(textPulish);
            insertBookPanel.add(yearPubLabel);
            insertBookPanel.add(textYearPub);
            insertBookPanel.add(priceLabel);
            insertBookPanel.add(textPrice);
            insertBookPanel.add(quantityLabel);
            insertBookPanel.add(textQuantity);
            
            boundInfoBookPanel.add(insertBookPanel,BorderLayout.NORTH);
            boundInfoBookPanel.add(buttonPanel,BorderLayout.CENTER);
            add(boundInfoBookPanel,BorderLayout.NORTH);
            
            //Table area
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
            scrollPane = new JScrollPane(table);
            
            tablePanel.add(scrollPane);
            orderInfoPanel.add(tablePanel);
            
            //panel thong ke tien
            JPanel boundStatsPanel = new JPanel(new BorderLayout());
            
            JPanel statsPanel = new JPanel(new GridLayout(1,6,10,5));
            JLabel labelTotal = new JLabel("Tổng Thành Tiền",JLabel.RIGHT);
            textTotalPrice = new JTextField();
            
            JLabel discountLabel = new JLabel("Giảm Giá",JLabel.RIGHT);
            textDiscount = new JTextField();
            
            JLabel totalPriceLabel = new JLabel("Giá sau khi giảm",JLabel.RIGHT);
            textPriceAfterDiscount = new JTextField();
            
            statsPanel.add(labelTotal);statsPanel.add(textTotalPrice);
            statsPanel.add(discountLabel);statsPanel.add(textDiscount);
            statsPanel.add(totalPriceLabel);statsPanel.add(textPriceAfterDiscount);
            boundStatsPanel.add(statsPanel,BorderLayout.CENTER);
            
            JPanel statusButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            btnRemoveOrder = new JButton("Xóa");
            btnTotal = new JButton("Thanh Toán");
            
            btnRemoveOrder.addActionListener(e -> {
                if (e.getSource().equals(btnRemoveOrder)){
                    int index = table.getSelectedRow();
                    String idBook = modelOrder.getValueAt(index, 1).toString().trim();
                    int idOrder = Integer.parseInt(textIDOrder.getText().trim());
                    model.removeBookInOrder(idBook,idOrder);
                }
                updateTable(model,Integer.parseInt(textIDOrder.getText().trim()));
                double totalPrice = model.getMainSystem().getOrderManager().calculateTotalPrice(Integer.parseInt(textIDOrder.getText().trim()));
                textTotalPrice.setText(totalPrice+"");
            });
            
            btnTotal.addActionListener(e ->{
                if (e.getSource().equals(btnTotal)){
                    double discount = Double.parseDouble(textDiscount.getText().trim());
                    double totalPrice = model.getMainSystem().getOrderManager().calculatePriceAfterDiscount(Integer.parseInt(textIDOrder.getText().trim()),discount);
                    textPriceAfterDiscount.setText(totalPrice+"");
                    String nameCus = textNameCus.getText().trim();
                    String idCustomer = textIDCustomer.getText().trim();
                    Customer cus = model.findCustomer(idCustomer,idCustomer,"");
                    cus.setTotalMoney(totalPrice);
                }
            });
            
            statusButtonPanel.add(btnRemoveOrder);
            statusButtonPanel.add(btnTotal);
            boundStatsPanel.add(statusButtonPanel,BorderLayout.SOUTH);
            
            tablePanel.add(boundStatsPanel,BorderLayout.SOUTH);
            add(orderInfoPanel,BorderLayout.CENTER);
        }
    }
    public void updateTable(IModel model, int idOrder){
        while (modelOrder.getRowCount() > 0) modelOrder.removeRow(0);
        List<OrderBook> list = model.getMainSystem().getOrderManager().findOrderById(idOrder).getListOrder();
        for (OrderBook orderBook : list){
            String[]row = new String[]{modelOrder.getRowCount()+1 + "",
                    orderBook.getBook().getIdBook(), orderBook.getBook().getType(), orderBook.getBook().getAuthor(),
                    orderBook.getBook().getPrice() + "", orderBook.getQuantity() +"", orderBook.getPriceBook() +""};
            modelOrder.addRow(row);
        }
        table.setModel(modelOrder);
    }
}

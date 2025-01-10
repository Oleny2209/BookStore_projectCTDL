package view;

import model.IModel;
import model.*;
import util.AnalyzeDate;
import util.TextPrompt;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CustomerManagerPanel extends JPanel {
    JButton addBtn, deleteBtn;
    JTextField textSmallId, textLargeId, textCustomerName, textBirthday, textCustomerPhone, textCustomerSum;
    JButton restartBtn, resetBtn, checkBtn, findBtn;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;
    static Set<Customer> tmpModel;
    
    public CustomerManagerPanel(IModel model) {
        setLayout(new BorderLayout());
        
        add(ButtonManagerPanel(model), BorderLayout.NORTH);
        add(moreButtonPlease(model), BorderLayout.CENTER);
        add(ListCustomerPanel(model), BorderLayout.SOUTH);
    }
    
    public JPanel ButtonManagerPanel(IModel model) {
        JPanel informationPanel = new JPanel(new BorderLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Nhập thông tin khách hàng");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20));
        informationPanel.setBorder(titledBorder);
        
        // Panel bên trái
        JPanel leftPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // 4 hàng, 2 cột, khoảng cách 5px
        JLabel customerId = new JLabel("Mã khách hàng:");
        JPanel idInputPanel = new JPanel(new BorderLayout());
        textSmallId = new JTextField(5); // Ô nhỏ chứa STT
        textSmallId.setEditable(false);
        textLargeId = new JTextField(); // Ô lớn chứa mã KH
        idInputPanel.add(textSmallId, BorderLayout.WEST);
        idInputPanel.add(textLargeId, BorderLayout.CENTER);
        JLabel customerName = new JLabel("Tên khách hàng:");
        textCustomerName = new JTextField();
        JLabel customerBirth = new JLabel("Số điện thoại:");
        textCustomerPhone = new JTextField();
        JLabel typeCustomer = new JLabel("Tổng tiền:");
        textCustomerSum = new JTextField();
        textCustomerSum.setEditable(false);
        
        JLabel labelBirthday = new JLabel("Ngày Sinh");
        textBirthday = new JTextField();
        TextPrompt textPrompt = new TextPrompt("dd / mm / yyyy", textBirthday);
        
        leftPanel.add(customerId);
        leftPanel.add(idInputPanel);
        leftPanel.add(customerName);
        leftPanel.add(textCustomerName);
        leftPanel.add(labelBirthday);
        leftPanel.add(textBirthday);
        leftPanel.add(customerBirth);
        leftPanel.add(textCustomerPhone);
        leftPanel.add(typeCustomer);
        leftPanel.add(textCustomerSum);
        
        // Panel bên phải
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 hàng, 1 cột, khoảng cách 10px
        addBtn = new JButton("Thêm Khách Hàng");
        addBtn.addActionListener(e -> {
            String idCustomer = textLargeId.getText().trim();
            String nameCustomer = textCustomerName.getText().trim();
            LocalDate birthCustomer = AnalyzeDate.convertDayFomart(textBirthday.getText().trim());
            String type = "Thường";
            String phoneCustomer = textCustomerPhone.getText().trim();
            double totalMoney = 0;
            Customer cus = new Customer(idCustomer, nameCustomer, birthCustomer, type, phoneCustomer, totalMoney);
            if (tmpModel == null) {
                tmpModel = new TreeSet<>((o1, o2) -> o1.getIdCustomer().compareTo(o2.getIdCustomer()));
                System.out.println("tmp model null");
            }
            Set<Customer> res = new HashSet<>(model.getMainSystem().getCustomerManager().getListCustomer());
            tmpModel.addAll(res);
            tmpModel.add(cus);
            updateTMPTable(tmpModel);
        });
        deleteBtn = new JButton("Xóa Khách Hàng");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tmpModel == null) {
                    tmpModel = new TreeSet<>((o1, o2) -> o1.getIdCustomer().compareTo(o2.getIdCustomer()));
                    System.out.println("tmp model null");
                }
                Set<Customer> res = new HashSet<>(model.getMainSystem().getCustomerManager().getListCustomer());
                tmpModel.addAll(res);
                tmpModel.remove(model.findCustomer(textLargeId.getText(), textCustomerName.getText(), textCustomerPhone.getText()));
                updateTMPTable(tmpModel);
            }
        });
        rightPanel.add(addBtn);
        rightPanel.add(deleteBtn);
        
        // Thêm leftPanel và rightPanel vào informationPanel
        informationPanel.add(leftPanel, BorderLayout.CENTER);
        informationPanel.add(rightPanel, BorderLayout.EAST);
        
        return informationPanel;
    }
    
    int showConfirmDialog() {
        return JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn thực hiện hành động trên?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    }
    
    ;
    
    public JPanel moreButtonPlease(IModel model) {
        // Chức năng làm việc chung với table phía dưới
        JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        restartBtn = new JButton("Hoàn tác danh sách");
        restartBtn.setPreferredSize(new Dimension(150, 50));
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable(model);
                tmpModel = new TreeSet<>((o1, o2) -> o1.getIdCustomer().compareTo(o2.getIdCustomer()));
            }
        });
        resetBtn = new JButton("Xoá danh sách");
        resetBtn.addActionListener(e -> {
            int option = showConfirmDialog();
            if (option == JOptionPane.YES_OPTION) {
                model.resetListCustomer();
                updateTable(model);
            }
        });
        resetBtn.setPreferredSize(new Dimension(150, 50));
        checkBtn = new JButton("Xác nhận thay đổi");
        checkBtn.setPreferredSize(new Dimension(150, 50));
        checkBtn.addActionListener(e -> {
            int option = showConfirmDialog();
            if (option == JOptionPane.YES_OPTION) {
//                model.confirmUpdateCustomer(textLargeId.getText(), textCustomerName.getText(), textCustomerPhone.getText());
                model.confirmUpdateCustomer(new CustomerManager(tmpModel));
                updateTable(model);
                tmpModel = new TreeSet<>((o1, o2) -> o1.getIdCustomer().compareTo(o2.getIdCustomer()));
            }
        });
        findBtn = new JButton("Tìm kiếm");
        findBtn.setPreferredSize(new Dimension(150, 50));
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String largeId = textLargeId.getText().trim();
                boolean found = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String idInTable = tableModel.getValueAt(i, 1).toString();
                    if (idInTable.equals(largeId)) {
                        found = true;
                        textSmallId.setText(String.valueOf(i + 1));
                        Customer customer = model.findCustomer(idInTable, "", "");
                        if (customer != null) {
                            textCustomerName.setText(customer.getName());
                            textBirthday.setText(AnalyzeDate.dateToString(customer.getBirth()));
                            textCustomerSum.setText(String.valueOf(customer.getTotalMoney()));
                            textCustomerPhone.setText(customer.getPhone());
                        }
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    textSmallId.setText("-1");
                }
            }
        });
        buttonPanel.add(checkBtn);
        buttonPanel.add(findBtn);
        buttonPanel.add(restartBtn);
        buttonPanel.add(resetBtn);
        groupPanel.add(buttonPanel);
        return groupPanel;
    }
    
    
    public JPanel ListCustomerPanel(IModel model) {
        // Table khách hàng
        JPanel tablePanel = new JPanel(new BorderLayout());
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Bảng thông tin khách hàng");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20));
        tablePanel.setBorder(titledBorder);
        
        String[] columns = {"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "Loại", "Số điện thoại", "Tổng tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setEditingColumn(0);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        updateTable(model);
        return tablePanel;
    }
    
    public void updateTable(IModel model) {
        if (model.getMainSystem().getCustomerManager().getListCustomer() == null) {
            System.out.println("Không trả ra dữ liệu đầu vào đúng cách");
        } else {
            while (tableModel.getRowCount() > 0) tableModel.removeRow(0);
            for (Customer customer : model.getMainSystem().getCustomerManager().getListCustomer()) {
                String idCustomer = customer.getIdCustomer();
                String nameCustomer = customer.getName();
                String birthCustomer = String.valueOf(customer.getBirth());
                String type = customer.getType();
                String phoneCustomer = customer.getPhone();
                double totalMoney = customer.getTotalMoney();
                
                tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, idCustomer, nameCustomer, birthCustomer, type, phoneCustomer, totalMoney});
            }
            System.out.println("Update complete");
        }
    }
    
    public void updateTMPTable(Set<Customer> modelTMP) {
        while (tableModel.getRowCount() > 0) tableModel.removeRow(0);
        for (Customer customer : modelTMP) {
            String idCustomer = customer.getIdCustomer();
            String nameCustomer = customer.getName();
            String birthCustomer = String.valueOf(customer.getBirth());
            String type = customer.getType();
            String phoneCustomer = customer.getPhone();
            double totalMoney = customer.getTotalMoney();
            
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, idCustomer, nameCustomer, birthCustomer, type, phoneCustomer, totalMoney});
        }
        System.out.println("Update temporary");
    }
}

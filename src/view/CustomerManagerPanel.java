package view;

import model.IModel;
import model.MainSystem;
import model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerManagerPanel extends JPanel {
    JButton addBtn, deleteBtn;
    JTextField textSmallId, textLargeId, textCustomerName, textCustomerPhone, textCustomerSum;
    JButton restartBtn, resetBtn, checkBtn, findBtn;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane scrollPane;

    public CustomerManagerPanel(IModel model){
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
        JPanel leftPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 hàng, 2 cột, khoảng cách 5px
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

        leftPanel.add(customerId);
        leftPanel.add(idInputPanel);
        leftPanel.add(customerName);
        leftPanel.add(textCustomerName);
        leftPanel.add(customerBirth);
        leftPanel.add(textCustomerPhone);
        leftPanel.add(typeCustomer);
        leftPanel.add(textCustomerSum);

        // Panel bên phải
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // 2 hàng, 1 cột, khoảng cách 10px
        addBtn = new JButton("Thêm Khách Hàng");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addCustomer(null);
            }
        });
        deleteBtn = new JButton("Xóa Khách Hàng");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeCustomer(textLargeId.getText(), textCustomerName.getText(), textCustomerPhone.getText());
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
    };

    public JPanel moreButtonPlease(IModel model){
        // Chức năng làm việc chung với table phía dưới
        JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        restartBtn = new JButton("Cập nhật danh sách");
        restartBtn.setPreferredSize(new Dimension(150,50));
        restartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.updateListCustomer(null);
            }
        });
        resetBtn = new JButton("Xoá danh sách");
        resetBtn.addActionListener(e -> {
            int option = showConfirmDialog();
            if(option == JOptionPane.YES_OPTION) {
                model.resetListCustomer();
            }
        });
        resetBtn.setPreferredSize(new Dimension(150,50));
        checkBtn = new JButton("Xác nhận thay đổi");
        checkBtn.setPreferredSize(new Dimension(150,50));
        checkBtn.addActionListener(e -> {
            int option = showConfirmDialog();
            if(option == JOptionPane.YES_OPTION) {
                model.confirmUpdateCustomer(textLargeId.getText(), textCustomerName.getText(), textCustomerPhone.getText());
            }
        });
        findBtn = new JButton("Tìm kiếm");
        findBtn.setPreferredSize(new Dimension(150,50));
        findBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.findCustomer(textLargeId.getText(), textCustomerName.getText(), textCustomerPhone.getText());
            }
        });
        buttonPanel.add(checkBtn);
        buttonPanel.add(findBtn);
        buttonPanel.add(restartBtn);
        buttonPanel.add(resetBtn);
        groupPanel.add(buttonPanel);
        return groupPanel;
    }

    public JPanel ListCustomerPanel(IModel model){
            // Table khách hàng
            JPanel tablePanel = new JPanel(new BorderLayout());
            TitledBorder titledBorder = BorderFactory.createTitledBorder("Bảng thông tin khách hàng");
            titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 20));
            tablePanel.setBorder(titledBorder);
      
            String[] columns = {"STT", "Mã Khách Hàng", "Tên Khách Hàng", "Ngày Sinh", "Loại", "Số điện thoại", "Tổng tiền"};
            tableModel = new DefaultTableModel(columns, 0);
            table = new JTable(tableModel);
            table.getTableHeader().setReorderingAllowed(false);
            scrollPane = new JScrollPane(table);
            tablePanel.add(scrollPane);

            return tablePanel;
        }
}

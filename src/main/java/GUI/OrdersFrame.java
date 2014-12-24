package GUI;

import DAO.DAO;
import models.ClientsEntity;
import models.OrdersEntity;
import models.ToursEntity;
import models.WorkersEntity;
import tour_firm.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Created by yaroslav on 16.11.2014.
 */
public class OrdersFrame extends JFrame {
    private JPanel panel;
    private JTable table;
    private JButton enterBtn;
    private JButton backBtn;
    private DAO dao = new DAO();
    private List<Object> objsList;
    private ClientsEntity clientsEntity;
    private WorkersEntity workersEntity;
    private ToursEntity toursEntity;


    public OrdersFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        backBtn = new JButton("Ok");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel = new JPanel(new BorderLayout());
        JPanel tablePanel = new JPanel();
        JPanel btnPanel = new JPanel();
        tablePanel.add(makeTable());
        btnPanel.add(backBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);
        panel.add(tablePanel, BorderLayout.NORTH);
        add(panel);
        panel.setOpaque(true);
        setContentPane(panel);
        pack();
    }

    private JScrollPane makeTable() {

        String[] columns = {"Iмя","Прізвище", "емайл", "Прізв. працівника", "Країна",
                "Місто", "Готель", "Ціна"};
        table = new JTable(new MyTableModel(listOfOrderObjs(), columns));
        table.setPreferredScrollableViewportSize(new Dimension(600, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    private java.util.List listOfOrderObjs(){
        List<Object[]> returnList = new ArrayList<>();
        objsList = new ArrayList<>();
        objsList = dao.getListEntity("from OrdersEntity");

        for (int i = 0; i < objsList.size() ; i++) {
            OrdersEntity order = (OrdersEntity) objsList.get(i);
            List<Object> orderElems = new ArrayList<>();
            orderElems.add(order.getClientsByClientId().getFirstName());
            orderElems.add(order.getClientsByClientId().getLastName());
            orderElems.add(order.getClientsByClientId().getEmail());
            orderElems.add(order.getWorkersByWorkerId().getLastName());
            orderElems.add(order.getToursByTourId().getHotelsByHotelId().getCitiesByCityId().getCountriesByCountryId().getCountry());
            orderElems.add(order.getToursByTourId().getHotelsByHotelId().getCitiesByCityId().getCity());
            orderElems.add(order.getToursByTourId().getHotelsByHotelId().getHotel());
            orderElems.add(order.getToursByTourId().getPrice());
            returnList.add(orderElems.toArray());
        }

        return  returnList;
    }
}

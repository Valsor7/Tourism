package GUI;

import DAO.DAO;
import models.ToursEntity;
import tour_firm.MyTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by yaroslav on 13.11.2014.
 */
public class ToursFrame extends JFrame {

    private JPanel panel;
    private JTable toursTable;
    private JButton enterBtn;
    private JButton backBtn;
    private DAO dao = new DAO();
    private List<Object> objsList;
    private List<Object[]> returnList;
    private static int ROW_INDEX;
    private static int COLUMN_INDEX = 0;
    private static int ID;
    private List<Object> toursFromSearch = new ArrayList<>();


    public ToursFrame(List<Object> toursObjs){
        toursFromSearch = toursObjs;

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        add(panel);
        pack();
    }
    public ToursFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        add(panel);
        pack();
    }

    private void initComponents(){
        enterBtn = new JButton("Замовити");
        backBtn = new JButton("Назад");
        enterBtn.setEnabled(false);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientFrame();
                dispose();
            }
        });
        panel = new JPanel(new BorderLayout());
        JPanel tablePanel = new JPanel();
        JPanel btnPanel = new JPanel();
        tablePanel.add(makeTable());
        btnPanel.add(backBtn);
        btnPanel.add(enterBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);
        panel.add(tablePanel, BorderLayout.NORTH);
    }

    private JScrollPane makeTable() {
        String[] columns = {"id туру","Готель", "К-сть осіб", "Транспорт", "Країна",
                            "Дата_відправлення", "Дата_прибуття", "Тривалість", "Ціна"};
        toursTable = new JTable(new MyTableModel(listOfTourObjs(), columns));
        toursTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        toursTable.setFillsViewportHeight(true);
        toursTable.getSelectionModel().addListSelectionListener(new RowSelectListener());
        JScrollPane scrollPane = new JScrollPane(toursTable);
        return scrollPane;
    }

    private java.util.List listOfTourObjs(){
        List<Object[]> returnList = new ArrayList<>();
        objsList = new ArrayList<>();
        if (toursFromSearch.size() == 0) {
            objsList = dao.getListEntity("from ToursEntity");
        } else {
            objsList = toursFromSearch;
        }

        for (int i = 0; i < objsList.size() ; i++) {
            ToursEntity toursEntity = (ToursEntity) objsList.get(i);
            List<Object> tourElems = new ArrayList<>();
            tourElems.add(toursEntity.getId());
            tourElems.add(toursEntity.getHotelsByHotelId().getHotel());
            tourElems.add(toursEntity.getHotelsByHotelId().getRooms());
            tourElems.add(toursEntity.getTransportByTransportId().getTransportType());
            tourElems.add(toursEntity.getHotelsByHotelId().getCitiesByCityId().getCountriesByCountryId().getCountry());
            tourElems.add(toursEntity.getDeparting());
            tourElems.add(toursEntity.getReturning());
            tourElems.add(toursEntity.getLength());
            tourElems.add(toursEntity.getPrice());
            returnList.add(tourElems.toArray());
        }

        return  returnList;
    }
    public static int getId(){
        return ID;
    }

    private class RowSelectListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()){
                return;
            }
            enterBtn.setEnabled(true);
            ROW_INDEX = toursTable.getSelectionModel().getLeadSelectionIndex();
            ID = (int) toursTable.getValueAt(ROW_INDEX,COLUMN_INDEX);
            System.out.println(ID);

        }
    }
}

package GUI;

import DAO.DAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by yaroslav on 15.11.2014.
 */
public class SearchFrame extends javax.swing.JFrame {

    private JButton searchBtn;
    private JPanel searchPanel;
    private JTextField priceFrom;
    private JTextField priceTo;
    private int[] starsArray = {1,2,3,4,5};
    private String[] boardArray = {"bb","al","ro","hb","ob","fb"};
    private DAO dao = new DAO();
    private ArrayList<JComboBox> boxList;
    private JComboBox countryBox;
    private JComboBox cityBox;
    private JComboBox starsBox;
    private JComboBox boardBox;


    public SearchFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(8, 2));
        initComponents();
        add(searchPanel);
        pack();
    }

    private void initComponents() {
        boxList = new ArrayList<>();
        String[] labelTexts = {"Країна","Місто", "Клас готелю", "Тип харчування","Ціна від","до"};

        List<JLabel> lsList = new ArrayList<>();
        int i =0;

        boxList.add(countryBox = new JComboBox(setDataInCountryBox()));
        boxList.add(cityBox = new JComboBox());
        boxList.add(starsBox = new JComboBox());
        boxList.add(boardBox = new JComboBox());
        countryBox.addActionListener(boxesAction);
        cityBox.addActionListener(boxesAction);
        starsBox.addActionListener(boxesAction);
        boardBox.addActionListener(boxesAction);

        for (int k = 1; k <boxList.size() ; k++) {
            boxList.get(k).setEnabled(false);
        }

        for (String s : labelTexts) {
           lsList.add(new JLabel(s));
        }
        for (int j = 0; j < labelTexts.length-2; j++) {
            searchPanel.add(lsList.get(j));
            searchPanel.add(boxList.get(j));
        }

        priceFrom = new JTextField();
        priceFrom.setText("1");
        priceTo = new JTextField();
        priceTo.setText("100000");
        searchBtn = new JButton("Пошук");
        searchBtn.addActionListener(searchAction);
        searchPanel.add(new JLabel(labelTexts[4]));
        searchPanel.add(priceFrom);
        searchPanel.add(new JLabel(labelTexts[5]));
        searchPanel.add(priceTo);
        searchPanel.add(new JLabel());
        searchPanel.add(new JLabel());
        searchPanel.add(searchBtn);
    }

    private Object[] setDataInCountryBox() {
        Object[] objs = dao.getListEntity("select c.country from CountriesEntity c").toArray();
        return objs;
    }

    private Object[] setDataInCityBox() {
        Object[] objs = dao.getCity(countryBox.getSelectedItem()).toArray();
        return objs;
    }

    ActionListener boxesAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == countryBox){
                cityBox.setEnabled(true);
                cityBox.removeAllItems();
                for (Object o : setDataInCityBox()){
                    cityBox.addItem(o);
                }
            }
            if (e.getSource() == cityBox){
                starsBox.setEnabled(true);
                starsBox.removeAllItems();
                for (int i = 1; i < 6; i++) {
                    starsBox.addItem(i);
                }
            }
            if (e.getSource() == cityBox){
                boardBox.setEnabled(true);
                boardBox.removeAllItems();
                for (int i = 0; i < 6; i++) {
                    boardBox.addItem(boardArray[i]);
                }
            }

        }
    };

    ActionListener searchAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Object> paramsList = new ArrayList<>();
            for (JComboBox box : boxList) {
                if (box.getSelectedItem() != null) {
                    paramsList.add(box.getSelectedItem());
                } else {
                    paramsList.add(0);
                }
            }

            Integer[] priceFromTo = {Integer.valueOf(priceFrom.getText()), Integer.valueOf(priceTo.getText())};

            List<Object> localList  = dao.criteria(paramsList, priceFromTo);
            if (localList.size() == 0){
                new JOptionPane().showMessageDialog(null, "Не знайдено жодного туру за даним запитом", "", JOptionPane.INFORMATION_MESSAGE);
            }else {

                new ToursFrame(localList);
            }
        }
    };


}

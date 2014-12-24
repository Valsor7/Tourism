package GUI;

import DAO.DAO;
import models.ClientsEntity;
import models.OrdersEntity;
import models.ToursEntity;
import models.WorkersEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslav on 16.11.2014.
 */
public class OrderFrame extends JFrame {
    private JPanel informPanel;
    private JPanel panel;
    private JButton backBtn;
    private DAO dao = new DAO();
    private ClientsEntity clientsEntity;
    private WorkersEntity workersEntity;
    private ToursEntity toursEntity;
    private List<JLabel> lsList = new ArrayList<>();
    private List<JLabel> dataLablsList = new ArrayList<>();

     public OrderFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dao.setOrder(setOrderInform(getIds()));
        panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("~ІНФОРМАЦІЯ ПРО ЗАМОВЛЕННЯ~"), BorderLayout.NORTH);
        backBtn = new JButton("Ok");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        informPanel = new JPanel(new GridLayout(13,2));
        makeOrder();
        informPanel.add(backBtn);
        panel.add(informPanel);
        panel.setOpaque(true);
        setContentPane(panel);
        pack();
    }

    private int[] getIds(){
        int[] ids = new int[3];

        toursEntity = (ToursEntity) dao.getEntityById("from ToursEntity where id= :id", ToursFrame.getId());
        workersEntity = (WorkersEntity) dao.getEntityById("from WorkersEntity where id= :id", WorkerFrame.getId());
        clientsEntity= dao.getLastClient();

        ids[0] = toursEntity.getId();
        ids[1] = clientsEntity.getId();
        ids[2] = workersEntity.getId();

        return ids;
    }

    private OrdersEntity setOrderInform(int[] ids){
        OrdersEntity ordersEntity = new OrdersEntity();

        ordersEntity.setTourId(ids[0]);
        ordersEntity.setClientId(ids[1]);
        ordersEntity.setWorkerId(ids[2]);
        ordersEntity.setDate(getDateTime());
        ordersEntity.setClientsByClientId(clientsEntity);
        ordersEntity.setToursByTourId(toursEntity);
        ordersEntity.setWorkersByWorkerId(workersEntity);
        return ordersEntity;
    }
    private Date getDateTime() {
        java.util.Date date = new java.util.Date();
        long currentDate = date.getTime();
        return new Date(currentDate);
    }

    private void makeOrder() {

        String[] titlesList = {"Iмя :","Прізвище :", "емайл :", "Прізв. працівника :", "Країна :",
                "Місто :", "Готель :", "Клас готелю :", "Транспорт :", "Дата відправки :", "Дата прибуття :", "Ціна :"};


        for (String s : titlesList) {
            lsList.add(new JLabel(s));
        }

        for (Object s : listOfOrderObjs()){
            dataLablsList.add(new JLabel(s.toString()));
        }

        for (int j = 0; j < titlesList.length; j++) {
            informPanel.add(lsList.get(j));
            informPanel.add(dataLablsList.get(j));
        }

    }

    private Object[] listOfOrderObjs(){

        OrdersEntity order = dao.getLastOrder();
        List<Object> orderElems = new ArrayList<>();
        orderElems.add(order.getClientsByClientId().getFirstName());
        orderElems.add(order.getClientsByClientId().getLastName());
        orderElems.add(order.getClientsByClientId().getEmail());
        orderElems.add(order.getWorkersByWorkerId().getLastName());
        orderElems.add(order.getToursByTourId().getHotelsByHotelId().getCitiesByCityId().getCountriesByCountryId().getCountry());
        orderElems.add(order.getToursByTourId().getHotelsByHotelId().getCitiesByCityId().getCity());
        orderElems.add(order.getToursByTourId().getHotelsByHotelId().getHotel());
        orderElems.add(order.getToursByTourId().getHotelsByHotelId().getStars());
        orderElems.add(order.getToursByTourId().getTransportByTransportId().getTransportType());
        orderElems.add(order.getToursByTourId().getDeparting());
        orderElems.add(order.getToursByTourId().getReturning());
        orderElems.add(order.getToursByTourId().getPrice());

        return  orderElems.toArray();
    }
}

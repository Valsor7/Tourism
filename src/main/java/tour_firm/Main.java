package tour_firm;

import DAO.DAO;
import GUI.Frame;
import GUI.SearchFrame;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String args[]) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Frame();
            }
        }).start();
        try {
            HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by yaroslav on 12.11.204.
 */
public class Frame  extends JFrame {


    public Frame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
       // setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new ImagePanel(new FlowLayout(FlowLayout.TRAILING));
        panel.add(new BtnPanel());
        add(panel);
        setSize(620, 336);


    }

    private class BtnPanel extends JPanel {

        JButton btnAllTour = new JButton("tours");
        JButton btnCountry = new JButton("search");
        JButton btnOrders = new JButton("orders");

        public BtnPanel() {
            setLayout(new GridLayout(1, 1));
            setOpaque(true);
            add(btnAllTour);
            add(btnCountry);
            add(btnOrders);
            btnAllTour.addActionListener(myAction);
            btnCountry.addActionListener(myAction);
            btnOrders.addActionListener(myAction);
        }


        ActionListener myAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAllTour){
                    new ToursFrame();
                }
                if (e.getSource() == btnCountry){
                    new SearchFrame();
                }
                if (e.getSource() == btnOrders){
                    new OrdersFrame();
                }
            }
        };
    }


}
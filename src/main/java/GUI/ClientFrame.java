package GUI;

import DAO.DAO;
import models.ClientsEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yaroslav on 16.11.2014.
 */
public class ClientFrame extends JFrame {

    public ClientFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ClientForm());
        pack();


    }
    private class ClientForm extends JPanel {

        public ClientForm() {
            initComponents();
            setLayout(new GridLayout(7,2));
            add(jLabel1);
            add(nameField);
            add(jLabel2);
            add(lastNameField);
            add(jLabel3);
            add(emailField);
            add(jLabel4);
            add(phoneField);
            add(jLabel5);
            add(adressField);
            add(new JLabel());
            add(new JLabel());


            backBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            enterBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new WorkerFrame();
                    dispose();
                    new DAO().setClient(getClientInform());
                }
            });
            add(backBtn);
            add(enterBtn);

        }


        private ClientsEntity getClientInform(){
            ClientsEntity clientsEntity = new ClientsEntity();
            clientsEntity.setFirstName(nameField.getText());
            clientsEntity.setLastName(lastNameField.getText());
            clientsEntity.setEmail(emailField.getText());
            clientsEntity.setPhone(Long.parseLong(phoneField.getText()));
            clientsEntity.setAdress(adressField.getText());

            return clientsEntity;
        }

        private void initComponents() {

            jLabel1 = new javax.swing.JLabel();
            nameField = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            lastNameField = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            emailField = new javax.swing.JTextField();
            phoneField = new javax.swing.JTextField();
            adressField = new javax.swing.JTextField();
            enterBtn = new JButton("Далі");
            backBtn = new JButton("Назад");

            jLabel1.setText("Імя");
            jLabel2.setText("Прізвище");
            jLabel3.setText("емайл");
            jLabel4.setText("телефон");
            jLabel5.setText("адреса");

        }

        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JButton backBtn;
        private javax.swing.JButton enterBtn;
        private javax.swing.JTextField nameField;
        private javax.swing.JTextField lastNameField;
        private javax.swing.JTextField emailField;
        private javax.swing.JTextField phoneField;
        private javax.swing.JTextField adressField;
    }
}

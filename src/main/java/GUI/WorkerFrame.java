package GUI;

import DAO.DAO;
import models.WorkersEntity;
import tour_firm.MyTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslav on 16.11.2014.
 */
public class WorkerFrame extends JFrame {


    private JPanel panel;
    private JTable workersTable;
    private JButton enterBtn;
    private JButton backBtn;
    private DAO dao = new DAO();
    private List<Object> objsList;
    private static int ROW_INDEX;
    private static int COLUMN_INDEX = 0;
    private static int ID;


    public WorkerFrame() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(size.width / 4, size.height / 4);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        enterBtn = new JButton("Далі");
        enterBtn.setEnabled(false);
        backBtn = new JButton("Відмінити");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderFrame();
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
        add(panel);
        panel.setOpaque(true);
        setContentPane(panel);
        pack();
    }

    private JScrollPane makeTable() {
        String[] columns = {"id працівника","Імя", "Прізвище", "Посада", "Телефон",
                "досвід"};
        workersTable = new JTable(new MyTableModel(listOfWorkersObjs(), columns));
        workersTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        workersTable.setFillsViewportHeight(true);
        workersTable.getSelectionModel().addListSelectionListener(new RowSelectListener());
        JScrollPane scrollPane = new JScrollPane(workersTable);
        return scrollPane;
    }

    private java.util.List listOfWorkersObjs(){
        List<Object[]> returnList = new ArrayList<>();
        objsList = new ArrayList<>();
        objsList = dao.getListEntity("from WorkersEntity");

        for (int i = 0; i < objsList.size() ; i++) {
            WorkersEntity worker = (WorkersEntity) objsList.get(i);
            List<Object> workerElems = new ArrayList<>();
            workerElems.add(worker.getId());
            workerElems.add(worker.getFirstName());
            workerElems.add(worker.getLastName());
            workerElems.add(worker.getWorkPost());
            workerElems.add(worker.getPhone());
            workerElems.add(worker.getExperience());
            returnList.add(workerElems.toArray());
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
            ROW_INDEX = workersTable.getSelectionModel().getLeadSelectionIndex();
            ID = (int) workersTable.getValueAt(ROW_INDEX,COLUMN_INDEX);
            System.out.println(ID);
        }
    }
}



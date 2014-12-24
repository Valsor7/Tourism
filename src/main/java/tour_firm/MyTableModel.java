package tour_firm;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by yaroslav on 14.11.2014.
 */
public class MyTableModel extends AbstractTableModel {

    List<Object[]> objsList = null;
    String[] cols_name;

    public MyTableModel(List<Object[]> list, String[] columns){
        objsList = list;
        cols_name = columns;

    }
    @Override
    public int getRowCount() {
        return objsList.size();
    }

    @Override
    public int getColumnCount() {
        return cols_name.length;
    }

    @Override
    public String getColumnName(int column) {
        try
        {
            return cols_name[column];
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] objs = objsList.get(rowIndex);
        return objs[columnIndex];
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}

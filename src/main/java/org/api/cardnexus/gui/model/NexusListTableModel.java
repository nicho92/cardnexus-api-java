package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.NexusList;
import org.api.cardnexus.model.enums.EnumStatus;

public class NexusListTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"id","game","name","itemCount","total","completion","public","status"};
    private transient List<NexusList> items;
    
    public NexusListTableModel() {
	items = new ArrayList<>();
    }
    
    public void init(List<NexusList> items)
    {
	this.items=items;
	fireTableDataChanged();
    }
    

    public void addItem(NexusList l) {
	items.add(l);
	fireTableDataChanged();
	
    }
    
    
    @Override
    public void removeRow(int row) {
        items.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public int getRowCount() {
       
	if(items==null)
	    return 0;
	
	return items.size();
    }
    
    
    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.id();
		case 1 : return l.game();
		case 2: return l.name();
		case 3 : return l.itemCount();
		case 4: return l.totalQuantity();
		case 5 : return l.completionPercentage();
		case 6 : return l.isPublic();
		case 7: return l.status();
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 3,4: return Integer.class;
        	case 5: return Double.class;
        	case 6: return Boolean.class;
        	case 7: return EnumStatus.class;
        	default: return super.getColumnClass(c);
        }
    }

    

}

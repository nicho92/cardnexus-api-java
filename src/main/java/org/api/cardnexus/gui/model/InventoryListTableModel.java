package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class InventoryListTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"id","quantity","condition","finish","lang","location","tags","comment","price","update","customId"};
    private transient List<InventoryLine> items;
    
    public InventoryListTableModel() {
	items = new ArrayList<>();
    }
    
    public void init(List<InventoryLine> items)
    {
	this.items=items;
	fireTableDataChanged();
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.id();
		case 1: return l.quantity();
		case 2: return l.condition();
		case 3 : return l.finish();
		case 4 : return l.language();
		case 5 : return l.location();
		case 6 : return l.tags();
		case 7: return l.comment();
		case 8 : return l.price();
		case 9 : return l.updatedAt();
		case 10: return l.customId();
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 2 : return EnumCondition.class;
        	case 3 : return EnumFinishes.class;
        	case 6: return List.class;
        	case 8: return Amount.class;
        	case 9: return Date.class;
        	default: return super.getColumnClass(c);
        }
    }


    public void addItem(InventoryLine l) {
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
    
    
    

}

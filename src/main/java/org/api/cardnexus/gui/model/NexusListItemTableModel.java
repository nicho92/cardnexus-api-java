package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.ListItem;
import org.api.cardnexus.model.enums.EnumCondition;

public class NexusListItemTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"id","name","expansion","quantity","fullFilled","sellPrice","wantPrice","minCondition","language","idProduct"};
    private transient List<ListItem> items;
    
    public NexusListItemTableModel() {
	items = new ArrayList<>();
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.id();
		case 1 : return l.name();
		case 2 : return l.expansion();
		case 3 : return l.quantity();
		case 4 : return l.quantityFulfilled();
		case 5 : return l.sellPrice();
		case 6 : return l.wantPrice();
		case 7 : return l.minCondition();
		case 8 : return l.language();
		case 9 : return l.productId();
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 3,4,9 : return Integer.class;
        	case 5,6 : return Double.class;
        	case 7 : return EnumCondition.class;
        	default: return super.getColumnClass(c);
        }
    }
    
    public void init(List<ListItem> items)
    {
	this.items=items;
	fireTableDataChanged();
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
    
  
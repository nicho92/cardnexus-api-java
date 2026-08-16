package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.Sales;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class SalesTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"date","region","condition","finish","quantity","price"};
    private transient List<Sales> items;
    
    public SalesTableModel() {
	items = new ArrayList<>();
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var s = items.get(row);
        
        switch (column) {
		case 0: return s.soldAt();
		case 1 : return s.region();
		case 2 : return s.condition();
		case 3 : return s.finish();
		case 4 : return s.quantity();
		case 5 : return s.price();
		
		
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 0 : return Date.class;
        	case 2 : return EnumCondition.class;
        	case 3 : return EnumFinishes.class;
        	case 5 : return Amount.class;
        	default: return super.getColumnClass(c);
        }
    }
    
    public void init(List<Sales> items)
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
    
  
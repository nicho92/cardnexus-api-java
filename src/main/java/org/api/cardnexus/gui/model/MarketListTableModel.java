package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.MarketList;
import org.api.cardnexus.model.Seller;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class MarketListTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"id","seller","quantity","language","condition","finish","price","shipping"};
    private transient List<MarketList> items;
    
    public MarketListTableModel() {
	items = new ArrayList<>();
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.listingId();
		case 1: return l.seller();
		case 2 : return l.quantity();
		case 3 : return l.language();
		case 4 : return l.condition();
		case 5 : return l.finish();
		case 6 : return l.price();
		case 7 : return l.seller().shipping();
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 1 : return Seller.class;
        	case 2 : return Integer.class;
        	case 4 : return EnumCondition.class;
        	case 5 : return EnumFinishes.class;
        	case 6,7 : return Amount.class;
        	default: return super.getColumnClass(c);
        }
    }
    
    public void init(List<MarketList> items)
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
    
  
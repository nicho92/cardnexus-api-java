package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.enums.EnumRarity;

public class NexusProductTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"id","name","expansion","type","in stock","rarity"};
    private transient List<AbstractProduct> items;
    
    public NexusProductTableModel() {
	items = new ArrayList<>();
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.getId();
		case 1 : return l.getName();
		case 2 : return l.getExpansion();
		case 3 : return l.getProductType();
		case 4 : return l.hasStock();
		case 5 : {
		    		if(l instanceof CardProduct c)
		    		    return c.getRarity();
		    		
		    		return null;
		}
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 0: return Integer.class;
        	case 2 : return Expansion.class;
        	case 3 : return EnumProductType.class;
        	case 4 : return Boolean.class;
        	case 5 : return EnumRarity.class;
        	default: return super.getColumnClass(c);
        }
    }
    
    public void init(List<AbstractProduct> items)
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
    
  
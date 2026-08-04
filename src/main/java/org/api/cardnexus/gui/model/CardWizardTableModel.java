package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.ProductEntry;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class CardWizardTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"id","finish","language","condition","quantity","maxPrice"};
    private transient List<ProductEntry> items;
    
    
    public List<ProductEntry> getItems() {
	return items;
    }
    
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        	switch (column) {
        		case 1 :  items.get(row).setFinish(EnumFinishes.valueOf(aValue.toString()));return;
        		case 2 :  items.get(row).setLanguages(List.of(aValue.toString()));return;
        		case 3 :  items.get(row).setMinCondition(EnumCondition.valueOf(aValue.toString()));return;
        		case 4 :  items.get(row).setQuantity(Integer.parseInt(aValue.toString()));return;
        		case 5 :  items.get(row).setMaxUnitPrice(new Amount(Double.parseDouble(aValue.toString()), "EUR"));return;
		}
    }

    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.productId();
		case 1 : return l.finish();
		case 2 : return l.languages().get(0);
		case 3 : return l.minCondition();
		case 4 : return l.quantity();
		case 5 : return l.maxUnitPrice();
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
        switch (c)
        {
        	case 0,4 : return Integer.class;
        	case 1: return EnumFinishes.class;
        	case 3: return EnumCondition.class;
        	case 5: return Amount.class;
        	
        	default: return super.getColumnClass(c);
        }
    }
    
    
    public CardWizardTableModel() {
	items = new ArrayList<>();
    }
    
    public void init(List<ProductEntry> items)
    {
	this.items=items;
	fireTableDataChanged();
    }
    

    public void addItem(ProductEntry l) {
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
       return column>0;
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

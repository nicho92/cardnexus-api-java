package org.api.cardnexus.gui.model;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.CardAttributs;

public class CardAttributsTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"Property","Value"};
    private transient CardAttributs items;
    
    
    public CardAttributs getItems() {
    	return items;
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var e = items.getEntryAt(row);
        
        switch (column) {
			case 0: return e.getKey();
			case 1 : return e.getValue();
			default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
    	
    	if(c==0)
    		return String.class;
    		
        return Object.class;
        
    }
    
    
    public CardAttributsTableModel() {
    	items = new CardAttributs();
    }
    
    public void init(CardAttributs items)
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

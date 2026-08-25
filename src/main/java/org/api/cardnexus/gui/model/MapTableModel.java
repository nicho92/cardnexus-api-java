package org.api.cardnexus.gui.model;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class MapTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"Property","Value"};
    private transient Map<String,Object> items;
    
    
    public Map<String,Object> getItems() {
	return items;
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var k = items.keySet().toArray()[row];
        var v = items.get(k);
        
        switch (column) {
		case 0: return k;
		case 1 : return v;
		
		default: return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int c) {
    	
    	if(c==0)
    		return String.class;
    		
        return super.getColumnClass(c);
        
    }
    
    
    public MapTableModel() {
    	items = new HashMap<>();
    }
    
    public void init(Map<String,Object> items)
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

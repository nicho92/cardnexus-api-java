package org.api.cardnexus.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.api.cardnexus.model.IconableItem;

public class TagLocationTableModel extends DefaultTableModel {

    private static final long serialVersionUID = 1L;
    
    private String[] columns = new String[] {"name","color","icon"};
    private transient List<IconableItem> items;
    
    public TagLocationTableModel() {
	items = new ArrayList<>();
    }
    

    @Override
    public Object getValueAt(int row, int column) {
        var l = items.get(row);
        
        switch (column) {
		case 0: return l.name();
		case 1 : return l.color();
		case 2 : return l.icon();
		default: return null;
        }
    }
    

    @Override
    public void removeRow(int row) {
        items.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    
    
    
    @Override
    public Class<?> getColumnClass(int c) {
        return String.class;
    }
    
    public void init(List<IconableItem> items)
    {
	this.items=items;
	fireTableDataChanged();
    }
    

    public void addItem(IconableItem l) {
	items.add(l);
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
    
  
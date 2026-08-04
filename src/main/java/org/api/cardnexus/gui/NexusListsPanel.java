package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.components.ProductPanel;
import org.api.cardnexus.gui.model.NexusListItemTableModel;
import org.api.cardnexus.gui.model.NexusListTableModel;
import org.api.cardnexus.model.enums.EnumStatus;
import org.api.cardnexus.model.requests.ListCreationRequest;
import org.api.cardnexus.services.ListsServices;
import org.api.cardnexus.services.ProductsService;

public class NexusListsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private NexusListTableModel modelLists;
    private NexusListItemTableModel modelItems;
    private ProductPanel productPanel;
    
    private ListsServices servicesList;
    private ProductsService pservice;
    
    protected transient Logger logger = LogManager.getLogger(getClass());
    
    public NexusListsPanel() {
    	setLayout(new BorderLayout());
    	
    	modelLists = new NexusListTableModel();
    	modelItems = new NexusListItemTableModel();
    	
    	productPanel = new ProductPanel();
    	var table = new JTable(modelLists);
    	var tableItems = new JTable(modelItems);
    	var panel = new JPanel();
    	var btnAddList = new JButton("New");
    	var btnDelete = new JButton("Delete");
    	var btnRefresh= new JButton("Reload");
    	var splitPane = new JPanel();
    	
    	
    	btnDelete.setEnabled(false);
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	splitPane.setLayout(new BorderLayout());
    	
    	add(new JScrollPane(table), BorderLayout.WEST);
    	add(panel, BorderLayout.NORTH);
    	panel.add(btnAddList);
    	panel.add(btnDelete);
    	panel.add(btnRefresh);
    	splitPane.add(new JScrollPane(tableItems),BorderLayout.CENTER);
    	splitPane.add(productPanel,BorderLayout.SOUTH);
    	add(splitPane, BorderLayout.CENTER);
    	
    	
    	pservice = new ProductsService();
    	servicesList = new ListsServices();
    	
    
    	btnRefresh.doClick();
    	
    	
    	
    
    btnRefresh.addActionListener(_->{
            	try {
        	    modelLists.init(servicesList.listNexusLists());
        	} catch (Exception e) {
        	    logger.error(e);
        	}
    	});
    	
    btnAddList.addActionListener(_->{
	var name = JOptionPane.showInputDialog("List Name ?");
	try {
	  var  l = servicesList.createList(ListCreationRequest.create().setName(name).setPublic(false).setStatus(EnumStatus.hold));
		modelLists.addItem(l);
	} catch (IOException e) {
	    logger.error(e);
	}

    });
    	
    
    btnDelete.addActionListener(_->{
	
	int row = table.convertRowIndexToModel(table.getSelectedRow());
	var id = modelLists.getValueAt(row, 0).toString();
	var confirmation = JOptionPane.showConfirmDialog(this, "Delete " + id + " ?");
	
	if(confirmation==JOptionPane.YES_OPTION) {
           	try {
           	  servicesList.deleteList(id);
           	  modelLists.removeRow(row);
           	} catch (IOException e) {
           	 logger.error(e);
           	}
	}
       });
    
    
    	
    tableItems.getSelectionModel().addListSelectionListener(e -> {
    	    if (!e.getValueIsAdjusting()) {
    		int row = tableItems.convertRowIndexToModel(tableItems.getSelectedRow());
    	       
    		 if(row>-1)
    		 {     
    		     var id = (Integer)modelItems.getValueAt(row, 9);
        	            try {
    			productPanel.init(pservice.getProductById(id));
    		    } catch (Exception e1) {
    			logger.error(e1);
    		    }
    		 }
    	    }
    	});
    	
    	
    	table.getSelectionModel().addListSelectionListener(e -> {
    	    if (!e.getValueIsAdjusting()) {
    		int row = table.convertRowIndexToModel(table.getSelectedRow());
    	            btnDelete.setEnabled(row != -1);
    	            
    	            if(row>-1)
    	            {
    	        	    var id = modelLists.getValueAt(row, 0).toString();
            	            try {
        			modelItems.init(servicesList.getNexusLists(id).items());
        		    } catch (IOException e1) {
        			logger.error(e1);
        		    }
    	            }
    	    }
    	});

    }

}

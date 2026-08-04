package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.model.NexusListItemTableModel;
import org.api.cardnexus.gui.model.NexusListTableModel;
import org.api.cardnexus.model.enums.EnumStatus;
import org.api.cardnexus.model.requests.ListCreationRequest;
import org.api.cardnexus.services.ListsServices;
import org.api.cardnexus.services.ProductsService;

public class ListManagementPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private NexusListTableModel modelLists;
    private NexusListItemTableModel modelItems;
    private ProductPanel productPanel;
    
    private ListsServices servicesList;
    private ProductsService pservice;
    
    protected Logger logger = LogManager.getLogger(getClass());
    
    
    public static void main(String[] args) throws IOException {
	
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.setDefaultGameValue("mtg");
	
	var f = new JFrame();
	
	f.getContentPane().add(new ListManagementPanel());
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	f.setLocationRelativeTo(null);
	f.setVisible(true);
	
    }

    public ListManagementPanel() {
    	setLayout(new BorderLayout(0, 0));
    	
    	modelLists = new NexusListTableModel();
    	modelItems = new NexusListItemTableModel();
    	
    	productPanel = new ProductPanel();
    	var table = new JTable(modelLists);
    	var tableItems = new JTable(modelItems);
    	JPanel panel = new JPanel();
    	JButton btnAddList = new JButton("New");
    	JButton btnDelete = new JButton("Delete");
    	JButton btnUpdate = new JButton("Update");
    	JPanel splitPane = new JPanel();
    	
    	
    	btnUpdate.setEnabled(false);
    	btnDelete.setEnabled(false);
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	tableItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	splitPane.setLayout(new BorderLayout());
    	
    	add(new JScrollPane(table), BorderLayout.WEST);
    	add(panel, BorderLayout.NORTH);
    	panel.add(btnAddList);
    	panel.add(btnUpdate);
    	panel.add(btnDelete);
    	splitPane.add(new JScrollPane(tableItems),BorderLayout.CENTER);
    	splitPane.add(productPanel,BorderLayout.SOUTH);
    	add(splitPane, BorderLayout.CENTER);
    	
    	
    	pservice = new ProductsService();
    	servicesList = new ListsServices();
    	
    	try {
	    modelLists.init(servicesList.listNexusLists());
	} catch (Exception e) {
	    logger.error(e);
	}
    	
    	
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
    	        var id = (Integer)modelItems.getValueAt(row, 9);
    	            try {
			productPanel.init(pservice.getProductById(id));
		    } catch (Exception e1) {
			logger.error(e);
		    }
    	        
    	    }
    	});
    	
    	
    	table.getSelectionModel().addListSelectionListener(e -> {
    	    if (!e.getValueIsAdjusting()) {
    		int row = table.convertRowIndexToModel(table.getSelectedRow());
    	            btnDelete.setEnabled(row != -1);
    	            btnUpdate.setEnabled(row != -1);
    	            
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

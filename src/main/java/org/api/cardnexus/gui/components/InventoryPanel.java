package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.model.InventoryListTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.requests.InventoryLinesRequest;
import org.api.cardnexus.services.InventoryService;

public class InventoryPanel extends JPanel 
{
 
    private static final long serialVersionUID = 1L;
    private transient InventoryService service;
    private InventoryListTableModel model;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private InventoryLineCreationPanel createPanel;
    private JButton btnSave;
    
    public InventoryPanel() {
	setLayout(new BorderLayout());
	
	service = new InventoryService();
	
	model = new InventoryListTableModel();
	var table = new JTable(model);
	createPanel = new InventoryLineCreationPanel(false);
	add(new JScrollPane(table),BorderLayout.CENTER);
	
	var panelRight = new JPanel();
	btnSave = new JButton("Add to Inventory");
	btnSave.setEnabled(false);
	
	panelRight.setLayout(new BorderLayout());
	panelRight.add(createPanel,BorderLayout.CENTER);
	panelRight.add(btnSave,BorderLayout.SOUTH);
	
	
	add(panelRight,BorderLayout.EAST);
	
	btnSave.addActionListener(_->{
	    
	    try {
		var ret = service.addInventoryLine(createPanel.getInventoryLine());
		
		for(var i : ret.created())
		    model.addItem(i);
		
		
	    } catch (IOException e) {
		logger.error(e);
	    }
	    
	});
	
	
    }
    
    @SuppressWarnings("null")
    public void init(AbstractProduct p)
    {
	createPanel.init(p);
	btnSave.setEnabled(p!=null);
	var wk = new SwingWorker<List<InventoryLine>, Void>() {

	    @Override
	    protected List<InventoryLine> doInBackground() throws Exception {
	       return service.getInventoryLines(InventoryLinesRequest.create().setProductId(p.getId()));
	    }
	    
	    @Override
	    protected void done() {
	       try {
		model.init(get());
	       } catch (InterruptedException e) {
		   Thread.currentThread().interrupt();
	       } catch (ExecutionException e) {
		   logger.error(e);
	       }
	    }

	};
	
	wk.execute();
	
    }
    

}

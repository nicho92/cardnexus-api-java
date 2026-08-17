package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.model.SalesTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Sales;
import org.api.cardnexus.services.PricesService;

public class LastProductSalesPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private SalesTableModel model;
    protected transient Logger logger = LogManager.getLogger(getClass());
    
    
    public LastProductSalesPanel() {
	setLayout(new BorderLayout());
	
	model = new  SalesTableModel();
	
	var table = new JTable(model);
	
	
	add(new JScrollPane(table),BorderLayout.CENTER);
	
	
    }
    
    
    public void init(AbstractProduct p) {
	
	var wk = new SwingWorker<List<Sales>, Void>() {

	    @Override
	    protected List<Sales> doInBackground() throws Exception {
		return new PricesService().getLastSales(p);
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

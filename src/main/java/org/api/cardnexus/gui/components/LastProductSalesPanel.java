package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.model.SalesTableModel;
import org.api.cardnexus.model.AbstractProduct;
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
	
	
	try {
	    var ret = new PricesService().getLastSales(p);
	    model.init(ret);
	} catch (IOException e) {
	  logger.error(e);
	}
	
	
	
	
    }

}

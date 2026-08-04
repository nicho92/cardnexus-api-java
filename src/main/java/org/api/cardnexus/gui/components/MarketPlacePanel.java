package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.model.MarketListTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Seller;
import org.api.cardnexus.model.requests.MarketListRequest;
import org.api.cardnexus.services.ProductsService;

public class MarketPlacePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private MarketListTableModel model;
    private transient ProductsService service;
    
    public MarketPlacePanel() {
	
	service = new ProductsService();
	model = new MarketListTableModel();
	setLayout(new BorderLayout());
	
	var table = new JTable(model);
	
	table.setDefaultRenderer(Seller.class, (JTable _, Object value, boolean _, boolean _,int _, int _) -> {
	    	var ex = (Seller)value;
	    	return new JLabel(ex.username());
	});
	
	
	add(new JScrollPane(table));
	
    }
    
    public void init(AbstractProduct p)
    {
	try {
	    model.init(service.listMarketListing(MarketListRequest.create().setProductId(p.getId())));
	} catch (IOException e) {
	  logger.error(e);
	}
    }
    
    
    
}

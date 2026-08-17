package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.model.MarketListTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Seller;
import org.api.cardnexus.model.requests.CartAddingRequest;
import org.api.cardnexus.model.requests.MarketListRequest;
import org.api.cardnexus.services.CartService;
import org.api.cardnexus.services.ProductsService;

public class MarketPlacePanel extends JTabbedPane {

    private static final long serialVersionUID = 1L;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private MarketListTableModel modelMarketList;
    private MarketVariationPanel marketVariationsPanel;
    private PriceHistoryPanel priceHistoryPanel;
    private LastProductSalesPanel salesPanel;
     
    private transient ProductsService pservice;
    private transient CartService cService;
    private InventoryPanel inventoryPanel;
   
    public MarketPlacePanel() {
	
	pservice = new ProductsService();
	cService = new CartService();
	
	modelMarketList = new MarketListTableModel();
	var tableMarketLists = new JTable(modelMarketList);
	marketVariationsPanel = new MarketVariationPanel();
	priceHistoryPanel = new PriceHistoryPanel();
	salesPanel = new LastProductSalesPanel();
	inventoryPanel = new InventoryPanel();
	
	var panelMarketList = new JPanel();
	var panelMarketListCommands = new JPanel();
	var btnAddCart = new JButton("Add to Cart");
	
	tableMarketLists.setDefaultRenderer(Seller.class, (JTable _, Object value, boolean _, boolean _,int _, int _) -> {
	    	var ex = (Seller)value;
	    	return new JLabel(ex.username());
	});
	
	
	btnAddCart.setEnabled(false);  
	panelMarketList.setLayout(new BorderLayout());
	
	panelMarketListCommands.add(btnAddCart);
	
	panelMarketList.add(new JScrollPane(tableMarketLists), BorderLayout.CENTER);
	panelMarketList.add(new JScrollPane(panelMarketListCommands), BorderLayout.EAST);
	
	
	addTab("Nexus MarketList",panelMarketList);
	addTab("Markets",marketVariationsPanel);
	addTab("History", priceHistoryPanel);
	addTab("Last Sales", salesPanel);
	addTab("Inventory",inventoryPanel);
	
	
	tableMarketLists.getSelectionModel().addListSelectionListener(e->{
	    if (!e.getValueIsAdjusting()) 
    	    {
    		int row = tableMarketLists.convertRowIndexToModel(tableMarketLists.getSelectedRow());
    		btnAddCart.setEnabled(row>-1);
    	    }
	});
	
	btnAddCart.addActionListener(_->{
	    int row = tableMarketLists.convertRowIndexToModel(tableMarketLists.getSelectedRow());
	    if(row>-1)
		{
		    try {
			 var id = modelMarketList.getValueAt(row, 0).toString();
			cService.addItem(CartAddingRequest.create().setDeliveryCountry(Locale.getDefault().getCountry()).addItem(id, 1));
		    } catch (Exception e1) {
			logger.error(e1);
		    }
		}
	});
    }
    
    public void init(AbstractProduct p)
    {
	try {
	    modelMarketList.init(pservice.listMarketListing(MarketListRequest.create().setProductId(p.getId())));
	    marketVariationsPanel.init(p.getPrices());
	    priceHistoryPanel.init(p);
	    inventoryPanel.init(p);
	    salesPanel.init(p);
	    
	} catch (IOException e) {
	  logger.error(e);
	}
    }
    
    
    
}

package org.api.cardnexus.gui.components;

import java.io.IOException;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.model.MarketListTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.Seller;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.requests.HistoryRequest;
import org.api.cardnexus.model.requests.MarketListRequest;
import org.api.cardnexus.services.PricesService;
import org.api.cardnexus.services.ProductsService;

public class MarketPlacePanel extends JTabbedPane {

    private static final long serialVersionUID = 1L;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private MarketListTableModel modelMarketList;
    private MarketVariationPanel marketVariationsPanel;
    private PriceHistoryPanel priceHistoryPanel;
    
    private transient ProductsService service;
    private transient PricesService pService;
    
    public MarketPlacePanel() {
	
	service = new ProductsService();
	pService = new PricesService();
	modelMarketList = new MarketListTableModel();
	var tableMarketLists = new JTable(modelMarketList);
	marketVariationsPanel = new MarketVariationPanel();
	priceHistoryPanel = new PriceHistoryPanel();
	
	
	tableMarketLists.setDefaultRenderer(Seller.class, (JTable _, Object value, boolean _, boolean _,int _, int _) -> {
	    	var ex = (Seller)value;
	    	return new JLabel(ex.username());
	});
	
	addTab("Nexus MarketList",new JScrollPane(tableMarketLists));
	addTab("Markets",marketVariationsPanel);
	addTab("History", priceHistoryPanel);
    }
    
    public void init(AbstractProduct p)
    {
	try {
	    modelMarketList.init(service.listMarketListing(MarketListRequest.create().setProductId(p.getId())));
	    marketVariationsPanel.init(p.getPrices());
	    
	    priceHistoryPanel.init(pService.getHistoryPrice(HistoryRequest.create().setIdProduct(p.getId())));
	    
	} catch (IOException e) {
	  logger.error(e);
	}
    }
    
    
    
}

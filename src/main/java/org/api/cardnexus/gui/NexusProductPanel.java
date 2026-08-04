package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.components.MarketPlacePanel;
import org.api.cardnexus.gui.components.ProductPanel;
import org.api.cardnexus.gui.model.NexusProductTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class NexusProductPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private NexusProductTableModel modelProducts;
    
    private ProductsService service;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private AbstractProduct selectedProduct;
    
    public NexusProductPanel()
    {
	this(false,false);
    }
    
    
    public AbstractProduct getSelectedProduct() {
	return selectedProduct;
    }
    
    public NexusProductPanel(boolean showListings, boolean showdetails) {
	
		service = new ProductsService();
	
		setLayout(new BorderLayout(0, 0));
		
		modelProducts = new NexusProductTableModel();
		var table = new JTable(modelProducts);
		var panel = new JPanel();
		var textField = new JTextField(30);
		var btnSearch = new JButton("Search");
		var productPanel = new ProductPanel();
		  var panelListMarket = new MarketPlacePanel();
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		panel.add(textField);
		panel.add(btnSearch);
		

		if(showdetails) 
		    add(productPanel, BorderLayout.EAST);
		
		if(showListings) 
		    add(panelListMarket, BorderLayout.SOUTH);
		
		
		table.setDefaultRenderer(Expansion.class, (JTable _, Object value, boolean _, boolean _,int _, int _) -> {
		    	var ex = (Expansion)value;
		    	return new JLabel(ex.name());
		});
		
		textField.addActionListener(_->btnSearch.doClick());
		
		btnSearch.addActionListener(_->{
		    try {
			var res = service.searchProduct(SearchProductRequest.create().setName(textField.getText()).contains());
			modelProducts.init(res);
		    } catch (IOException e) {
			logger.error(e);
		    }
		});
		
		table.getSelectionModel().addListSelectionListener(e -> {
		    	    if (!e.getValueIsAdjusting()) 
		    	    {
		    		int row = table.convertRowIndexToModel(table.getSelectedRow());
		    	       
		    		 if(row>-1)
		    		 {     
		    		     var id = (Integer)modelProducts.getValueAt(row, 0);
		        	     try 
		        	     {
		        		 selectedProduct = service.getProductById(id);
		        		 if(showdetails)
		        		     productPanel.init(selectedProduct);
		        		
		        		 if(showListings)		        		 
		        		     panelListMarket.init(selectedProduct);
		        	     } 
		        	     catch (Exception e1) {
        		    		logger.error(e1);
		        	     }
		    		 }
		    	    }
		 });

		}
    }


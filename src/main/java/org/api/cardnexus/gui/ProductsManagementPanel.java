package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.components.MarketPlacePanel;
import org.api.cardnexus.gui.components.ProductPanel;
import org.api.cardnexus.gui.model.NexusProductTableModel;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class ProductsManagementPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private NexusProductTableModel modelProducts;
    
    private ProductsService service;
    protected transient Logger logger = LogManager.getLogger(getClass());

    
    
    public static void main(String[] args) throws IOException {
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.setDefaultGameValue("mtg");
	
	
	
	var f = new JFrame();
	f.getContentPane().add(new ProductsManagementPanel());
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	f.pack();
	f.setVisible(true);
	
    }
    
    
    public ProductsManagementPanel() {
	
		service = new ProductsService();
	
		setLayout(new BorderLayout(0, 0));
		
		modelProducts = new NexusProductTableModel();
		var table = new JTable(modelProducts);
		var panel = new JPanel();
		var textField = new JTextField(30);
		var btnSearch = new JButton("Search");
		var productPanel = new ProductPanel();
		
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		panel.add(textField);
		panel.add(btnSearch);
		add(productPanel, BorderLayout.EAST);
		
		var panelListMarket = new MarketPlacePanel();
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
		        	     try {
		        		 var p = service.getProductById(id);
		        		 productPanel.init(p);
		        		 panelListMarket.init(p);
		        	     } 
		        	     catch (Exception e1) {
        		    		logger.error(e1);
		        	     }
		    		 }
		    	    }
		 });
		
	}

     

}

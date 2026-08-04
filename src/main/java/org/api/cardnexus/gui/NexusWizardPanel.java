package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.model.CardWizardTableModel;
import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.ProductEntry;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.requests.CardOptimizationRequest;
import org.api.cardnexus.services.CartService;

public class NexusWizardPanel extends JPanel {
    
    	private static final long serialVersionUID = 1L;
    	private JTable table;
    	private CartService cartService;
    	private CardWizardTableModel model;
        protected transient Logger logger = LogManager.getLogger(getClass());
    	
    	
	public NexusWizardPanel() {
	    	
	    	model = new CardWizardTableModel();
	    	cartService = new CartService();
	    	
		setLayout(new BorderLayout());
		var productsManagementPanel = new NexusProductPanel(false,true);
		add(productsManagementPanel, BorderLayout.WEST);
		
		var panelWizardConfig = new JPanel();
		add(panelWizardConfig, BorderLayout.CENTER);
		panelWizardConfig.setLayout(new BorderLayout());
		
		table = new JTable(model);
		panelWizardConfig.add(new JScrollPane(table), BorderLayout.CENTER);
		
		var btnRunWizard = new JButton("Start Wizard");
		panelWizardConfig.add(btnRunWizard, BorderLayout.SOUTH);
		
		var panelCommand = new JPanel();
		panelWizardConfig.add(panelCommand, BorderLayout.NORTH);
		
		var btnAddProduct = new JButton("Add Product");
		panelCommand.add(btnAddProduct);
		
		var btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.setEnabled(false);
		panelCommand.add(btnRemoveProduct);
		
		JPanel jobResultsPanel = new JPanel();
		add(jobResultsPanel, BorderLayout.SOUTH);
		
		
		table.setDefaultEditor(EnumFinishes.class, new DefaultCellEditor(new JComboBox<>(EnumFinishes.values())));
		table.setDefaultEditor(EnumCondition.class, new DefaultCellEditor(new JComboBox<>(EnumCondition.values())));
		table.setDefaultEditor(Amount.class, new DefaultCellEditor(new JTextField()));
		
		btnAddProduct.addActionListener(_->{
		    
		    if(productsManagementPanel.getSelectedProduct()==null)
			return;
		    
		    var p = productsManagementPanel.getSelectedProduct();
		    
		    var entry = new ProductEntry();
		    	  entry.setLanguages(List.of("en"));
		    	  entry.setFinish(EnumFinishes.Standard);
		    	  entry.setMinCondition(EnumCondition.LP);
		    	  entry.setQuantity(1);
		    	  entry.setMaxUnitPrice(new Amount(1.0, "EUR"));
		    	  entry.setProductId(p.getId());
		    
		    model.addItem(entry);
		    
		});
		
		
		btnRunWizard.addActionListener(_->{
		    
		    var req = CardOptimizationRequest.create().setCountry(Locale.getDefault().getCountry());
		    model.getItems().forEach(req::addEntry);
		    try {
			var idjob = cartService.runOptimizationQuery(req);
			logger.info("job created with id={}",idjob);
			var job = cartService.getRunById(idjob);
			
			
			
		    } catch (IOException e) {
			logger.error(e);
		    }
		});
		
		table.getSelectionModel().addListSelectionListener(e -> {
	    	    if (!e.getValueIsAdjusting()) 
	    	    {
	    		int row = table.convertRowIndexToModel(table.getSelectedRow());
	    	        btnRemoveProduct.setEnabled(row>-1);
	    	    }
		});
		
		btnRemoveProduct.addActionListener(_->{
	    	     int row = table.convertRowIndexToModel(table.getSelectedRow());
	    	     model.removeRow(row);
	    	 }); 
		
		
	}


    public static void main(String[] args) throws IOException {
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.setDefaultGameValue("mtg");
	
	var f = new JFrame();
	f.getContentPane().add(new NexusWizardPanel());
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	f.pack();
	f.setVisible(true);
    }
    
      
    
    
}

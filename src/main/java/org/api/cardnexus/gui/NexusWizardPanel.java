package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.components.RunResultPanel;
import org.api.cardnexus.gui.model.CardWizardTableModel;
import org.api.cardnexus.model.Amount;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.ProductEntry;
import org.api.cardnexus.model.SealedProduct;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.model.enums.EnumKindsRun;
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
		var productsManagementPanel = new NexusProductPanel(false,true,false);
		add(productsManagementPanel, BorderLayout.WEST);
		
		var panelWizardConfig = new JPanel();
		add(panelWizardConfig, BorderLayout.CENTER);
		panelWizardConfig.setLayout(new BorderLayout());
		
		table = new JTable(model);
		panelWizardConfig.add(new JScrollPane(table), BorderLayout.CENTER);
		
		
		var panelWizardLaunch = new JPanel();
		var listKinds = new JList<EnumKindsRun>(EnumKindsRun.values());
		listKinds.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listKinds.setSelectedIndices(new int[] {0,1,2});
		var btnRunWizard = new JButton("Start Wizard");
		
		panelWizardLaunch.add(listKinds);
		panelWizardLaunch.add(btnRunWizard);
		
		panelWizardConfig.add(panelWizardLaunch, BorderLayout.SOUTH);
		
		var panelCommand = new JPanel();
		panelWizardConfig.add(panelCommand, BorderLayout.NORTH);
		
		var btnAddProduct = new JButton("Add Product");
		panelCommand.add(btnAddProduct);
		
		var btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.setEnabled(false);
		panelCommand.add(btnRemoveProduct);
		
		var jobResultsPanel = new JPanel();
		add(jobResultsPanel, BorderLayout.SOUTH);
		jobResultsPanel.setLayout(new BorderLayout(0, 0));
		
		var modelRuns = new DefaultListModel<String>();
		var listJobs = new JList<String>(modelRuns);
		
		jobResultsPanel.add(new JScrollPane(listJobs), BorderLayout.WEST);
		
		var resultPanel = new RunResultPanel();
		jobResultsPanel.add(resultPanel, BorderLayout.CENTER);
		
		
		table.setDefaultEditor(EnumFinishes.class, new DefaultCellEditor(new JComboBox<>(EnumFinishes.values())));
		table.setDefaultEditor(EnumCondition.class, new DefaultCellEditor(new JComboBox<>(EnumCondition.values())));
		table.setDefaultEditor(Amount.class, new DefaultCellEditor(new JTextField()));
		
		btnAddProduct.addActionListener(_->{
		    
		    if(productsManagementPanel.getSelectedProduct()==null)
			return;
		    
		    var p = productsManagementPanel.getSelectedProduct();
		    
		    var entry = new ProductEntry();
		    var currency = Currency.getInstance(Locale.getDefault()).getCurrencyCode();
		    
		    if(p instanceof CardProduct c)
		    {
			entry.setFinish(c.getFinishes().getFirst());
			try 
			{
			    entry.setMaxUnitPrice(new Amount(c.getPrices().get(c.getFinishes().getFirst()).cardmarket().marketValue(), currency));
			}
			catch(Exception _)
			{
			    entry.setMaxUnitPrice(new Amount(100.0,currency));
			}
			entry.setMinCondition(EnumCondition.LP);
		    }
		    else if(p instanceof SealedProduct c)
		    {
			try 
			{
			    entry.setMaxUnitPrice(new Amount(c.getPrices().get(EnumFinishes.Standard).cardmarket().marketValue(), currency));
			}
			catch(Exception _)
			{
			    entry.setMaxUnitPrice(new Amount(100.0,currency));
			}
		    }

		    entry.setLanguages(List.of("en"));
		    entry.setQuantity(1);
		    entry.setProductId(p.getId());
		    model.addItem(entry);
		    
		});
		
		
		listJobs.addListSelectionListener(l->{
		    
		    if(!l.getValueIsAdjusting())
		    {
			
			try {
			    var job = cartService.getRunById(listJobs.getSelectedValue());
			    
			    resultPanel.init(job);
			    
			} catch (IOException e) {
			    logger.error(e);
			}
		    }
		    
		});
		
		
		btnRunWizard.addActionListener(_->{
		    
		    var req = CardOptimizationRequest.create().setCountry(Locale.getDefault().getCountry()).setOptions(listKinds.getSelectedValuesList(), false);
		    model.getItems().forEach(req::addEntry);
		    try {
			var idjob = cartService.runOptimizationQuery(req);
			logger.info("job created with id={}",idjob);
			modelRuns.addElement(idjob);
			
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


      
    
    
}

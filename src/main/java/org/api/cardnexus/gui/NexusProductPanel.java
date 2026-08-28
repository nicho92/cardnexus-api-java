package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.components.LoadingLabel;
import org.api.cardnexus.gui.components.MarketPlacePanel;
import org.api.cardnexus.gui.components.ProductPicturePanel;
import org.api.cardnexus.gui.model.CardAttributsTableModel;
import org.api.cardnexus.gui.model.NexusProductTableModel;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.Expansion;
import org.api.cardnexus.model.enums.EnumProductType;
import org.api.cardnexus.model.requests.SearchProductRequest;
import org.api.cardnexus.services.ProductsService;

public class NexusProductPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private NexusProductTableModel modelProducts;
    
    private ProductsService service;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private transient AbstractProduct selectedProduct;
    private DefaultListModel<Expansion> modelExpansions;
	private CardAttributsTableModel attributsModel;
    
    
    public NexusProductPanel()
    {
    	this(true,true,true);
    }
    
    
    public AbstractProduct getSelectedProduct() {
	return selectedProduct;
    }
    
    public NexusProductPanel(boolean showListings, boolean showdetails, boolean showExpansion) {
	
		service = new ProductsService();
	
		setLayout(new BorderLayout());
		
		attributsModel = new CardAttributsTableModel();
		modelProducts = new NexusProductTableModel();
		modelExpansions = new DefaultListModel<Expansion>();
		
		var loading = new LoadingLabel();
		var table = new JTable(modelProducts);
		var panel = new JPanel();
		var textField = new JTextField(30);
		var btnSearch = new JButton("Search");
		var picturePanel = new ProductPicturePanel();
		var panelListMarket = new MarketPlacePanel();
		var listExpansion = new JList<Expansion>(modelExpansions);
		 
		var chkSealed = new JCheckBox("Sealed");
		var chkCard = new JCheckBox("Card");
		var chkInStock = new JCheckBox("In Stock only");
		
		
		chkSealed.setSelected(true);
		chkCard.setSelected(true);
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		
		panel.add(chkSealed);
		panel.add(chkCard);
		panel.add(chkInStock);
		
		panel.add(textField);
		panel.add(btnSearch);
		panel.add(loading);
		
		if(showdetails) 
		{
			var pane = new JTabbedPane();
			pane.addTab("Picture",picturePanel);
			pane.add("Attributs",new JScrollPane( new JTable(attributsModel)));
			add(pane, BorderLayout.EAST);
		}
		
		if(showListings) 
		    add(panelListMarket, BorderLayout.SOUTH);
		
		if(showExpansion) 
		    add(new JScrollPane(listExpansion),BorderLayout.WEST);
		
		listExpansion.setCellRenderer(new ListCellRenderer<Expansion>() {

		    JLabel label = new JLabel();
		    @Override
		    public Component getListCellRendererComponent(JList<? extends Expansion> list, Expansion value,int index, boolean isSelected, boolean cellHasFocus) {
			
			label.setText(value.name());
			label.setOpaque(true);
			
			if(isSelected)
			    label.setBackground(list.getSelectionBackground());
			else
			    label.setBackground(list.getBackground());
			
			
			return label;
			
		}});
		
		var wkEx = new SwingWorker<List<Expansion>, Void>()
			  {

			    @Override
			    protected List<Expansion> doInBackground() throws Exception {
				return  service.listExpansion(NexusConfig.getDefaultGameValue());
			    }
		      
			    @Override
			    protected void done() {
				try {
				    get().forEach(modelExpansions::addElement);
				} catch (InterruptedException _) {
				   Thread.currentThread().interrupt();
				} catch (ExecutionException e) {
				    logger.error(e);
				}
				
				loading.setVisible(false);
			    }
			    
			  };
			  
		wkEx.execute();
		
		table.setDefaultRenderer(Expansion.class, (JTable _, Object value, boolean _, boolean _,int _, int _) -> {
		    	var ex = (Expansion)value;
		    	return new JLabel(ex.name());
		});
		
		textField.addActionListener(_->btnSearch.doClick());
		
		
		listExpansion.getSelectionModel().addListSelectionListener(lsl->{
		    
		   if(!lsl.getValueIsAdjusting())
		   {
		       loading.setVisible(true);
		       var wk = new SwingWorker<List<AbstractProduct>, Void>()
			    {

				@Override
				protected List<AbstractProduct> doInBackground() throws Exception {
				    
				    var req = SearchProductRequest.create().setExpansionId(listExpansion.getSelectedValue().id())
					    							.setListings(null, chkInStock.isSelected(), null);
				    	
				    if(chkCard.isSelected() && chkSealed.isSelected())
					req.setProductTypes(EnumProductType.card, EnumProductType.sealed);
				    else
					req.setProductTypes(chkSealed.isSelected()?EnumProductType.sealed:EnumProductType.card);
				    
				    
				   return service.searchProduct(req);
				}

				@Override
				protected void done() {
				    try {
					modelProducts.init(get());
				    } catch (InterruptedException _) {
					Thread.currentThread().interrupt();
				    } catch (ExecutionException e) {
					logger.error(e);
				    }
				    loading.setVisible(false);
				}
			    };
			   wk.execute(); 
		       
		   }
		    
		});
		
		btnSearch.addActionListener(_->{
		    loading.setVisible(true);
		    var wk = new SwingWorker<List<AbstractProduct>, Void>()
			    {

				@Override
				protected List<AbstractProduct> doInBackground() throws Exception {
				    var req = SearchProductRequest.create().setName(textField.getText()).contains().setListings(null, chkInStock.isSelected(), null);
				    	
				    
				    if(chkCard.isSelected() && chkSealed.isSelected())
					req.setProductTypes(EnumProductType.card, EnumProductType.sealed);
				    else
					req.setProductTypes(chkSealed.isSelected()?EnumProductType.sealed:EnumProductType.card);
		
				    
				    
				   return service.searchProduct(req);
				}

				@Override
				protected void done() {
				    try {
					modelProducts.init(get());
				    } catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				    } catch (ExecutionException e) {
					logger.error(e);
				    }
				    loading.setVisible(false);
				}
			    };
			   wk.execute(); 
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
		        		 {
		        			 picturePanel.init(selectedProduct);
		        			 if(selectedProduct instanceof CardProduct card)
		        				 attributsModel.init(card.getAttributes());
		        			 
		        		 }
		        		
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


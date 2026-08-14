package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.CartItemEntry;
import org.api.cardnexus.services.CartService;

public class NexusCartPanel extends JPanel {
    
    	private DefaultMutableTreeNode root;
    	private CartService cService;
        protected transient Logger logger = LogManager.getLogger(getClass());
    
	public NexusCartPanel() {
		setLayout(new BorderLayout());
		
		cService = new CartService();
		var btnDelete = new JButton("Delete item");
		var btnReload = new JButton("Reload");
		var panelCartCommand = new JPanel();
		root = new DefaultMutableTreeNode("Cart");
		tree = new JTree(new DefaultTreeModel(root));
		
		
		add(panelCartCommand, BorderLayout.NORTH);
		panelCartCommand.add(btnReload);
		panelCartCommand.add(btnDelete);
		add(new JScrollPane(tree), BorderLayout.CENTER);
		
		tree.addTreeSelectionListener(_->{
		    var node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		    btnDelete.setEnabled(node.getUserObject() instanceof CartItemEntry);
		    
		    
		});
		
		btnDelete.addActionListener(_->{
		    
		    var node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		    var entry = (CartItemEntry)node.getUserObject();
		    var res = JOptionPane.showConfirmDialog(this, "Remove Item #"+ entry.productName() + " ?");
		    
		    if(res==JOptionPane.YES_OPTION)
		    {
			try {
			    cService.removeItem( entry.listingId());
			} catch (IOException e) {
			  logger.error(e);
			}
		    }
		    
		    btnReload.doClick();
		    
		});
		
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
		    @Override
		    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			var node = (DefaultMutableTreeNode)value;
			if(node.getUserObject() instanceof CartItemEntry entry)
			    return super.getTreeCellRendererComponent(tree, entry.productName(), sel, expanded, leaf, row, hasFocus);

			return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		    }
		});
		
		btnReload.addActionListener(_->{
		    
		    try {
			  root.removeAllChildren();
			  var cart = cService.getYourCart();

			  root.add(new DefaultMutableTreeNode("Delivery to " + cart.deliveryCountry()));

			  for(var cartItem : cart.sellers())
			  {
			      var sellerNde  = new DefaultMutableTreeNode(cartItem.seller().username());
			      for(var item : cartItem.items())
			      {
				 var productNde = new DefaultMutableTreeNode(item);
        				 productNde.add(new DefaultMutableTreeNode(item.finish()));
        				 productNde.add(new DefaultMutableTreeNode(item.language()));
        				 productNde.add(new DefaultMutableTreeNode(item.condition()));
        				 productNde.add(new DefaultMutableTreeNode(item.quantity()));
        				 productNde.add(new DefaultMutableTreeNode(item.unitPrice()));
				 
				 sellerNde.add(productNde);
				 
			      }
			      root.add(sellerNde);
			      tree.updateUI();
			      
			  }
		    } catch (IOException e) {
			logger.error(e);
		    }
		});
	
	}

    private static final long serialVersionUID = 1L;
    private JTree tree;
    
    
    
    
}

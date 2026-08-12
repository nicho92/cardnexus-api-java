package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.CartItem;
import org.api.cardnexus.model.CartItemEntry;
import org.api.cardnexus.services.CartService;

public class NexusCartPanel extends JPanel {
    
    	private DefaultMutableTreeNode root;
    	private CartService cService;
        protected transient Logger logger = LogManager.getLogger(getClass());
    
	public NexusCartPanel() {
		setLayout(new BorderLayout(0, 0));
		
		
		cService = new CartService();
		
		var panelCartCommand = new JPanel();
		add(panelCartCommand, BorderLayout.NORTH);
		
		var btnReload = new JButton("Reload");
		panelCartCommand.add(btnReload);
		
		var btnDelete = new JButton("Delete item");
		panelCartCommand.add(btnDelete);
		root = new DefaultMutableTreeNode("Cart");
		tree = new JTree(new DefaultTreeModel(root));
		
		add(new JScrollPane(tree), BorderLayout.CENTER);
		
		
		tree.addTreeSelectionListener(_->{
		    
		    var node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		    btnDelete.setEnabled(node.getUserObject() instanceof CartItemEntry);
		});
		
		btnDelete.addActionListener(_->{
		    
		    var node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		    var id = ((CartItemEntry)node.getUserObject()).listingId();
		    
		    var res = JOptionPane.showConfirmDialog(this, "Remove Item #"+id + " ?");
		    
		    if(res==JOptionPane.YES_OPTION)
		    {
			try {
			    cService.removeItem(id);
			} catch (IOException e) {
			  logger.error(e);
			}
		    }
		    
		    
		});
		
		
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
		    
		    @Override
		    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			var node = (DefaultMutableTreeNode)value;
			if(node.getUserObject() instanceof CartItemEntry entry)
			{
			    return super.getTreeCellRendererComponent(tree, entry.productName(), sel, expanded, leaf, row, hasFocus);
			}
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
			  }
		    } catch (IOException e) {
			logger.error(e);
		    }
		});
	
	}

    private static final long serialVersionUID = 1L;
    private JTree tree;
    
    
    
    
}

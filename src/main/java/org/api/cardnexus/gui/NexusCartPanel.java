package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		
		var lblTotal = new JLabel("Total 0.0");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTotal, BorderLayout.SOUTH);
		
		
		btnReload.addActionListener(_->{
		    
		    try {
			var cart = cService.getYourCart();
			  root.removeAllChildren();
			  
			  root.add(new DefaultMutableTreeNode("Delivery to " + cart.deliveryCountry(),true));

			  for(var cartItem : cart.sellers())
			  {
			      
			      var sellerNde  = new DefaultMutableTreeNode(cartItem.seller().username() + " " + cartItem.itemsSubtotal());
			      for(var item : cartItem.items())
			      {
				 var productNde = new DefaultMutableTreeNode(item.productName());
				 
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

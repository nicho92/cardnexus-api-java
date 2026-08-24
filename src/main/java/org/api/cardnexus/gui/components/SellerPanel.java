package org.api.cardnexus.gui.components;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.api.cardnexus.model.Seller;

public class SellerPanel extends JPanel{
	private JLabel lblType;
	private JLabel lblCountry;
	private JLabel lblCurrency;
	private JCheckBox chkAvailable;


	public SellerPanel() {
		setLayout(new GridLayout(4, 1, 0, 0));
		
		lblType = new JLabel(" ");
		add(lblType);
		
		lblCountry = new JLabel(" ");
		add(lblCountry);
		
		lblCurrency = new JLabel(" ");
		add(lblCurrency);
		
		chkAvailable = new JCheckBox("available");
		add(chkAvailable);
	}
	
	
	public void setSeller(Seller seller)
	{
	    
	    if(seller==null)
		return;
	    
	    
	    lblType.setText(seller.type());
	    
	    lblCountry.setText(seller.country());
	    
	    lblCurrency.setText(seller.currency());
	    
	    chkAvailable.setSelected(seller.available());
	}
	
	
    private static final long serialVersionUID = 1L;
    
    
    
    
}

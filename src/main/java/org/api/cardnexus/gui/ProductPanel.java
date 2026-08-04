package org.api.cardnexus.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.api.cardnexus.model.AbstractProduct;

public class ProductPanel extends JPanel {
	public ProductPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{199, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblImage = new JLabel(" ");
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridheight = 5;
		gbc_lblImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(lblImage, gbc_lblImage);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		txtExpansion = new JTextField();
		txtExpansion.setEditable(false);
		GridBagConstraints gbc_txtExpansion = new GridBagConstraints();
		gbc_txtExpansion.insets = new Insets(0, 0, 5, 5);
		gbc_txtExpansion.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtExpansion.gridx = 1;
		gbc_txtExpansion.gridy = 2;
		add(txtExpansion, gbc_txtExpansion);
		txtExpansion.setColumns(10);
		
		txtSlug = new JTextField();
		txtSlug.setEditable(false);
		GridBagConstraints gbc_txtSlug = new GridBagConstraints();
		gbc_txtSlug.insets = new Insets(0, 0, 5, 5);
		gbc_txtSlug.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSlug.gridx = 1;
		gbc_txtSlug.gridy = 3;
		add(txtSlug, gbc_txtSlug);
		txtSlug.setColumns(10);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		GridBagConstraints gbc_txtType = new GridBagConstraints();
		gbc_txtType.anchor = GridBagConstraints.NORTH;
		gbc_txtType.insets = new Insets(0, 0, 0, 5);
		gbc_txtType.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtType.gridx = 1;
		gbc_txtType.gridy = 4;
		add(txtType, gbc_txtType);
		txtType.setColumns(10);
	}

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtExpansion;
    private JTextField txtSlug;
    private JTextField txtType;
    private JLabel lblImage;

    public void init(AbstractProduct p) {
	txtName.setText(p.getName());
	txtExpansion.setText(p.getExpansion().name());
	txtSlug.setText(p.getNameSlug());
	txtType.setText(p.getProductType().name());
	
	try {
	    var img = ImageIO.read(URI.create(p.getImageUrl()).toURL());
	    var rimg =  img.getScaledInstance(img.getWidth()/3, img.getHeight()/3, Image.SCALE_SMOOTH);
	    
	    lblImage.setIcon(new ImageIcon(rimg));
	    
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	
    }

}

package org.api.cardnexus.gui.components;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;

public class ProductPanel extends JPanel {
    
    protected transient Logger logger = LogManager.getLogger(getClass());

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtExpansion;
    private JTextField txtSlug;
    private JTextField txtType;
    private JLabel lblImage;


	public ProductPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{199, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblImage = new JLabel(" ");
		GridBagConstraints gbclblImage = new GridBagConstraints();
		gbclblImage.gridheight = 5;
		gbclblImage.insets = new Insets(0, 0, 5, 5);
		gbclblImage.gridx = 0;
		gbclblImage.gridy = 0;
		add(lblImage, gbclblImage);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		GridBagConstraints gbctxtName = new GridBagConstraints();
		gbctxtName.insets = new Insets(0, 0, 5, 0);
		gbctxtName.fill = GridBagConstraints.HORIZONTAL;
		gbctxtName.gridx = 1;
		gbctxtName.gridy = 1;
		add(txtName, gbctxtName);
		txtName.setColumns(10);
		
		txtExpansion = new JTextField();
		txtExpansion.setEditable(false);
		GridBagConstraints gbctxtExpansion = new GridBagConstraints();
		gbctxtExpansion.insets = new Insets(0, 0, 5, 0);
		gbctxtExpansion.fill = GridBagConstraints.HORIZONTAL;
		gbctxtExpansion.gridx = 1;
		gbctxtExpansion.gridy = 2;
		add(txtExpansion, gbctxtExpansion);
		txtExpansion.setColumns(10);
		
		txtSlug = new JTextField();
		txtSlug.setEditable(false);
		GridBagConstraints gbctxtSlug = new GridBagConstraints();
		gbctxtSlug.insets = new Insets(0, 0, 5, 0);
		gbctxtSlug.fill = GridBagConstraints.HORIZONTAL;
		gbctxtSlug.gridx = 1;
		gbctxtSlug.gridy = 3;
		add(txtSlug, gbctxtSlug);
		txtSlug.setColumns(10);
		
		txtType = new JTextField();
		txtType.setEditable(false);
		GridBagConstraints gbctxtType = new GridBagConstraints();
		gbctxtType.anchor = GridBagConstraints.NORTH;
		gbctxtType.insets = new Insets(0, 0, 5, 0);
		gbctxtType.fill = GridBagConstraints.HORIZONTAL;
		gbctxtType.gridx = 1;
		gbctxtType.gridy = 4;
		add(txtType, gbctxtType);
		txtType.setColumns(10);
	
	}

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
	   logger.error(e);
	}
	
	
    }

}

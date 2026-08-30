package org.api.cardnexus.gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;

public class InventoryLineCreationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private AbstractProduct product;
    private JTextField txtCustomId;
    private JTextField txtComment;
    private JComboBox<EnumFinishes> cboFinish;
    private JComboBox<EnumCondition> cboCondition;
    private JComboBox<String> cboLang;
    private JCheckBox chkForSale;
    private JSpinner spnQty;
    private ProductPicturePanel productPicturePanel;
    private JLabel lblCustom;
    private JLabel lblComment;
    private JLabel lblFinish;
    private JLabel lblCondition;
    private JLabel lblLang;
    private JLabel lblQty;
    protected transient Logger logger = LogManager.getLogger(getClass());
    
    public InventoryLineCreationPanel()
    {
	this(true);
    }
    
    
    public InventoryLineCreationPanel(boolean showThumbnail) {
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
    	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    	setLayout(gridBagLayout);
    	
    	
    	if(showThumbnail)
    	{
    	productPicturePanel = new ProductPicturePanel();
    	GridBagConstraints gbc_productPicturePanel = new GridBagConstraints();
    	gbc_productPicturePanel.gridheight = 7;
    	gbc_productPicturePanel.insets = new Insets(0, 0, 5, 5);
    	gbc_productPicturePanel.fill = GridBagConstraints.BOTH;
    	gbc_productPicturePanel.gridx = 0;
    	gbc_productPicturePanel.gridy = 0;
    	add(productPicturePanel, gbc_productPicturePanel);
    	}
    	
    	
    	lblCustom = new JLabel("Custom ID :");
    	GridBagConstraints gbc_lblCustom = new GridBagConstraints();
    	gbc_lblCustom.insets = new Insets(0, 0, 5, 5);
    	gbc_lblCustom.anchor = GridBagConstraints.EAST;
    	gbc_lblCustom.gridx = 1;
    	gbc_lblCustom.gridy = 0;
    	add(lblCustom, gbc_lblCustom);
    	
    	txtCustomId = new JTextField();
    	GridBagConstraints gbc_txtCustomId = new GridBagConstraints();
    	gbc_txtCustomId.insets = new Insets(0, 0, 5, 0);
    	gbc_txtCustomId.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtCustomId.gridx = 2;
    	gbc_txtCustomId.gridy = 0;
    	add(txtCustomId, gbc_txtCustomId);
    	txtCustomId.setColumns(10);
    	
    	lblComment = new JLabel("Comment :");
    	GridBagConstraints gbc_lblComment = new GridBagConstraints();
    	gbc_lblComment.insets = new Insets(0, 0, 5, 5);
    	gbc_lblComment.anchor = GridBagConstraints.EAST;
    	gbc_lblComment.gridx = 1;
    	gbc_lblComment.gridy = 1;
    	add(lblComment, gbc_lblComment);
    	
    	txtComment = new JTextField();
    	GridBagConstraints gbc_txtComment = new GridBagConstraints();
    	gbc_txtComment.insets = new Insets(0, 0, 5, 0);
    	gbc_txtComment.fill = GridBagConstraints.HORIZONTAL;
    	gbc_txtComment.gridx = 2;
    	gbc_txtComment.gridy = 1;
    	add(txtComment, gbc_txtComment);
    	txtComment.setColumns(10);
    	
    	lblFinish = new JLabel("Finish :");
    	GridBagConstraints gbc_lblFinish = new GridBagConstraints();
    	gbc_lblFinish.insets = new Insets(0, 0, 5, 5);
    	gbc_lblFinish.anchor = GridBagConstraints.EAST;
    	gbc_lblFinish.gridx = 1;
    	gbc_lblFinish.gridy = 2;
    	add(lblFinish, gbc_lblFinish);
    	
    	cboFinish = new JComboBox<>();
    	GridBagConstraints gbc_cboFinish = new GridBagConstraints();
    	gbc_cboFinish.insets = new Insets(0, 0, 5, 0);
    	gbc_cboFinish.fill = GridBagConstraints.HORIZONTAL;
    	gbc_cboFinish.gridx = 2;
    	gbc_cboFinish.gridy = 2;
    	add(cboFinish, gbc_cboFinish);
    	
    	lblCondition = new JLabel("Condition :");
    	GridBagConstraints gbc_lblCondition = new GridBagConstraints();
    	gbc_lblCondition.insets = new Insets(0, 0, 5, 5);
    	gbc_lblCondition.anchor = GridBagConstraints.EAST;
    	gbc_lblCondition.gridx = 1;
    	gbc_lblCondition.gridy = 3;
    	add(lblCondition, gbc_lblCondition);
    	
    	cboCondition = new JComboBox<>();
    	GridBagConstraints gbc_cboCondition = new GridBagConstraints();
    	gbc_cboCondition.insets = new Insets(0, 0, 5, 0);
    	gbc_cboCondition.fill = GridBagConstraints.HORIZONTAL;
    	gbc_cboCondition.gridx = 2;
    	gbc_cboCondition.gridy = 3;
    	add(cboCondition, gbc_cboCondition);
    	
    	lblLang = new JLabel("Language :");
    	GridBagConstraints gbc_lblLang = new GridBagConstraints();
    	gbc_lblLang.insets = new Insets(0, 0, 5, 5);
    	gbc_lblLang.anchor = GridBagConstraints.EAST;
    	gbc_lblLang.gridx = 1;
    	gbc_lblLang.gridy = 4;
    	add(lblLang, gbc_lblLang);
    	
    	cboLang = new JComboBox<>();
    	GridBagConstraints gbc_cboLang = new GridBagConstraints();
    	gbc_cboLang.insets = new Insets(0, 0, 5, 0);
    	gbc_cboLang.fill = GridBagConstraints.HORIZONTAL;
    	gbc_cboLang.gridx = 2;
    	gbc_cboLang.gridy = 4;
    	add(cboLang, gbc_cboLang);
    	
    	lblQty = new JLabel("Quantity :");
    	GridBagConstraints gbc_lblQty = new GridBagConstraints();
    	gbc_lblQty.anchor = GridBagConstraints.EAST;
    	gbc_lblQty.insets = new Insets(0, 0, 5, 5);
    	gbc_lblQty.gridx = 1;
    	gbc_lblQty.gridy = 5;
    	add(lblQty, gbc_lblQty);
    	
    	spnQty = new JSpinner();
    	spnQty.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
    	GridBagConstraints gbc_spnQty = new GridBagConstraints();
    	gbc_spnQty.fill = GridBagConstraints.HORIZONTAL;
    	gbc_spnQty.insets = new Insets(0, 0, 5, 0);
    	gbc_spnQty.gridx = 2;
    	gbc_spnQty.gridy = 5;
    	add(spnQty, gbc_spnQty);
    	
    	chkForSale = new JCheckBox("For Sale ? ");
    	GridBagConstraints gbc_chkForSale = new GridBagConstraints();
    	gbc_chkForSale.gridx = 2;
    	gbc_chkForSale.gridy = 6;
    	add(chkForSale, gbc_chkForSale);
    	
    	
    }
    
    public void init(CardProduct c)
    {
	this.product=c;
	
	if(productPicturePanel!=null)
	    productPicturePanel.init(c);
	
	
	cboFinish.removeAllItems();
	cboCondition.removeAllItems();
	cboLang.removeAllItems();
	
	
	((DefaultComboBoxModel<EnumFinishes>)cboFinish.getModel()).addAll(c.getFinishes());
	((DefaultComboBoxModel<EnumCondition>)cboCondition.getModel()).addAll(List.of(EnumCondition.values()));
	((DefaultComboBoxModel<String>)cboLang.getModel()).addAll(c.getLanguages());
	
    }
    
    
    
    public InventoryLine getInventoryLine() throws IOException
    {
	var cId = txtCustomId.getText().isEmpty() ? null:txtCustomId.getText();
	var comment = txtComment.getText().isEmpty() ? null:txtComment.getText();
	
	return new InventoryLine(null,cId, comment, product.getId(), product.getGameId(), cboFinish.getItemAt(cboFinish.getSelectedIndex()), cboCondition.getItemAt(cboCondition.getSelectedIndex()), cboLang.getItemAt(cboLang.getSelectedIndex()), (Integer)spnQty.getValue(), chkForSale.isSelected(), null, null, null, null, null, null);
    }

    public void init(AbstractProduct selectedProduct) {
	
	if(selectedProduct instanceof CardProduct p)
	    init(p);
	
    }
    
    
    
}

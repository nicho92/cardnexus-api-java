package org.api.cardnexus.gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.CardProduct;
import org.api.cardnexus.model.InventoryLine;
import org.api.cardnexus.model.enums.EnumCondition;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.services.InventoryService;

public class InventoryLineCreationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private transient AbstractProduct product;
    private JTextField txtCustomId;
    private JTextField txtComment;
    private JComboBox<EnumFinishes> cboFinish;
    private JComboBox<EnumCondition> cboCondition;
    private JComboBox<String> cboLang;
    private JCheckBox chkForSale;
    private JSpinner spnQty;
    private ProductPicturePanel productPicturePanel;
    protected transient Logger logger = LogManager.getLogger(getClass());

    private DefaultListModel<String> tagsModel;

    private DefaultListModel<String> locationsModel;

    private JList<String> lstTags;

    private JList<String> lstLocation;
    
    public InventoryLineCreationPanel()
    {
	this(true);
    }
    
    
    public InventoryLineCreationPanel(boolean showThumbnail) {
	
    	var gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
    	gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
    	setLayout(gridBagLayout);
    	
    	
    	if(showThumbnail)
    	{
        	productPicturePanel = new ProductPicturePanel();
        	var gbcproductPicturePanel = new GridBagConstraints();
        	gbcproductPicturePanel.gridheight = 7;
        	gbcproductPicturePanel.insets = new Insets(0, 0, 5, 5);
        	gbcproductPicturePanel.fill = GridBagConstraints.BOTH;
        	gbcproductPicturePanel.gridx = 0;
        	gbcproductPicturePanel.gridy = 0;
        	add(productPicturePanel, gbcproductPicturePanel);
    	}
    	
    	
    	var lblCustom = new JLabel("Custom ID :");
    	var gbclblCustom = new GridBagConstraints();
    	gbclblCustom.insets = new Insets(0, 0, 5, 5);
    	gbclblCustom.anchor = GridBagConstraints.EAST;
    	gbclblCustom.gridx = 1;
    	gbclblCustom.gridy = 0;
    	add(lblCustom, gbclblCustom);
    	
    	txtCustomId = new JTextField();
    	var gbctxtCustomId = new GridBagConstraints();
    	gbctxtCustomId.insets = new Insets(0, 0, 5, 0);
    	gbctxtCustomId.fill = GridBagConstraints.HORIZONTAL;
    	gbctxtCustomId.gridx = 2;
    	gbctxtCustomId.gridy = 0;
    	add(txtCustomId, gbctxtCustomId);
    	txtCustomId.setColumns(10);
    	
    	var lblComment = new JLabel("Comment :");
    	GridBagConstraints gbclblComment = new GridBagConstraints();
    	gbclblComment.insets = new Insets(0, 0, 5, 5);
    	gbclblComment.anchor = GridBagConstraints.EAST;
    	gbclblComment.gridx = 1;
    	gbclblComment.gridy = 1;
    	add(lblComment, gbclblComment);
    	
    	txtComment = new JTextField();
    	var gbctxtComment = new GridBagConstraints();
    	gbctxtComment.insets = new Insets(0, 0, 5, 0);
    	gbctxtComment.fill = GridBagConstraints.HORIZONTAL;
    	gbctxtComment.gridx = 2;
    	gbctxtComment.gridy = 1;
    	add(txtComment, gbctxtComment);
    	txtComment.setColumns(10);
    	
    	var lblFinish = new JLabel("Finish :");
    	GridBagConstraints gbclblFinish = new GridBagConstraints();
    	gbclblFinish.insets = new Insets(0, 0, 5, 5);
    	gbclblFinish.anchor = GridBagConstraints.EAST;
    	gbclblFinish.gridx = 1;
    	gbclblFinish.gridy = 2;
    	add(lblFinish, gbclblFinish);
    	
    	cboFinish = new JComboBox<>();
    	var gbccboFinish = new GridBagConstraints();
    	gbccboFinish.insets = new Insets(0, 0, 5, 0);
    	gbccboFinish.fill = GridBagConstraints.HORIZONTAL;
    	gbccboFinish.gridx = 2;
    	gbccboFinish.gridy = 2;
    	add(cboFinish, gbccboFinish);
    	
    	var lblCondition = new JLabel("Condition :");
    	GridBagConstraints gbclblCondition = new GridBagConstraints();
    	gbclblCondition.insets = new Insets(0, 0, 5, 5);
    	gbclblCondition.anchor = GridBagConstraints.EAST;
    	gbclblCondition.gridx = 1;
    	gbclblCondition.gridy = 3;
    	add(lblCondition, gbclblCondition);
    	
    	cboCondition = new JComboBox<>();
    	var gbccboCondition = new GridBagConstraints();
    	gbccboCondition.insets = new Insets(0, 0, 5, 0);
    	gbccboCondition.fill = GridBagConstraints.HORIZONTAL;
    	gbccboCondition.gridx = 2;
    	gbccboCondition.gridy = 3;
    	add(cboCondition, gbccboCondition);
    	
    	var lblLang = new JLabel("Language :");
    	GridBagConstraints gbclblLang = new GridBagConstraints();
    	gbclblLang.insets = new Insets(0, 0, 5, 5);
    	gbclblLang.anchor = GridBagConstraints.EAST;
    	gbclblLang.gridx = 1;
    	gbclblLang.gridy = 4;
    	add(lblLang, gbclblLang);
    	
    	cboLang = new JComboBox<>();
    	var gbccboLang = new GridBagConstraints();
    	gbccboLang.insets = new Insets(0, 0, 5, 0);
    	gbccboLang.fill = GridBagConstraints.HORIZONTAL;
    	gbccboLang.gridx = 2;
    	gbccboLang.gridy = 4;
    	add(cboLang, gbccboLang);
    	
    	var lblQty = new JLabel("Quantity :");
    	var gbclblQty = new GridBagConstraints();
    	gbclblQty.anchor = GridBagConstraints.EAST;
    	gbclblQty.insets = new Insets(0, 0, 5, 5);
    	gbclblQty.gridx = 1;
    	gbclblQty.gridy = 5;
    	add(lblQty, gbclblQty);
    	
    	spnQty = new JSpinner();
    	spnQty.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
    	var gbcspnQty = new GridBagConstraints();
    	gbcspnQty.fill = GridBagConstraints.HORIZONTAL;
    	gbcspnQty.insets = new Insets(0, 0, 5, 0);
    	gbcspnQty.gridx = 2;
    	gbcspnQty.gridy = 5;
    	add(spnQty, gbcspnQty);
    	
    	chkForSale = new JCheckBox("For Sale ? ");
    	var gbcchkForSale = new GridBagConstraints();
    	gbcchkForSale.insets = new Insets(0, 0, 5, 0);
    	gbcchkForSale.gridx = 2;
    	gbcchkForSale.gridy = 6;
    	add(chkForSale, gbcchkForSale);
    	
    	var lblTags = new JLabel("Tags :");
    	var gbclblTags = new GridBagConstraints();
    	gbclblTags.anchor = GridBagConstraints.EAST;
    	gbclblTags.insets = new Insets(0, 0, 5, 5);
    	gbclblTags.gridx = 1;
    	gbclblTags.gridy = 7;
    	add(lblTags, gbclblTags);
    	
    	tagsModel = new DefaultListModel<>();
    	locationsModel = new DefaultListModel<>();
    
    	lstTags = new JList<>(tagsModel);
    	lstTags.setVisibleRowCount(3);
    	var gbclstTags = new GridBagConstraints();
    	gbclstTags.insets = new Insets(0, 0, 5, 0);
    	gbclstTags.fill = GridBagConstraints.BOTH;
    	gbclstTags.gridx = 2;
    	gbclstTags.gridy = 7;
    	add(new JScrollPane(lstTags), gbclstTags);
    	
    	var lblLocation = new JLabel("Location :");
    	var gbclblLocation = new GridBagConstraints();
    	gbclblLocation.anchor = GridBagConstraints.EAST;
    	gbclblLocation.insets = new Insets(0, 0, 0, 5);
    	gbclblLocation.gridx = 1;
    	gbclblLocation.gridy = 8;
    	add(lblLocation, gbclblLocation);
    	
    	lstLocation = new JList<>(locationsModel);
    	lstLocation.setVisibleRowCount(3);
    	lstLocation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	
    	var gbclstLocation = new GridBagConstraints();
    	gbclstLocation.fill = GridBagConstraints.BOTH;
    	gbclstLocation.gridx = 2;
    	gbclstLocation.gridy = 8;
    	add(new JScrollPane(lstLocation), gbclstLocation);
    	
	loadDatas();
    	
    }
    
    public void loadDatas() {
	var wk1 = new SwingWorker<List<String>, Void>() {
    	    @Override
    	    protected List<String> doInBackground() throws Exception {
    	       return new InventoryService().listTags().stream().map(i->i.name()).toList();
    	    }
    	    @Override
    	    protected void done() {
    		try {
    		    tagsModel.removeAllElements();
		    tagsModel.addAll(get());
		} catch (InterruptedException e) {
		   Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
		    logger.error(e);
		}
    	    }
    	};
    	wk1.execute();
    	
	var wk2 = new SwingWorker<List<String>, Void>() {
    	    @Override
    	    protected List<String> doInBackground() throws Exception {
    	       return new InventoryService().listLocations().stream().map(i->i.name()).toList();
    	    }
    	    @Override
    	    protected void done() {
    		try {
    		    locationsModel.removeAllElements();
		    locationsModel.addAll(get());
		} catch (InterruptedException e) {
		   Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
		    logger.error(e);
		}
    	    }
    	    
	};
	wk2.execute();
    
	
    }


    public InventoryLine getInventoryLine()
    {
	var cId = txtCustomId.getText().isEmpty() ? null:txtCustomId.getText();
	var comment = txtComment.getText().isEmpty() ? null:txtComment.getText();
	var location = lstLocation.getSelectedIndex()>-1? locationsModel.getElementAt(lstLocation.getSelectedIndex()):null;
	List<String> tags = lstTags.getSelectedIndex()>-1? lstTags.getSelectedValuesList():null;
	
	return new InventoryLine(null,cId, comment, product.getId(), product.getGameId(), cboFinish.getItemAt(cboFinish.getSelectedIndex()), cboCondition.getItemAt(cboCondition.getSelectedIndex()), cboLang.getItemAt(cboLang.getSelectedIndex()), (Integer)spnQty.getValue(), chkForSale.isSelected(), location, null, null, null, null, tags);
    }

    public void init(AbstractProduct selectedProduct) {

	this.product=selectedProduct;
	
	if(productPicturePanel!=null)
	    productPicturePanel.init(selectedProduct);
	
	
	cboFinish.removeAllItems();
	cboCondition.removeAllItems();
	cboLang.removeAllItems();
	
	((DefaultComboBoxModel<EnumCondition>)cboCondition.getModel()).addAll(List.of(EnumCondition.values()));
	((DefaultComboBoxModel<String>)cboLang.getModel()).addAll(selectedProduct.getLanguages());
	
	if(selectedProduct instanceof CardProduct c)
	    ((DefaultComboBoxModel<EnumFinishes>)cboFinish.getModel()).addAll(c.getFinishes());

    }
    
    
    
}

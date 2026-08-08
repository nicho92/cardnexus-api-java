package org.api.cardnexus.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.api.cardnexus.gui.model.TagLocationTableModel;
import org.api.cardnexus.model.IconableItem;
import org.api.cardnexus.services.InventoryService;

public class TagsAndLocationPanel extends JPanel {
	public TagsAndLocationPanel() {
	    	service = new InventoryService();
		setLayout(new BorderLayout(0, 0));
		model = new TagLocationTableModel();
		var buttonGroup = new ButtonGroup();
		
		var panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		var rdoTags = new JRadioButton("Tags");
		panel.add(rdoTags);
		
		var rdoLocation = new JRadioButton("Location");
		panel.add(rdoLocation);
		
		var table = new JTable(model);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		var panelcommand= new JPanel();
		add(panelcommand, BorderLayout.SOUTH);
		
		var btnAdd = new JButton("New");
		panelcommand.add(btnAdd);
		
		var btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		panelcommand.add(btnDelete);
		
		buttonGroup.add(rdoLocation);
		buttonGroup.add(rdoTags);
		
		
		rdoTags.addActionListener(_->{
		    
		    try {
			model.init(service.listTags());
		    } catch (IOException e) {
			logger.error(e);
		    }
		});

		rdoLocation.addActionListener(_->{
		    
		    try {
			model.init(service.listLocations());
		    } catch (IOException e) {
			logger.error(e);
		    }
		});
		
		table.getSelectionModel().addListSelectionListener(_->{
		    
		    int row = table.convertRowIndexToModel(table.getSelectedRow());
		    btnDelete.setEnabled(row>-1);
		});
		
		
		btnDelete.addActionListener(_->{
		    
		    int row = table.convertRowIndexToModel(table.getSelectedRow());
		    var id = model.getValueAt(row, 0).toString();
		    var isTags = rdoTags.isSelected();
		    var confirmation = JOptionPane.showConfirmDialog(this, "Delete " + (isTags?"Tag":"Location") + " " + id + " ?");
		    
		    if(confirmation==JOptionPane.YES_OPTION)
		    {
			
			var result = false;
			
			
			if(isTags)
			    try {
				result = service.deleteTag(id);
			    } catch (IOException e) {
				logger.error(e);
			    }
			else
			    try {
				result = service.deleteLocation(id);
			    } catch (IOException e) {
				logger.error(e);
			    }
			
			if(result)
			    model.removeRow(row);
			else
			    logger.error("can't delete " + id);
			
			
		    }
		    
		    
		    
		});
		
		
		
		btnAdd.addActionListener(_->{
		    
		    JPanel pane = new JPanel();
		    pane.setLayout(new GridLayout(4,2));
		    var txtName = new JTextField(20);
		    var txtColor = new JTextField(20);
		    var txtIcon = new JTextField(20);
		    var btnClose = new JButton("ok");
		    
		    
		    pane.add(new JLabel("Name"));
		    pane.add(txtName);
		    pane.add(new JLabel("Color"));
		    pane.add(txtColor);
		    pane.add(new JLabel("Icon"));
		    pane.add(txtIcon);
		    pane.add(new JSeparator());
		    pane.add(btnClose);
		    
		    
		    var diag = new JDialog();
		    diag.setLocationRelativeTo(this);
		    diag.getContentPane().add(pane);
		    diag.setModal(true);
		    diag.pack();
		    diag.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		    btnClose.addActionListener(_->{
			    var item = new IconableItem(txtName.getText(), txtColor.getText(), txtIcon.getText());
			    
			    try {
            			    if(rdoLocation.isSelected())
            			    {
            				    model.addItem( service.createLocation(item));
            			    }
            			    else
            			    {
            				    model.addItem( service.createTag(item));
            			    }
				} catch (IOException e) {
				    logger.error(e);
				}
			    	diag.dispose();
		    });
		    diag.setVisible(true);
		    
		
		});
		
		
		
		
	}	
	
	protected transient Logger logger = LogManager.getLogger(getClass());
	private InventoryService service;
	private static final long serialVersionUID = 1L;
	private TagLocationTableModel model;

}

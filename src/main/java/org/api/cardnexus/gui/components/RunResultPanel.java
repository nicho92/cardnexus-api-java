package org.api.cardnexus.gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.api.cardnexus.model.Run;

public class RunResultPanel extends JPanel {
    
    	private JLabel lblResults;
    	private JLabel lblStatus;
    	private DefaultMutableTreeNode root;
    	private JTree tree;
    	
    	
	public RunResultPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblResults = new JLabel("Results : ");
		var gbclblResults = new GridBagConstraints();
		gbclblResults.insets = new Insets(0, 0, 5, 0);
		gbclblResults.gridx = 0;
		gbclblResults.gridy = 0;
		add(lblResults, gbclblResults);
		
		lblStatus = new JLabel("Statut : ");
		var gbclblStatus = new GridBagConstraints();
		gbclblStatus.insets = new Insets(0, 0, 5, 0);
		gbclblStatus.gridx = 0;
		gbclblStatus.gridy = 1;
		add(lblStatus, gbclblStatus);
		
		
		root= new DefaultMutableTreeNode();
		var model = new DefaultTreeModel(root);
		tree = new JTree(model);
		
		var gbctree = new GridBagConstraints();
		gbctree.fill = GridBagConstraints.BOTH;
		gbctree.gridx = 0;
		gbctree.gridy = 2;
		add(tree, gbctree);
	}
    
   
    
    private static final long serialVersionUID = 1L;

    public void init(Run job) {
			
	lblStatus.setText("Status : " + job.status());
	lblResults.setText("Results : " + (job.hasResult()?"OK":"No Result"));
	
	root.removeAllChildren();
	
	
	if(job.hasResult())
	{
	    
	    for(var o : job.result().options())
	    {
		root.add(new DefaultMutableTreeNode(o));
	    }
	    
	    
	}
	
	
	
	
	tree.updateUI();
	
	
	
    }

}

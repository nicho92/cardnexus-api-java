package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import org.api.cardnexus.model.CardNexusPrice;
import org.api.cardnexus.model.MarketVariations;
import org.api.cardnexus.model.ProductPriceMarket;
import org.api.cardnexus.model.RegionData;
import org.api.cardnexus.model.enums.EnumFinishes;
import org.api.cardnexus.tools.Formatter;

public class MarketVariationPanel extends JPanel {
    	private JTree tree;
    	private DefaultTreeModel treeModel;
    	private DefaultMutableTreeNode root;
    	
    	
	public MarketVariationPanel() {
		setLayout(new BorderLayout(0, 0));
		root = new DefaultMutableTreeNode("Finishes");
		treeModel = new DefaultTreeModel(root);
		
		tree = new JTree(treeModel);
		add(new JScrollPane(tree));
	}

    
    private static final long serialVersionUID = 1L;

    
    private DefaultMutableTreeNode addMarketNode( MarketVariations v, String name)
    {
	    var nodeMarket = new DefaultMutableTreeNode(name);
	    nodeMarket.add(new DefaultMutableTreeNode("Change 24H ="+Formatter.format(v.change24h()), false));
	    nodeMarket.add(new DefaultMutableTreeNode("Change 30d ="+Formatter.format(v.change30d()), false));
	    nodeMarket.add(new DefaultMutableTreeNode("Change 7d ="+Formatter.format(v.change7d()), false));
	    nodeMarket.add(new DefaultMutableTreeNode("Low ="+Formatter.format(v.low()), false));
	    nodeMarket.add(new DefaultMutableTreeNode("Market Value ="+Formatter.format(v.marketValue()), false));
	    return nodeMarket;
    }
    
    private MutableTreeNode addMarketNode(CardNexusPrice v, String name) {
	  var nodeMarket = new DefaultMutableTreeNode(name);
	  	nodeMarket.add(new DefaultMutableTreeNode("Low ="+v.low(), false));
	  	nodeMarket.add(new DefaultMutableTreeNode("Available ="+v.availableQuantity(), false));
	  	nodeMarket.add(new DefaultMutableTreeNode("Listings ="+v.listingCount(), false));
	  	
	  	if(v.regions().eu() !=null)
	  	  nodeMarket.add(addRegionsData(v.regions().eu(),"EU"));
	  	
	  	if(v.regions().na() !=null)
	  	  nodeMarket.add(addRegionsData(v.regions().na(),"NA"));
	  	
	  return nodeMarket;
      }
       
    private MutableTreeNode addRegionsData(RegionData v, String name){
	
	var nodeRegion = new DefaultMutableTreeNode(name);
        	nodeRegion.add(new DefaultMutableTreeNode("Low ="+v.low(), false));
        	nodeRegion.add(new DefaultMutableTreeNode("Available ="+v.availableQuantity(), false));
        	nodeRegion.add(new DefaultMutableTreeNode("Listings ="+v.listingCount(), false));	
        	
        	var nodeConditions = new DefaultMutableTreeNode("Conditions",true);
        	nodeRegion.add(nodeConditions);
        	
        	v.byCondition().entrySet().forEach(e->{
        	    
        	    var conditionRoot = new DefaultMutableTreeNode(e.getKey());
        	    	conditionRoot.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode("Available ="+e.getValue().availableQuantity(), false)));
        	    	conditionRoot.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode("Listings ="+e.getValue().listingCount(), false)));
        	    	conditionRoot.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode("Low ="+e.getValue().low(), false)));
        	    nodeConditions.add(conditionRoot);
        	    
        	    var nodeLanguages= new DefaultMutableTreeNode("Languages",true);
        	    conditionRoot.add(nodeLanguages);
        	    e.getValue().byLanguage().entrySet().forEach(l->{
        		
        		var languageRoot = new DefaultMutableTreeNode(l.getKey());
        		nodeLanguages.add(languageRoot);
        		
        		languageRoot.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode("Available ="+l.getValue().availableQuantity(), false)));
        		languageRoot.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode("Listings ="+l.getValue().listingCount(), false)));
        		languageRoot.add(new DefaultMutableTreeNode(new DefaultMutableTreeNode("Low ="+l.getValue().low(), false)));
        		
        	    });
        	});
        	return nodeRegion;
    }
    
    
    public void init(Map<EnumFinishes, ProductPriceMarket> map)
    {
		root.removeAllChildren();
			
		map.entrySet().forEach(e->{
		    
		    var fNode = new DefaultMutableTreeNode(e.getKey());
		    	if(e.getValue().cardmarket()!=null)
		    	    	fNode.add(addMarketNode(e.getValue().cardmarket(), "CardMarket"));
		    
		    	if(e.getValue().cardmarket()!=null)
		    	    	fNode.add(addMarketNode(e.getValue().tcgplayer(), "TCGPlayer"));
		    	
		    	if(e.getValue().cardnexus()!=null)
		    	    	fNode.add(addMarketNode(e.getValue().cardnexus(), "CardNexus"));
		    	
		    	root.add(fNode);
		    	
		});
		
		treeModel.reload();
    }

  
    
    
}

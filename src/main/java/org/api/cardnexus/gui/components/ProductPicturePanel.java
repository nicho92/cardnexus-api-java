package org.api.cardnexus.gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;

public class ProductPicturePanel extends JPanel {
    
    protected transient Logger logger = LogManager.getLogger(getClass());

    private static final long serialVersionUID = 1L;
    private JLabel lblImage;


	public ProductPicturePanel() {
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
		
	
	}

    public void init(AbstractProduct p) {
	
	try {
	    
	    var wk = new SwingWorker<BufferedImage, Void>()
		    {

			@Override
			protected BufferedImage doInBackground() throws Exception {
			    return ImageIO.read(URI.create(p.getImageUrl()).toURL());
			}
			@Override
			protected void done() {
			    try {
				var img = get();
				var rimg =  img.getScaledInstance(img.getWidth()/3, img.getHeight()/3, Image.SCALE_SMOOTH);
				lblImage.setIcon(new ImageIcon(rimg));
			    } catch (InterruptedException _) {
				Thread.currentThread().interrupt();
			    } catch (ExecutionException e) {
				logger.error(e);
			    }
			  
			}
			
		    };
	    
	    wk.execute();
	    
	    
	} catch (Exception e) {
	   logger.error(e);
	}
	
	
    }

}

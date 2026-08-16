package org.api.cardnexus.gui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;

public class ProductPicturePanel extends JPanel {
    
    protected transient Logger logger = LogManager.getLogger(getClass());

    private static final long serialVersionUID = 1L;
    private Image image;

    public ProductPicturePanel() {
        setOpaque(true);
        
        setPreferredSize(new Dimension(330,456));
        
        
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        if (imageWidth <= 0 || imageHeight <= 0) {
            return;
        }

        // Calcul du ratio pour conserver les proportions
        double scale = Math.min(
                (double) panelWidth / imageWidth,
                (double) panelHeight / imageHeight
        );

        int newWidth = (int) (imageWidth * scale);
        int newHeight = (int) (imageHeight * scale);

        // Centrage de l'image
        int x = (panelWidth - newWidth) / 2;
        int y = (panelHeight - newHeight) / 2;

        var g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );

        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        g2.drawImage(
                image,
                x, y,
                newWidth, newHeight,
                this
        );

        g2.dispose();
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
				image= get();
			    } catch (InterruptedException _) {
				Thread.currentThread().interrupt();
			    } catch (ExecutionException e) {
				logger.trace(e);
				image=null;
			    }
			    revalidate();
			    repaint();
			  
			}
			
		    };
	    
	    wk.execute();
	    
	    
	} catch (Exception e) {
	   logger.error(e);
	}
	
	
    }

}

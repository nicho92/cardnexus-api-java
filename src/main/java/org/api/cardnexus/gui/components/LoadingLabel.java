package org.api.cardnexus.gui.components;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LoadingLabel extends JLabel {

    private static final long serialVersionUID = 1L;
    
    public LoadingLabel() {
	
	
	setVisible(false);
	try {
	    setIcon(new ImageIcon(ImageIO.read( this.getClass().getResource( "/load.gif" ))));
	} catch (IOException _) {
	    //do nothing
	}
    }
    
    
    
    
}

package org.cardnexus.tests.gui;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.NexusProductPanel;

public class GUITester {

    

    public static void main(String[] args) throws IOException {
	
	NexusConfig.loadTokenFromEnv();
	NexusConfig.setDefaultGameValue("mtg");
	
	var f = new JFrame();
	
	f.getContentPane().add(new NexusProductPanel(true,true,true));
	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	f.pack();
	f.setVisible(true);
    }
    
}

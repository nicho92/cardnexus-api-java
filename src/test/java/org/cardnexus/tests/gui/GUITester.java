package org.cardnexus.tests.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;

import org.api.cardnexus.configuration.NexusConfig;
import org.api.cardnexus.gui.NexusAccountPanel;
import org.api.cardnexus.gui.NexusProductPanel;
import org.api.cardnexus.model.Game;
import org.api.cardnexus.services.ProductsService;
import org.api.cardnexus.tools.Utils;

public class GUITester {

    

    public static void main(String[] args) throws IOException {
	NexusConfig.loadTokenFromEnv();
	
	
	var dialog = new JDialog();
	      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	      dialog.setModal(true);
	      dialog.setLayout(new BorderLayout());
	      dialog.setLocationRelativeTo(null);
	      
	      
	      var model = new DefaultComboBoxModel<Game>();
	      model.addAll(new ProductsService().listGames());
	      
	      var cboGames = new JComboBox<>(model);
	      
	      cboGames.setRenderer(new ListCellRenderer<Game>() {

		@Override
		public Component getListCellRendererComponent(JList<? extends Game> list, Game value, int index,boolean isSelected, boolean cellHasFocus) {
		    if(value!=null)
			return new DefaultListCellRenderer().getListCellRendererComponent(list, value.name(), index, isSelected, cellHasFocus);
		    else
			return new JLabel("Choose a Game");
		}
	    });
	      
	      var btnOK = new JButton("OK");
	      	dialog.getContentPane().add(cboGames,BorderLayout.CENTER);
	      	dialog.getContentPane().add(btnOK,BorderLayout.SOUTH);
	      	
	      	
	      btnOK.addActionListener(_->{
		  NexusConfig.setDefaultGameValue(model.getElementAt(cboGames.getSelectedIndex()).id());
		  dialog.dispose();
	      });
	      
	      dialog.pack();
	      dialog.setVisible(true);
	
        	var f = new JFrame();
        	f.setIconImage(Utils.getNexusImage());
        	f.getContentPane().add(new NexusAccountPanel());
        	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        	f.pack();
        	f.setVisible(true);
    }
    
}

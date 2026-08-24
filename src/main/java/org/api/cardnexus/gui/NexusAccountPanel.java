package org.api.cardnexus.gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.gui.components.SellerPanel;
import org.api.cardnexus.model.Account;
import org.api.cardnexus.model.Balance;
import org.api.cardnexus.model.Vacation;
import org.api.cardnexus.model.enums.EnumAccountStatus;
import org.api.cardnexus.model.enums.EnumScopes;
import org.api.cardnexus.services.AccountService;
import org.api.cardnexus.tools.Utils;

public class NexusAccountPanel extends JPanel {
    
    protected transient Logger logger = LogManager.getLogger(getClass());
    private DefaultListModel<EnumScopes> modelScope;
    private JLabel lblAvailable;
    private JLabel lblPending;

    private static final long serialVersionUID = 1L;
    private JTextField txtID;
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtCreated;
    private JLabel lblAvatar;
    private JCheckBox chkVacation;
    private transient AccountService service;
    private SellerPanel sellerPanel; 
    
    public NexusAccountPanel() {
	    
	   service = new AccountService();
	    
	    initGUI();
	    
	    try {
		init(service.getAccount(), service.getVacationMode(),service.getWalletBalance());
	    } catch (IOException e) {
		logger.error(e);
	    }
	}
	
	public void init(Account account, Vacation vacation, Balance balance) {
	    txtID.setText(account.id());
	    txtUsername.setText(account.username());
	    txtEmail.setText(account.email());
	    txtCreated.setText(Utils.format(account.createdAt(),true));
	    
	    
	    chkVacation.setSelected(vacation.status()!=EnumAccountStatus.active);
	    
	    
	    lblAvailable.setText("Available : " + balance.available());
	    lblPending.setText("Pending : " + balance.pending());
	    
	    modelScope.removeAllElements();
	    modelScope.addAll(account.scopes());
	    
	    sellerPanel.setSeller(account.seller());
	    
	    chkVacation.addItemListener((ItemEvent e) -> {
		
		var input="";
		
		if(e.getStateChange()==ItemEvent.SELECTED)
		    input = JOptionPane.showInputDialog(this, "Vacation Message ? ");
		
		try {
		    service.setVacationMode(e.getStateChange()==ItemEvent.SELECTED, input);
		} catch (IOException e1) {
		   logger.error(e1);
		}
	        
	    });
	    
	    
	    var wk = new SwingWorker<BufferedImage, Void>()
	    {

		@Override
		protected BufferedImage doInBackground() throws Exception {
		    return ImageIO.read(URI.create(account.imageUrl()).toURL());
		}
		@Override
		protected void done() {
		    try {
			
			lblAvatar.setIcon(new ImageIcon(get().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		    } catch (InterruptedException _) {
			Thread.currentThread().interrupt();
		    } catch (ExecutionException e) {
			logger.error(e);
		    }
		}
		
	    };
	    wk.execute();
	}

	private void initGUI()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{79, 347, 69, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblAvatar = new JLabel(" ");
		GridBagConstraints gbc_lblAvatar = new GridBagConstraints();
		gbc_lblAvatar.gridheight = 3;
		gbc_lblAvatar.insets = new Insets(0, 0, 5, 0);
		gbc_lblAvatar.gridx = 2;
		gbc_lblAvatar.gridy = 0;
		add(lblAvatar, gbc_lblAvatar);
		
		JLabel lblNewLabel = new JLabel("ID :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		GridBagConstraints gbc_txtID = new GridBagConstraints();
		gbc_txtID.insets = new Insets(0, 0, 5, 5);
		gbc_txtID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtID.gridx = 1;
		gbc_txtID.gridy = 0;
		add(txtID, gbc_txtID);
		txtID.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 1;
		add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 2;
		add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblCreated = new JLabel("Created :");
		GridBagConstraints gbc_lblCreated = new GridBagConstraints();
		gbc_lblCreated.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreated.anchor = GridBagConstraints.EAST;
		gbc_lblCreated.gridx = 0;
		gbc_lblCreated.gridy = 3;
		add(lblCreated, gbc_lblCreated);
		
		txtCreated = new JTextField();
		txtCreated.setEditable(false);
		GridBagConstraints gbc_txtCreated = new GridBagConstraints();
		gbc_txtCreated.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreated.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreated.gridx = 1;
		gbc_txtCreated.gridy = 3;
		add(txtCreated, gbc_txtCreated);
		txtCreated.setColumns(10);
		
		JLabel lblScopes = new JLabel("Scopes :");
		GridBagConstraints gbc_lblScopes = new GridBagConstraints();
		gbc_lblScopes.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblScopes.insets = new Insets(0, 0, 5, 5);
		gbc_lblScopes.gridx = 0;
		gbc_lblScopes.gridy = 4;
		add(lblScopes, gbc_lblScopes);
		
		modelScope = new DefaultListModel<EnumScopes>();
		var lstScops = new JList<>(modelScope);
		GridBagConstraints gbc_lstScops = new GridBagConstraints();
		gbc_lstScops.insets = new Insets(0, 0, 5, 5);
		gbc_lstScops.fill = GridBagConstraints.BOTH;
		gbc_lstScops.gridx = 1;
		gbc_lstScops.gridy = 4;
		add(new JScrollPane(lstScops), gbc_lstScops);
		
		chkVacation = new JCheckBox("Vacation");
		GridBagConstraints gbc_chkVacation = new GridBagConstraints();
		gbc_chkVacation.insets = new Insets(0, 0, 5, 0);
		gbc_chkVacation.gridx = 2;
		gbc_chkVacation.gridy = 4;
		add(chkVacation, gbc_chkVacation);
		
		JLabel lblBalance = new JLabel("Balance :");
		GridBagConstraints gbc_lblBalance = new GridBagConstraints();
		gbc_lblBalance.anchor = GridBagConstraints.EAST;
		gbc_lblBalance.insets = new Insets(0, 0, 0, 5);
		gbc_lblBalance.gridx = 0;
		gbc_lblBalance.gridy = 5;
		add(lblBalance, gbc_lblBalance);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;
		add(panel, gbc_panel);
		
		lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblAvailable);
		
		lblPending = new JLabel("Pending");
		lblPending.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblPending);
		
		sellerPanel = new SellerPanel();
		sellerPanel.setBorder(new TitledBorder(null, "Seller : ", TitledBorder.LEFT, TitledBorder.TOP, null));
		GridBagConstraints gbc_sellerPanel = new GridBagConstraints();
		gbc_sellerPanel.fill = GridBagConstraints.BOTH;
		gbc_sellerPanel.gridx = 2;
		gbc_sellerPanel.gridy = 5;
		add(sellerPanel, gbc_sellerPanel);
	}
	

}

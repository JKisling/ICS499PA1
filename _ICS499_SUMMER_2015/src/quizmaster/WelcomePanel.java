package quizmaster;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import core.Config;
import core.Growler;

public class WelcomePanel extends JPanel {
	
	/**
     * The panel for the Welcome tab of the GUI for the Quiz Master.
     * Defines the components included in the Welcome tab of the Quiz Master GUI.
	 */
	
	private static final long serialVersionUID = 1L;
	private JButton btnLevel1;
	private JButton btnLevel2;
	private JButton btnLevel3;
	private Color bgcolor;
	private String logoImage;
	
	public WelcomePanel(Color color)
	{
		bgcolor = color;
		String applicationDir = new String("");
		
		try {
			URL url = getClass().getResource("").toURI().toURL();
			applicationDir = url.getPath();
		} catch (MalformedURLException | URISyntaxException e) {			
			e.printStackTrace();
		}
		
		//logoImage = applicationDir + "\\" + Config.LOGO_FILE;
		//System.out.println(logoImage);
		logoImage = Config.IMAGE_DIR + Config.LOGO_FILE;
		
		initComponents();
	}
	
	private void initComponents() 
	{

		// Create color		
		setBackground(bgcolor);	
				
		// Create main panel
		setLayout(new BorderLayout());
		
		// Create logo image
		JPanel pnlLogo = new JPanel(new FlowLayout(FlowLayout.LEADING));
		pnlLogo.setBackground(bgcolor);	
	    ImageIcon iiLogo = new ImageIcon(logoImage);    
		JLabel lblLogo = new JLabel(iiLogo);
		pnlLogo.add(lblLogo);
		add(pnlLogo, BorderLayout.WEST);
		
		// Create title
		JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlTitle.setBackground(bgcolor);
		
		pnlTitle.setBorder(new EmptyBorder(0, 200, 0, 0));
		JLabel title = new JLabel(Config.WELCOME_TITLE);
		title.setFont(Config.WELCOME_FONT);
		pnlTitle.add(title);
		add(pnlTitle, BorderLayout.NORTH);
		
		// Create welcome message
		JTextArea message = new JTextArea(Config.WELCOME_MSG);
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setFont(Config.WELCOME_FONT);
		message.setEditable(false);
		message.setBackground(bgcolor);
		add(message, BorderLayout.CENTER);
		
		// Create blank panel
		JPanel pnlBlank = new JPanel();
		pnlBlank.setBackground(bgcolor);
		pnlBlank.setBorder(new EmptyBorder(0, 250, 0, 0));
		add(pnlBlank, BorderLayout.EAST);
		
		// Create button panel
		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(bgcolor);
		
		
		//btnLevel1 = new JButton(Config.ICON_LEVEL_1);
		String level1_txt = "<html><center>"+"1. Sub juniors"+"<br>"+"అక్షరమాల" +"</center></html>";
		btnLevel1 = new JButton(level1_txt);
		btnLevel1.setBackground(Color.LIGHT_GRAY);
		btnLevel1.setFont(Config.TELUGU_FONT.deriveFont(45.0f));
		btnLevel1.setPreferredSize(new Dimension(Config.BTN_WIDTH + 100, Config.BTN_HEIGHT));
		
		//btnLevel2 = new JButton(Config.ICON_LEVEL_2);
		String level2_txt = "<html><center>"+"2. Juniors"+"<br>"+"గుణింతాలు" +"</center></html>";
		btnLevel2 = new JButton(level2_txt);
		btnLevel2.setBackground(Color.LIGHT_GRAY);
		btnLevel2.setFont(Config.TELUGU_FONT.deriveFont(45.0f));
		btnLevel2.setPreferredSize(new Dimension(Config.BTN_WIDTH + 100, Config.BTN_HEIGHT));
		
		//btnLevel3 = new JButton(Config.ICON_LEVEL_3);
		String level3_txt = "<html><center>"+"3. Seniors"+"<br>"+"సంయుక్తాక్షరాలు " +"</center></html>";
		btnLevel3 = new JButton(level3_txt);
		btnLevel3.setBackground(Color.LIGHT_GRAY);
		btnLevel3.setFont(Config.TELUGU_FONT.deriveFont(45.0f));
		btnLevel3.setPreferredSize(new Dimension(Config.BTN_WIDTH + 100, Config.BTN_HEIGHT));
		

		
		//Add actionListener to button
		btnLevel1.addActionListener(new LevelAction(1));
		btnLevel2.addActionListener(new LevelAction(2));
		btnLevel3.addActionListener(new LevelAction(3));
		
		// Add buttons to pnlButton
		pnlButton.add(btnLevel1);
		pnlButton.add(btnLevel2);
		pnlButton.add(btnLevel3);
		
		// Add pnlButton
		add(pnlButton, BorderLayout.SOUTH);	
		
		// this is the default button when the application is started
		btnLevel1.setSelected(true);
		
	}
	// the actions for each level
	public class LevelAction implements ActionListener{
			
		int level;
			
		public LevelAction(int i) {
			level = i;
		}

			public void actionPerformed(ActionEvent arg0) {
				btnLevel1.setBackground(Color.LIGHT_GRAY);
				btnLevel2.setBackground(Color.LIGHT_GRAY);
				btnLevel3.setBackground(Color.LIGHT_GRAY);
				
				if (level == 1) btnLevel1.setBackground(Color.GRAY);
				else if (level == 2) btnLevel2.setBackground(Color.GRAY);
				else if (level == 3) btnLevel3.setBackground(Color.GRAY);
				
				Config.gameCollection = Config.entireCollection.getBigWordCollectionByLevel(level);	
				System.out.println(Config.gameCollection.size());
				Config.gameCollection.removeDuplicates();
				System.out.println(Config.gameCollection.size());
				Growler.showMessage("Level " + level + " is now set for the quiz");
			}
			
		}
	
	

}

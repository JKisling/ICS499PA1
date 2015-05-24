package quizmaster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Audio;
import core.BWCIterator;
import core.BigWord;
import core.Config;
import core.Growler;
import core.ImageUtils;

class VocabularyPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	boolean checkingMode = false;
	
	BWCIterator iterator;
	BigWord currentWord;
	
	JTextField englishTextField;
	JTextField teluguTextField;
	JTextField topicField;
	
	JPanel imagePanel;
	JLabel imageLabel;
	
	JButton btnSound;
	JButton btnNext;
	JButton btnBack;
	
	/**
	 * Set the iterator and initialize the panel.
	 */
	public VocabularyPanel(Color color)
	{
		this.setLayout(null);
		setSize( 1200, 800 );
		setBackground(color);
		JLabel lblNewLabel = new JLabel(); //new JLabel("Topic");
		lblNewLabel.setBounds(593, 48, 93, 20);
		add(lblNewLabel);

		//the textField for the topic
		topicField = new JTextField();
		topicField.setBounds(705, 45, 350, 30);
		topicField.setEditable(false);
		topicField.setHorizontalAlignment(JTextField.CENTER);
		add(topicField);
		topicField.setColumns(10);
		//Displays the English text associated with currentWord
		englishTextField = new JTextField();
		englishTextField.setBounds(705, 194, 350, 60);
		englishTextField.setBackground(Color.WHITE);
		englishTextField.setHorizontalAlignment(JTextField.CENTER);
		englishTextField.setBorder(BorderFactory.createEmptyBorder());
		englishTextField.setEditable(false);
		englishTextField.setFont(new Font(Config.FONT_NAME, Font.PLAIN, 50));
		add(englishTextField);

		//Displays the Telugu text associated with currentWord uses Gautami as font currently since Gidugu isnt working properly
		teluguTextField = new JTextField();
		teluguTextField.setBounds(705, 363, 350, 79);
		teluguTextField.setBackground(Color.WHITE);
		teluguTextField.setHorizontalAlignment(JTextField.CENTER);
		teluguTextField.setBorder(BorderFactory.createEmptyBorder());
		teluguTextField.setEditable(false);
		teluguTextField.setFont(Config.TELUGU_FONT.deriveFont(50.0f));
		add(teluguTextField);

		
	
		// Back Button
		btnBack = new JButton(Config.ICON_PREVIOUS);
		btnBack.setBackground(Color.BLUE);
		btnBack.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
		btnBack.setBounds(200, 500, 200, 150);
		add(btnBack);

		// Sound Button acting as Check Button
	//	btnSound = new JButton(Config.ICON_SOUND);
		btnSound = new JButton(Config.ICON_CHECK);
		btnSound.setBackground(Color.BLUE);
		btnSound.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
		btnSound.setBounds(500, 500, 200, 150);
		add(btnSound);
	
		// Next Button
		btnNext = new JButton(Config.ICON_NEXT);
		btnNext.setBackground(Color.BLUE);
		btnNext.setPreferredSize(new Dimension(Config.BTN_WIDTH, Config.BTN_HEIGHT));
		btnNext.setBounds(750, 500, 200, 150);
		add(btnNext);

		//Image Panel
		imagePanel = new JPanel();
		imagePanel.setBounds(85, 48, 453, 418);
		imagePanel.setBackground(Color.WHITE);
		imageLabel = new JLabel();
		imageLabel.setBackground(Color.WHITE);
		imageLabel.setBounds(0, 0, 453, 418);
		imagePanel.add(imageLabel);
		add(imagePanel);
		
		
		//Sets the currentWord to the next item in the list and updates the GUI
		btnNext.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iterator.hasNext())
					currentWord = iterator.getNext();
				else
					Growler.showMessage("There is no next word");

				checkingMode = false;
				updateGUI();
			}
		});
		
		
		
		/*When clicked, this button attempts to play the sound file associated with the BigWord.
		 * 
		 * For now catch all errors in a blanket and play the No Sound file
		 */
		btnSound.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					String audio_name = iterator.getCurrent().getSound();
					Audio audio = new Audio(Config.SOUND_DIR + audio_name);
				} catch (Exception e1) {
					try {
						Audio audio = new Audio(Config.DEFAULT_SOUND);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
				checkingMode = true;
				updateGUI();
			}
		});
		
		
		
		//Sets the currentWord to the previous item in the list and updates the GUI
		btnBack.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (iterator.hasPrevious())
					currentWord = iterator.getPrevious();
				else
					Growler.showMessage("There is no previous word");
		
				checkingMode = false;
				updateGUI();
			}
		});
	

		//when previewpanel method becomes visible
		//get the latest gamecollection
		this.addComponentListener(new ComponentAdapter(){
			public void componentShown ( ComponentEvent e )
	        {
				iterator = new BWCIterator(Config.gameCollection);
				updateGUI();
	        }
		});

	}


	/**
	 * Updates the dynamic GUI variables when called
	 */
	public void updateGUI()
	{
		currentWord = iterator.getCurrent();
		topicField.setText(currentWord.getTopic());
		englishTextField.setText(currentWord.getEnglish());
		if (checkingMode)
		    teluguTextField.setText(currentWord.getTelugu());
		else
			teluguTextField.setText("");

		//sets the btnSound to false unless the following conditions are met
	//	btnSound.setEnabled(false);
	//	boolean canExecute = new File(Config.SOUND_DIR + "\\" + currentWord.getSound()).canExecute();
	//	boolean isBlank = currentWord.getSound().trim().equals("");
	//	if(canExecute && !isBlank)
			btnSound.setEnabled(true);
			

		//Set the image of currentWord to the image associated with currentWord. Otherwise loads the defailt image if 
		//currentWord has no image or the image is bad.
		try
		{					
			imageLabel.setIcon(new ImageIcon(ImageUtils.getImage(currentWord.getImage())));

		}catch(Exception e)
		{	
			System.out.println("Failed to get the image");
		}
		// whenever the UI is updated, we also play the sound
		// this will cover -- next, back and switching to the tab
		//btnSound.doClick();
	}
	
}



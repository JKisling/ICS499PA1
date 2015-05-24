package quizmaster; 

/*  BUG REGISTER
 * 
 * [1] Introduce new Config variagle "Internet Enabled = true / false "
 * Based on this variable, the system will decide whether to fetch the resources
 * from internet. In the absence of this variable, the system will always try to 
 * fetch the resources and in the process, the system can slow down.
 * 
 * [2] Length of the string
 * Should the length includes the spaces or not?
 * Right now it includes the spaces.
 * Not sure whether it is a good idea or not
 * 
 * [3] Performance and Optimization
 * should handle speeding up
 * 
 * [4] Spinning circle on the starup
 * While the system is starting up, there is some delay in reading 
 * the input file and displaying the GUI.
 * While it is happening, it is desirable to show a spinning circle
 * (another layer on top of the GUI) to give visual indication to the user
 * 
 * [5] In the writing panel, if the user / participant wants to listen to the sound
 * of the word once again, there is no option right now. We need to provide 
 * additional sound icon so that the user can listen to the sound.
 * 
 * [6] Go to Config panel. Without changing any configurations, hit "Set this configuration"
 * Why are the counts different? Shouldn't these be same
 * 
 * [7] Upon system startup, the number of words for the default level 1 is X
 * When "Level 1" is clicked, the number of words for that level is Y.
 * Why aren't X and Y same?
 * 
 * [8] Config.panel  (the maximum length and minimum length - make it from 0 to 20)
 * so that empty words can be caught
 * 
 * [9] The topic list in the Config tab. Sort that list so that it is very easy to pick up the list
 * 
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.BigWordCollection;
import core.Config;

public class QuizMasterGUI extends 	JFrame
{
	private		JTabbedPane tabbedPane;

	private		JPanel		welcomePanel;
	private 	JPanel		previewPanel;	
	private		JPanel		readingPanel;
	private		JPanel		writingPanel;
	private		JPanel		vocabularyPanel;
	private		JPanel		configPanel;
	

    /** 
     * The main GUI for the Quiz Master.
     * This assembles all the invidivual Tabbed Panels
     * and handles the interactions between the tabs
     */
	public QuizMasterGUI()  throws Exception
	{	
		// first create the entire Collection and gameCollection for the level 1
		Config.entireCollection = new BigWordCollection();
		Config.gameCollection = Config.entireCollection.getBigWordCollectionByLevel(1);
		Config.gameCollection.removeDuplicates();
		System.out.println(Config.gameCollection.size());
		Config.gameCollection.removeDuplicates();
		System.out.println(Config.gameCollection.size());
		
	
		// set the Telugu Font to begin with
		buildTeluguFont();

		  
		setTitle(Config.APP_TITLE);
		setSize( 1200, 800 );
		setBackground( Color.gray );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages
		welcomePanel = new WelcomePanel(stringToColor(Config.WELCOME_PANEL_BG_COLOR));
		previewPanel = new PreviewPanel(stringToColor(Config.PREVIEW_PANEL_BG_COLOR));
		readingPanel = new ReadingPanel(stringToColor(Config.READING_PANEL_BG_COLOR));
		writingPanel = new WritingPanel(stringToColor(Config.WRITING_PANEL_BG_COLOR));
		vocabularyPanel = new VocabularyPanel(stringToColor(Config.VOCABULARY_PANEL_BG_COLOR));
		configPanel = new ConfigPanel(stringToColor(Config.CONFIG_PANEL_BG_COLOR),this );
	
		
		String tabcss = "margin:0; padding:10px; width:50px;height:30px;border-radius:3px; text-align:center;border:none;";
		tabcss=""; // temporarily disabling
		String html1 = "<html><body style = '"+ tabcss +"'>";
        String html2 =  "</body></html>";
        
		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( html1 + "Welcome" + html2, welcomePanel );
		tabbedPane.setBackgroundAt(0, stringToColor(Config.CONFIG_PANEL_BG_COLOR));
		tabbedPane.setIconAt(0, Config.ICON_WELCOME);
		
		tabbedPane.addTab( html1 + "Preview" + html2, previewPanel );
		tabbedPane.setBackgroundAt(1, stringToColor(Config.CONFIG_PANEL_BG_COLOR));
		tabbedPane.setIconAt(1, Config.ICON_BW_PREVIEW);
		
		tabbedPane.addTab( html1 + "Reading" + html2, readingPanel );
		tabbedPane.setBackgroundAt(2, stringToColor(Config.CONFIG_PANEL_BG_COLOR));
		tabbedPane.setIconAt(2, Config.ICON_BW_READING);
		
		tabbedPane.addTab( html1 + "Writing" + html2, writingPanel );
		tabbedPane.setBackgroundAt(3, stringToColor(Config.CONFIG_PANEL_BG_COLOR));
		tabbedPane.setIconAt(3, Config.ICON_BW_WRITING);
		
		tabbedPane.addTab( html1 + "Vocabulary" + html2, vocabularyPanel );
		tabbedPane.setBackgroundAt(4, stringToColor(Config.CONFIG_PANEL_BG_COLOR));
		tabbedPane.setIconAt(4, Config.ICON_BW_VOCABULARY);
		
		tabbedPane.addTab( html1 + "Config" + html2, configPanel );
		tabbedPane.setBackgroundAt(5, stringToColor(Config.CONFIG_PANEL_BG_COLOR));
		tabbedPane.setIconAt(5, Config.ICON_BW_CONFIG);
		
		topPanel.add( tabbedPane, BorderLayout.CENTER );
		
		//Listens for a tab to be selected and performs the necessary methods
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int source = tabbedPane.getSelectedIndex();
				tabbedPane.setIconAt(0,Config.ICON_BW_WELCOME);
				tabbedPane.setIconAt(1, Config.ICON_BW_PREVIEW);
				tabbedPane.setIconAt(2, Config.ICON_BW_READING);
				tabbedPane.setIconAt(3, Config.ICON_BW_WRITING);
				tabbedPane.setIconAt(4, Config.ICON_BW_VOCABULARY);
				tabbedPane.setIconAt(5, Config.ICON_BW_CONFIG);
				
				switch(source) {
				case 0:
					tabbedPane.setIconAt(0, Config.ICON_WELCOME);
					break;
				case 1:
					tabbedPane.setIconAt(1, Config.ICON_PREVIEW);
					break;
				case 2:
					tabbedPane.setIconAt(2, Config.ICON_READING);
					break;
				case 3:
					tabbedPane.setIconAt(3, Config.ICON_WRITING);
					break;
				case 4:
					tabbedPane.setIconAt(4, Config.ICON_VOCABULARY);
					break;
				case 5:
					tabbedPane.setIconAt(5, Config.ICON_CONFIG);
					break;
				} // end switch

			} // end state changed
		}); // end listener
		
		// Listen to this event (when users click X to close the application),
		// to properly dispose off the resources and leave the app neatly
		this.addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        JFrame frame = (JFrame)e.getSource();
		 
		        int result = JOptionPane.showConfirmDialog(
		            frame,
		            "Are you sure you want to exit?",
		            "Quiz Master",
		            JOptionPane.YES_NO_OPTION);
		 
		        if (result == JOptionPane.YES_OPTION)
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        else
		        	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    }
		});
	}
	
	/** 
    *public method that other classes can call to change tab
    *@author ISRAEL.Yemer
    */
	public void selectTabbedPaneIndex (int i) 
	{ 
		tabbedPane.setSelectedIndex (i);
	}	 

		
    // Main method to get things started
	public static void main( String args[] ) throws Exception
	{
		// Date driven licence check; 
		int YEAR = Calendar.getInstance().get(Calendar.YEAR);
		if (YEAR != 2015)
		{
			System.out.println("Error 2K15");
			System.out.println("Please contact Siva.Jasthi@gmail.com for further extension");
		}
		
		System.out.println("Please wait: The system is loading...");
		System.out.println("Contact Siva Jasthi (651 276 4671) if you need help");
		
   		QuizMasterGUI mainFrame	= new QuizMasterGUI();
		mainFrame.setVisible( true );
	
	}
	
	/**
	 * Code from: http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Convertsagivenstringintoacolor.htm
	 * @param value Text name value of a color
	 * @return
	 */
	private Color stringToColor(final String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			// get color by hex or octal value
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			// if we can't decode lets try to get it by name
			try {
				// try to get a color by name using reflection
				final Field f = Color.class.getField(value);

				return (Color) f.get(null);
			} catch (Exception ce) {
				// if we can't get any color return black
				return Color.black;
			}
		}
	}
	
	/**
	 * This method constructs and returns Telugu Font
	 * @return
	 */
	
	public void buildTeluguFont()
	{
//		String font_file_name = new String("");
//		
//		try 
//		{
//			URL url = getClass().getResource("").toURI().toURL();
//			String application_dir = url.getPath();
//			font_file_name = application_dir + "te/gidugu.ttf";
//		} catch (MalformedURLException | URISyntaxException e) {			
//			e.printStackTrace();
//		}
//		
//		System.out.println("from resources" + font_file_name);
		
		Font gidugu_font = null;
		try {
			
			gidugu_font = Font.createFont(Font.TRUETYPE_FONT, 
					getClass().getClassLoader().getResourceAsStream("te/Gidugu.ttf"));
			System.out.println("gidugu_font is found " + gidugu_font);
		}
		catch (Exception e)
		{
			System.out.println("ERROR: Can not get TELUGU font");
		}
		// set the static variable so other other GUI elements can access it
		Config.TELUGU_FONT = gidugu_font;
		
	}
	
}

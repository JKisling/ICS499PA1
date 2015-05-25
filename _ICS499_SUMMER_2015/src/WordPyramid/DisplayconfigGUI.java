package WordPyramid;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import wp_display.CategorySelection;
import wp_display.CreatePyramid;
import wp_display.LanguageSelection;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class DisplayconfigGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMax;
	private JTextField txtMin;
	public DisplayconfigGUI() {
		setTitle("Word Pyramid Creator");
		setSize(500,400);							//Set default frame size
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);			//launch window in center of screen
		
		
		JButton btnCreatePyramid = new JButton("Create Pyramid");
		btnCreatePyramid.setBounds(10, 226, 134, 23);
		getContentPane().add(btnCreatePyramid);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(LanguageSelection.values()));
		comboBox.setToolTipText("");
		comboBox.setEditable(true);
		comboBox.setBounds(10, 45, 107, 20);
		getContentPane().add(comboBox);
		
		JLabel lblLanguageSelection = new JLabel("Language Selection");
		lblLanguageSelection.setBounds(10, 11, 107, 23);
		getContentPane().add(lblLanguageSelection);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(204, 11, 53, 23);
		getContentPane().add(lblCategory);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(CategorySelection.values()));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(204, 45, 220, 20);
		getContentPane().add(comboBox_1);
		
		JLabel lblSetMaxWord = new JLabel("Set max word size");
		lblSetMaxWord.setBounds(10, 124, 134, 23);
		getContentPane().add(lblSetMaxWord);
		
		txtMax = new JTextField();
		txtMax.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(Integer.parseInt(txtMax.getText()) > WP_Config.maxWordSize )
					JOptionPane.showMessageDialog(null, "Max word size is "+WP_Config.maxWordSize+" characters or smaller.");
			}
		});
		txtMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMax.setText("");
			}
		});
		txtMax.setText("Max");
		txtMax.setBounds(10, 158, 53, 30);
		getContentPane().add(txtMax);
		txtMax.setColumns(20);
		
		JLabel lblSetMinWord = new JLabel("Set min word size");
		lblSetMinWord.setBounds(204, 124, 134, 23);
		getContentPane().add(lblSetMinWord);
		
		txtMin = new JTextField();
		txtMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMin.setText("");
			}
		});
		txtMin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(Integer.parseInt(txtMin.getText()) < WP_Config.minWordSize )
					JOptionPane.showMessageDialog(null, "Min word size is "+WP_Config.minWordSize+" characters or larger.");
			}
		});
		txtMin.setText("Min");
		txtMin.setBounds(204, 158, 53, 30);
		getContentPane().add(txtMin);
		txtMin.setColumns(20);
		
		btnCreatePyramid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if (WP_Config.maxWordSize < WP_Config.minWordSize){
						JOptionPane.showMessageDialog(null, "Max size must be greater then min.");
						
					}else{
					
					CreatePyramid cp = new CreatePyramid(txtMax.getText(), txtMin.getText(), comboBox.getSelectedIndex(), comboBox_1.getSelectedIndex());
					cp.renderHTML();
					}
				} catch (NumberFormatException nfe) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Both max and min must have numerical values.");
				} catch (Exception e){
					
				}
			}
		});
	}
}

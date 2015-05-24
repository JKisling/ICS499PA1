package WordPyramid;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import wp_display.CategorySelection;
import wp_display.CreatePyramid;
import wp_display.LanguageSelection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
		btnCreatePyramid.setBounds(10, 227, 107, 23);
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
		lblSetMaxWord.setBounds(10, 124, 107, 23);
		getContentPane().add(lblSetMaxWord);
		
		txtMax = new JTextField();
		txtMax.setText("Max");
		txtMax.setBounds(10, 158, 53, 30);
		getContentPane().add(txtMax);
		txtMax.setColumns(20);
		
		JLabel lblSetMinWord = new JLabel("Set min word size");
		lblSetMinWord.setBounds(141, 124, 100, 23);
		getContentPane().add(lblSetMinWord);
		
		txtMin = new JTextField();
		txtMin.setText("Min");
		txtMin.setBounds(141, 158, 53, 30);
		getContentPane().add(txtMin);
		txtMin.setColumns(20);
		
		btnCreatePyramid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreatePyramid cp = new CreatePyramid(txtMax.getText(), txtMin.getText(), comboBox.getSelectedIndex(), comboBox_1.getSelectedIndex());
				cp.renderHTML();
			}
		});
	}
}

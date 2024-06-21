/*
 * Author:   Mauricio Gomez
 * Course:   COP3503
 * Project:  #4
 * Title:    GUIs
 * Due Date: 12/8/2023
 * 
 */
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.*;  
import java.awt.event.*;  
import java.awt.Color;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Creates a GUI to ask user diet questions
 *
 */
public class CustomJFrame extends JFrame{
	//Initialize all variables that will be used
	SpinnerModel value = new SpinnerNumberModel(15,0,50,1);
	private JLabel headingLabel  = new JLabel();
	private JLabel firstNameLabel  = new JLabel();
	private JLabel lastNameLabel = new JLabel();
	private JLabel phoneNumberLabel  = new JLabel();
	private JLabel emailLabel  = new JLabel();
	private JLabel dietaryLabel  = new JLabel();
	private JLabel genderLabel  = new JLabel();
	private JLabel waterLabel  = new JLabel();
	private JLabel mealsLabel  = new JLabel();
	private JLabel checkBoxLabel  = new JLabel();
	private JLabel walkLabel  = new JLabel();
	private JLabel weightLabel  = new JLabel();
	private JTextField firstNameTextField = new JTextField(16);
	private JTextField lastNameTextField = new JTextField(16);
	private JTextField phoneNumberTextField = new JTextField(16);
	private JTextField emailTextField = new JTextField(16);
	private JRadioButton maleRadioButton = new JRadioButton("Male");
	private JRadioButton femaleRadioButton = new JRadioButton("Female");
	private JRadioButton preferRadioButton = new JRadioButton("Prefer not to say");
	private ButtonGroup radioButtonGroup = new ButtonGroup();
	private JSpinner waterIntakeSpinner = new JSpinner(value);
	private JSlider mealSlider = new JSlider(0 ,10, 3);
	private JCheckBox wheatCheckBox = new JCheckBox("Wheat");
	private JCheckBox sugarCheckBox = new JCheckBox("Sugar");
	private JCheckBox dairyCheckBox = new JCheckBox("Dairy");
	private JComboBox walkComboBox;
	private String[] walkOptions = {"Less than 1 mile", "More than 1 mile but less than 2 miles", "More than 2 miles but less than 3 miles", "More than 3 miles"};
	private JFormattedTextField weightFormattedTextField = new JFormattedTextField(NumberFormat.getNumberInstance());
	
	
	private JButton clearButton = new JButton("Clear");
	private JButton submitButton = new JButton("Submit");
	
	private FileHandler fileHandler;
	
	//Creates grid for GUI setup 
	public CustomJFrame(){
		setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.insets = new Insets(5,5,5,5);
		
		//Set labels, buttons, and other functions
		headingLabel.setText("Personal information");
		firstNameLabel.setText("Frist Name: ");
		lastNameLabel.setText("Last Name: ");
		phoneNumberLabel.setText("Phone Number: ");
		emailLabel.setText("Email: ");
		genderLabel.setText("Sex: ");
		dietaryLabel.setText("Dietary Questions");
		waterLabel.setText("How many cups of water on average do you drink a day?");
		mealsLabel.setText("How many meals on average do you eat a day?");
		checkBoxLabel.setText("Do any of these meals regularly contain: ");
		weightLabel.setText("How much do you weigh?");
		radioButtonGroup.add(maleRadioButton);
		radioButtonGroup.add(femaleRadioButton);
		radioButtonGroup.add(preferRadioButton);
		
		g.gridx = 0;
		g.gridy = 0;
		add(headingLabel,g);
		
		//Add first name label
		g.gridx = 0;
		g.gridy = 1;
		add(firstNameLabel,g);
		g.gridx = 3;
		add(firstNameTextField,g);
		
		//Add last name label
		g.gridx = 0;
		g.gridy = 2;
		add(lastNameLabel,g);
		g.gridx = 3;
		add(lastNameTextField,g);
		
		//Add phone number label
		g.gridx = 0;
		g.gridy = 3;
		add(phoneNumberLabel,g);
		g.gridx = 3;
		add(phoneNumberTextField,g);
		
		//Add email label
		g.gridx = 0;
		g.gridy = 4;
		add(emailLabel,g);
		g.gridx = 3;
		add(emailTextField,g);
		
		//Add gender label and button options
		g.gridx = 0;
		g.gridy = 5;
		add(genderLabel,g);
		g.gridx = 2;
		g.gridy = 5;
		add(maleRadioButton,g);
		g.gridy = 6;
		g.gridx =2;
		add(femaleRadioButton,g);
		g.gridx = 2;
		g.gridy = 7;
		g.gridwidth = 1;
		add(preferRadioButton,g);
		
		//Add dietary label
		g.gridx = 0;
		g.gridy = 8;
		g.gridwidth = 1;
		add(dietaryLabel,g);
		
		//Add water label and spinner
		g.gridx = 1;
		g.gridy = 9;
		add(waterLabel,g);
		g.gridx = 1;
		g.gridy = 10;
		g.gridheight = 2;
		add(waterIntakeSpinner,g);
		
		//Add meals label and slider
		g.gridx = 1;
		g.gridy = 12;
		g.gridheight = 1;
		add(mealsLabel,g);
		g.gridx = 1;
		g.gridy = 14;
		mealSlider.setPaintTrack(true);
		mealSlider.setPaintTicks(true);
		mealSlider.setPaintLabels(true);
		mealSlider.setMajorTickSpacing(1);
		add(mealSlider,g);
		
		//Adds meal check box options 
		g.gridx = 1;
		g.gridy = 16;
		add(checkBoxLabel,g);
		g.gridx = 0;
		g.gridy = 18;
		g.gridwidth = 1;
		add(dairyCheckBox,g);
		g.gridx = 1;
		add(wheatCheckBox,g);
		g.gridx = 2;
		add(sugarCheckBox,g);
		
		//Add walking label and creates combo box
		g.gridx = 1;
		g.gridy = 19;
		walkLabel.setText("On average how many miles do you walk in a day?");
		add(walkLabel,g);
		g.gridy = 20;
		walkComboBox = new JComboBox(walkOptions);
		add(walkComboBox,g);
		
		//Add weight label and textfield that only accepts numbers
		g.gridx = 1;
		g.gridy = 21;
		add(weightLabel,g);
		g.gridy = 22;
		weightFormattedTextField.setColumns(12);
		add(weightFormattedTextField,g);
		
		//Creates clear and submit button
		g.gridx = 0;
		g.gridy = 23;
		clearButton.setBackground(Color.YELLOW);
		
		add(clearButton,g);
		g.gridx = 3;
		submitButton.setBackground(Color.GREEN);
		
		add(submitButton,g);
		
		setTitle("Dairy Survey");
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		
		
		
		
		
		
	}

	//Creates action listeners for the clear and submit button 
	public void InnerActionListener(){
		ActionListener submitListen = new ActionListener() {
			@Override
			//Adds the info to the survey csv and clears all the fields
			public void actionPerformed(ActionEvent evt) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy");
				LocalDateTime now = LocalDateTime.now();
				fileHandler = new FileHandler();
				fileHandler.writeResults(dtf.format(now) + "," + firstNameTextField.getText() + "," + lastNameTextField.getText() + "," + phoneNumberTextField.getText()
				+ "," + emailTextField.getText() + "," + radioButtonGroup.getSelection() + "," + waterIntakeSpinner.getValue() + "," + mealSlider.getValue() + "," +
				dairyCheckBox.toString() + "," + wheatCheckBox.toString() + "," + sugarCheckBox.toString() + "," + walkComboBox.getSelectedItem() + "," 
				+ weightFormattedTextField.getText());
				firstNameTextField.setText(null);
				lastNameTextField.setText(null);
				phoneNumberTextField.setText(null);
				emailTextField.setText(null);
				radioButtonGroup.setSelected(null, false);
				waterIntakeSpinner.setValue(15);
				mealSlider.setValue(0);
				walkComboBox.setSelectedIndex(0);
				weightFormattedTextField.setValue(null);
			}
		};
		
		submitButton.addActionListener(submitListen);
		
		
		ActionListener clearListen = new ActionListener() {
			@Override
			//Clears all fields
			public void actionPerformed(ActionEvent e) {
				firstNameTextField.setText(null);
				lastNameTextField.setText(null);
				phoneNumberTextField.setText(null);
				emailTextField.setText(null);
				radioButtonGroup.setSelected(null, false);
				waterIntakeSpinner.setValue(15);
				mealSlider.setValue(0);
				walkComboBox.setSelectedIndex(0);
				weightFormattedTextField.setValue(null);
			}
		};
		
		clearButton.addActionListener(clearListen);
		
	}
}
	


		



		
	

	
	

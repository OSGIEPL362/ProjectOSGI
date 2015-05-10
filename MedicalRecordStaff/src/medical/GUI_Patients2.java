package medical;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import medicalFactory.medicalServiceFactory;
import medicalModel.medicalFunctions;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI_Patients2 extends JFrame {

	private JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public GUI_Patients2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Petients  Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(26, 30, 515, 330);
		panel.add(panel_1);
		
		JLabel label = new JLabel("Staff ID:");
		label.setFont(new Font("Calibri", Font.PLAIN, 14));
		label.setBounds(10, 18, 85, 16);
		panel_1.add(label);
		
		JLabel ldlFisrtName = new JLabel("First Name:");
		ldlFisrtName.setFont(new Font("Calibri", Font.PLAIN, 14));
		ldlFisrtName.setBounds(10, 52, 102, 16);
		panel_1.add(ldlFisrtName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLastName.setBounds(10, 88, 102, 16);
		panel_1.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAddress.setBounds(10, 127, 102, 16);
		panel_1.add(lblAddress);
		
		JLabel lblLevelOfMadness = new JLabel("Level Of Madness:");
		lblLevelOfMadness.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLevelOfMadness.setBounds(10, 166, 120, 16);
		panel_1.add(lblLevelOfMadness);
		
		JLabel lblRealatives = new JLabel("Realatives:");
		lblRealatives.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRealatives.setBounds(10, 205, 85, 16);
		panel_1.add(lblRealatives);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(140, 11, 196, 28);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 45, 196, 28);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(140, 81, 196, 28);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(140, 120, 196, 28);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(140, 159, 196, 28);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(140, 198, 196, 28);
		panel_1.add(textField_5);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnYes.setBounds(183, 262, 109, 23);
		panel_1.add(rdbtnYes);
		
		JRadioButton rdbtnYes_1 = new JRadioButton("Yes");
		rdbtnYes_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnYes_1.setBounds(10, 262, 109, 23);
		panel_1.add(rdbtnYes_1);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						//na mpoun sosta pedia apo gui	
				
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
		button.setBounds(383, 242, 109, 23);
		panel_1.add(button);
		
		JLabel lblSelfharm = new JLabel("SelfHarm:");
		lblSelfharm.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSelfharm.setBounds(10, 239, 85, 16);
		panel_1.add(lblSelfharm);
		
		JLabel lblDead = new JLabel("Dead:");
		lblDead.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDead.setBounds(183, 237, 85, 16);
		panel_1.add(lblDead);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				General2 frame = new General2();
				frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SELECT
				
				
			}
		});
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBack.setBounds(383, 202, 109, 23);
		panel_1.add(btnBack);
	}
}

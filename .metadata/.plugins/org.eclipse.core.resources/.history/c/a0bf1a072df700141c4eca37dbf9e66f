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

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

//import aa.modifyCustomer;
//import aa.wrongID;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.sql.ResultSet;
import java.sql.SQLException;

public class GUI_Patients_Edit extends JFrame {

	private JPanel contentPane;
	public JTextField patientID;
	public JTextField name;
	public JTextField address;
	public JTextField mad_lvl;
	public JTextField realative_phone;
	private static int insert;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public GUI_Patients_Edit() {
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
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPatientId.setBounds(10, 18, 85, 16);
		panel_1.add(lblPatientId);
		
		JLabel ldlFisrtName = new JLabel("Name:");
		ldlFisrtName.setFont(new Font("Calibri", Font.PLAIN, 14));
		ldlFisrtName.setBounds(10, 52, 102, 16);
		panel_1.add(ldlFisrtName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAddress.setBounds(10, 90, 102, 16);
		panel_1.add(lblAddress);
		
		JLabel lblLevelOfMadness = new JLabel("Level Of Madness:");
		lblLevelOfMadness.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLevelOfMadness.setBounds(10, 131, 120, 16);
		panel_1.add(lblLevelOfMadness);
		
		JLabel lblRealatives = new JLabel("Realative Phone:");
		lblRealatives.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRealatives.setBounds(10, 169, 120, 16);
		panel_1.add(lblRealatives);
		
		patientID = new JTextField();
		patientID.setEditable(false);
		patientID.setColumns(10);
		patientID.setBounds(140, 11, 196, 28);
		panel_1.add(patientID);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(140, 45, 196, 28);
		panel_1.add(name);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(140, 84, 196, 28);
		panel_1.add(address);
		
		mad_lvl = new JTextField();
		mad_lvl.setColumns(10);
		mad_lvl.setBounds(140, 125, 196, 28);
		panel_1.add(mad_lvl);
		
		realative_phone = new JTextField();
		realative_phone.setColumns(10);
		realative_phone.setBounds(140, 163, 196, 28);
		panel_1.add(realative_phone);
		
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
				try {
					insert=Integer.parseInt(mad_lvl.getText());
					System.out.println(insert);
					//ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
					//stmt = conn.createStatement	();
					//ResultSet srs =	stmt.executeQuery("SELECT * FROM CUSTOMERS");					
					
				}
				
				catch (NumberFormatException q) {
					JOptionPane.showMessageDialog(null,"Please give valid ID.", "Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
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
				General frame = new General();
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

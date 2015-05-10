package medical;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_Staff2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public GUI_Staff2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Staff Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(26, 30, 515, 330);
		panel.add(panel_1);
		
		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblStaffId.setBounds(10, 18, 85, 16);
		panel_1.add(lblStaffId);
		
		JLabel lblFisrtName = new JLabel("First Name:");
		lblFisrtName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblFisrtName.setBounds(10, 52, 102, 16);
		panel_1.add(lblFisrtName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLastName.setBounds(10, 88, 102, 16);
		panel_1.add(lblLastName);
		
		JLabel lblCAddress = new JLabel("Address:");
		lblCAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCAddress.setBounds(10, 127, 102, 16);
		panel_1.add(lblCAddress);
		
		JLabel lblClinicName = new JLabel("Clinic Name:");
		lblClinicName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblClinicName.setBounds(10, 166, 85, 16);
		panel_1.add(lblClinicName);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsername.setBounds(10, 205, 85, 16);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PassWord:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPassword.setBounds(10, 245, 85, 16);
		panel_1.add(lblPassword);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(105, 11, 231, 28);
		panel_1.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(105, 45, 231, 28);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(105, 81, 231, 28);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(105, 120, 231, 28);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(105, 159, 231, 28);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(105, 198, 231, 28);
		panel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(105, 239, 231, 28);
		panel_1.add(textField_6);
		
		JRadioButton rdbtnHealthService = new JRadioButton("Health Service");
		rdbtnHealthService.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnHealthService.setBounds(370, 120, 109, 23);
		panel_1.add(rdbtnHealthService);
		
		JRadioButton rdbtnReceptionist = new JRadioButton("Receptionist");
		rdbtnReceptionist.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnReceptionist.setBounds(370, 85, 109, 23);
		panel_1.add(rdbtnReceptionist);
		
		JRadioButton rdbtnNurse = new JRadioButton("Nurse");
		rdbtnNurse.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnNurse.setBounds(370, 49, 109, 23);
		panel_1.add(rdbtnNurse);
		
		JRadioButton rdbtnDoctor = new JRadioButton("Doctor");
		rdbtnDoctor.setFont(new Font("Calibri", Font.PLAIN, 14));
		rdbtnDoctor.setBounds(370, 15, 109, 23);
		panel_1.add(rdbtnDoctor);
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnHealthService);
        group.add(rdbtnReceptionist);
        group.add(rdbtnNurse);
        group.add(rdbtnDoctor);
        
        
        JButton Bsave= new JButton("Save");
        Bsave.setFont(new Font("Calibri", Font.PLAIN, 14));
        Bsave.setBounds(383, 242, 109, 23);
		panel_1.add(Bsave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				General2 frame = new General2();
				frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBack.setBounds(383, 208, 109, 23);
		panel_1.add(btnBack);
		
	}
}

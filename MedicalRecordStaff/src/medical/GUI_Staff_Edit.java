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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JRadioButton;

import medicalFactory.medicalServiceFactory;
import medicalModel.medicalFunctions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Staff_Edit extends JFrame {

	private JPanel contentPane;
	private JTextField staffID;
	private JTextField name;
	private JTextField address;
	private JTextField clinic;
	private JTextField user;
	private JTextField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public GUI_Staff_Edit(int ID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 371);
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
		panel_1.setBounds(26, 30, 515, 280);
		panel.add(panel_1);
		
		JLabel lblStaffId = new JLabel("Staff ID:");
		lblStaffId.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblStaffId.setBounds(10, 18, 85, 16);
		panel_1.add(lblStaffId);
		
		JLabel lblFisrtName = new JLabel("Name:");
		lblFisrtName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblFisrtName.setBounds(10, 52, 102, 16);
		panel_1.add(lblFisrtName);
		
		JLabel lblCAddress = new JLabel("Email:");
		lblCAddress.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCAddress.setBounds(10, 90, 102, 16);
		panel_1.add(lblCAddress);
		
		JLabel lblClinicName = new JLabel("Clinic Name:");
		lblClinicName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblClinicName.setBounds(10, 129, 85, 16);
		panel_1.add(lblClinicName);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsername.setBounds(10, 174, 85, 16);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PassWord:");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPassword.setBounds(10, 211, 85, 16);
		panel_1.add(lblPassword);
		
		staffID = new JTextField();
		staffID.setColumns(10);
		staffID.setBounds(105, 11, 231, 28);
		panel_1.add(staffID);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(105, 45, 231, 28);
		panel_1.add(name);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(105, 84, 231, 28);
		panel_1.add(address);
		
		clinic = new JTextField();
		clinic.setColumns(10);
		clinic.setBounds(105, 123, 231, 28);
		panel_1.add(clinic);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(105, 168, 231, 28);
		panel_1.add(user);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(105, 205, 231, 28);
		panel_1.add(pass);
		
		JRadioButton hea = new JRadioButton("Health Service");
		hea.setFont(new Font("Calibri", Font.PLAIN, 14));
		hea.setBounds(370, 120, 109, 23);
		panel_1.add(hea);
		
		JRadioButton rec = new JRadioButton("Receptionist");
		rec.setFont(new Font("Calibri", Font.PLAIN, 14));
		rec.setBounds(370, 85, 109, 23);
		panel_1.add(rec);
		
		JRadioButton nur = new JRadioButton("Nurse");
		nur.setFont(new Font("Calibri", Font.PLAIN, 14));
		nur.setBounds(370, 49, 109, 23);
		panel_1.add(nur);
		
		JRadioButton doc = new JRadioButton("Doctor");
		doc.setFont(new Font("Calibri", Font.PLAIN, 14));
		doc.setBounds(370, 15, 109, 23);
		panel_1.add(doc);
		
		JRadioButton man = new JRadioButton("Manager");
		man.setBounds(370, 159, 109, 23);
		panel_1.add(man);
		
		ButtonGroup group = new ButtonGroup();
        group.add(hea);
        group.add(rec);
        group.add(nur);
        group.add(doc);
        group.add(man);
        
        
        JButton Bsave= new JButton("Save");
        Bsave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int s_id=Integer.parseInt(staffID.getText());
				String s_name=name.getText();
				String s_email=address.getText();
				String s_clinic=clinic.getText();
				String s_user=user.getText();
				String s_pass=pass.getText();
				int s_doc=0,s_rec=0,s_nur=0,s_hea=0, s_man=0;
				if(doc.isSelected())
					s_doc=1;
				if(rec.isSelected())
					s_rec=1;
				if(nur.isSelected())
					s_nur=1;
				if(hea.isSelected())
					s_hea=1;
				if(man.isSelected())
					s_man=1;
				
medicalFunctions factory = medicalServiceFactory.getFactory();
				
				if (factory.addStaff(s_id, s_name,  s_email, s_clinic, s_user, s_pass, s_doc,s_nur ,s_rec ,s_hea,s_man)){
					JOptionPane.showMessageDialog(null, "New Staff Added!");
					General frame = new General(ID);
					frame.setVisible(true);
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Erron! Could not add staff",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				
				//System.out.println(s_id+" "+s_name+" "+s_email+" "+s_clinic+" "+s_user+" "+s_pass+" "+s_doc+" "+
				//s_rec+" "+s_nur+" "+s_hea);
        		
        	}
        });
        Bsave.setFont(new Font("Calibri", Font.PLAIN, 14));
        Bsave.setBounds(370, 229, 109, 23);
		panel_1.add(Bsave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				General frame = new General(ID);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnBack.setBounds(370, 192, 109, 23);
		panel_1.add(btnBack);
		
	}
}

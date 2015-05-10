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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import medicalFactory.medicalServiceFactory;
import medicalModel.medicalFunctions;


public class GUI_Patients_Edit extends JFrame {

	private JPanel contentPane;
	public JTextField pid;
	public JTextField name;
	public JTextField address;
	public JTextField email;
	private JComboBox combo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public GUI_Patients_Edit(int ID, int p_id) {
		
		/****************Patient Data from DB*************************/
		String name1 = "";
		Integer patient_id = 0;
		Integer lm = 0;
		Integer d = -1;
		Integer sh = -1;
		String email1 = "";
		String addr = "";
		medicalFunctions factory = medicalServiceFactory.getFactory();
		ResultSet rs = factory.getInfoForPatient(p_id);
		try {
			if (rs.next()) {
				patient_id = rs.getInt("Patient_ID");
				name1 = rs.getString("Name");
				lm = rs.getInt("Madness_level");
				d = rs.getInt("Dead");
				sh = rs.getInt("Self_harm");
				email1 = rs.getString("Relative_Email");
				addr = rs.getString("Address");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*********************************************************************/
		
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
		
		JLabel lblRealatives = new JLabel("Realative Email:");
		lblRealatives.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblRealatives.setBounds(10, 169, 120, 16);
		panel_1.add(lblRealatives);
		
		pid = new JTextField();
		pid.setEditable(false);
		pid.setText(patient_id.toString());
		pid.setColumns(10);
		pid.setBounds(140, 11, 196, 28);
		panel_1.add(pid);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(140, 45, 196, 28);
		name.setText(name1);
		panel_1.add(name);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(140, 84, 196, 28);
		address.setText(addr);
		panel_1.add(address);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(140, 163, 196, 28);
		email.setText(email1);
		panel_1.add(email);
		
		JRadioButton dead = new JRadioButton("Yes");
		dead.setFont(new Font("Calibri", Font.PLAIN, 14));
		dead.setBounds(183, 262, 109, 23);
		if (d == 0){
			dead.setSelected(false);
		}else{
			dead.setSelected(true);
		}
		panel_1.add(dead);
		
		JRadioButton harm = new JRadioButton("Yes");
		harm.setFont(new Font("Calibri", Font.PLAIN, 14));
		harm.setBounds(10, 262, 109, 23);
		if(sh == 0){
			harm.setSelected(false);
		}else{
			harm.setSelected(true);
		}
		panel_1.add(harm);
		
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int p_id=Integer.parseInt(pid.getText());
					String p_name=name.getText();
					String p_addr=address.getText();
					int p_lvl=combo.getSelectedIndex() +1;
					String p_email=email.getText();
					int p_harm=0,p_dead=0;
					if(harm.isSelected())
						p_harm=1;
					if(dead.isSelected())
						p_dead=1;	
					
					medicalFunctions factory = medicalServiceFactory.getFactory();
					if (factory.editPatient(p_id, p_name,  p_addr, p_lvl, p_email, p_harm, p_dead)){
						JOptionPane.showMessageDialog(null, "Update Patient!");
						General frame = new General(ID);
						frame.setVisible(true);
						setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null,"Erron! Could not update patient",
							    "Insert error",
							    JOptionPane.ERROR_MESSAGE);
					}
					
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
				General frame = new General(ID);
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
		
		combo = new JComboBox();
		combo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		combo.setBounds(140, 129, 70, 20);
		combo.setSelectedIndex(lm-1);
		panel_1.add(combo);
	}
}

package receptionist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import receptionistFactory.receptionistFactory;
import receptionistModel.receptionistFunctions;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class newRandevou extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;

	private JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newRandevou frame = new newRandevou("Argo Clinic", 1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newRandevou(String clinic, int rec_id) {
		setBounds(100, 100, 575, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "New Randevou", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(24, 25, 497, 368);
		panel.add(panel_1);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPatientId.setBounds(10, 23, 77, 21);
		panel_1.add(lblPatientId);
		
		textField = new JTextField();
		textField.setBounds(97, 23, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblDoctors = new JLabel("Doctors:");
		lblDoctors.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoctors.setBounds(10, 79, 57, 14);
		panel_1.add(lblDoctors);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 79, 376, 115);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		/*****************************LOAD LIST***************************************/
		DefaultTableModel model = new DefaultTableModel(0, 0);
		
		table.setModel(model);
		
		String header[] = new String[] { "Doctor ID","Name" };
		model.setColumnIdentifiers(header);	
		
		
		receptionistFunctions factory = receptionistFactory.getFactory();	
		ResultSet rs = factory.getStaff(clinic);
		
		try {
			while (rs.next()) {
				
				Integer id = rs.getInt("Staff_ID");
				String name1 = rs.getString("Name");
				model.addRow(new Object[] { id, name1});
			
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**************************************************************/
		
		JLabel lblDoctorId = new JLabel("Doctor ID:");
		lblDoctorId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoctorId.setBounds(10, 219, 77, 14);
		panel_1.add(lblDoctorId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(97, 216, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(10, 250, 46, 14);
		panel_1.add(lblDate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(97, 247, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCheckAva = new JButton("Check Availability");
		btnCheckAva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dt = textField_2.getText();
				final int did = Integer.parseInt(textField_1.getText());
				int t = comboBox.getSelectedIndex() + 8;
				receptionistFunctions factory = receptionistFactory.getFactory();	
				if (factory.checkAvaliability(dt, t, did)){
					
					JOptionPane.showMessageDialog(null, "The day and Time is available");
					
				}else{
					JOptionPane.showMessageDialog(null,"There is other randevou that time!",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCheckAva.setBounds(233, 282, 142, 23);
		panel_1.add(btnCheckAva);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTime.setBounds(10, 285, 46, 14);
		panel_1.add(lblTime);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"}));
		comboBox.setBounds(97, 282, 86, 20);
		panel_1.add(comboBox);
		
		JButton btnCreatRandevou = new JButton("Creat Randevou");
		btnCreatRandevou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//////////////////////////////////////////////////////////////////
				int pid = Integer.parseInt(textField.getText());
				String date = textField_2.getText();
				final int did = Integer.parseInt(textField_1.getText());
				int time = comboBox.getSelectedIndex() + 8;
				receptionistFunctions factory = receptionistFactory.getFactory();	
				
				if (factory.creareNewRandevou(pid, date, time, clinic, did)){
					JOptionPane.showMessageDialog(null, "New Randevou Added!");
					Receptionist_GUI frame = new Receptionist_GUI(rec_id);
					frame.setVisible(true);
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Erron! Could not add randevou",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCreatRandevou.setBounds(151, 326, 177, 23);
		panel_1.add(btnCreatRandevou);
		
		JLabel lblYyyy = new JLabel("(yyyy-mm-dd)");
		lblYyyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblYyyy.setBounds(199, 251, 86, 14);
		panel_1.add(lblYyyy);
	}
}

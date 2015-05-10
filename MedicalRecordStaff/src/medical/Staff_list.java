package medical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import medicalFactory.medicalServiceFactory;
import medicalModel.medicalFunctions;

public class Staff_list extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff_list frame = new Staff_list(1);
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
	public Staff_list(int ID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Staff List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(26, 30, 476, 364);
		panel.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 424, 269);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		/*****************************LOAD LIST***************************************/
		DefaultTableModel model = new DefaultTableModel(0, 0);
		
		table.setModel(model);
		
		String header[] = new String[] { "Staff ID","Name" };
		model.setColumnIdentifiers(header);	
		
		
		medicalFunctions factory = medicalServiceFactory.getFactory();	
		ResultSet rs = factory.getStaff();
		
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
				
		JButton button = new JButton("Go Go Coco");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final int no = Integer.parseInt(textField.getText()); 
				GUI_Staff_Edit frame = new GUI_Staff_Edit(ID, no);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		button.setBounds(320, 294, 101, 23);
		panel_1.add(button);
		
		JLabel lblSelectPatientNumber = new JLabel("Select Staff Number:");
		lblSelectPatientNumber.setBounds(20, 291, 149, 29);
		panel_1.add(lblSelectPatientNumber);
		
		textField = new JTextField();
		textField.setBounds(148, 295, 101, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				General frame = new General(ID);
				frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnNewButton.setBounds(320, 330, 101, 23);
		panel_1.add(btnNewButton);
	}
}

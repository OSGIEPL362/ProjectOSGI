package doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import doctorFactory.doctorServiceFactory;
import doctorsModel.doctorFunctions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class incitent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					incitent frame = new incitent(1112,1111);
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
	public incitent(Integer p_id, Integer d_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Insert Incident", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(26, 28, 515, 411);
		panel.add(panel_1);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(42, 47, 74, 14);
		panel_1.add(lblPatientId);
		
		JLabel lblDetails = new JLabel("Details:");
		lblDetails.setBounds(42, 89, 74, 14);
		panel_1.add(lblDetails);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(149, 44, 148, 20);
		textField.setText(p_id.toString());
		panel_1.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(149, 89, 281, 105);
		panel_1.add(textArea);
		
		JLabel lblMedi = new JLabel("Medicants:");
		lblMedi.setBounds(42, 212, 93, 14);
		panel_1.add(lblMedi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 212, 281, 101);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
/********************************* DRUGS LIST*******************************************/
		
		DefaultTableModel model = new DefaultTableModel(0, 0);
		
		table.setModel(model);
		
		String header[] = new String[] { "Medication ID","Medication Name", "Description" };
		model.setColumnIdentifiers(header);	
			
		final ArrayList<Integer> medID = new ArrayList<Integer>();
		final ArrayList<String> names = new ArrayList<String>();
		final ArrayList<String> disc = new ArrayList<String>();
		
		doctorFunctions factory = doctorServiceFactory.getFactory();
		factory = doctorServiceFactory.getFactory();
		ResultSet dr = factory.getDrugs();
		try {
			while (dr.next()) {
				Integer med = dr.getInt("Medication_ID");
				medID.add(med);
				String name2 = dr.getString("Medication_Name");
				names.add(name2);
				String di = dr.getString("Discreption");
				disc.add(di);
				
				model.addRow(new Object[] { med, name2,di});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*********************************************************************/
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(149, 324, 89, 20);
		panel_1.add(textField_2);
		
		JLabel label = new JLabel("Select Medicent:");
		label.setBounds(42, 327, 116, 14);
		panel_1.add(label);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String details = textArea.getText();
				final int no = Integer.parseInt(textField_2.getText()); 
				
				doctorFunctions factory = doctorServiceFactory.getFactory();
				if(factory.insertIncentend(p_id, details, no)){
					JOptionPane.showMessageDialog(null, "You have add an incetent!");
					doctor_gui frame = new doctor_gui(d_id);
					frame.setVisible(true);
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null,"Erron! Could not add an incedent",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setBounds(149, 364, 89, 23);
		panel_1.add(btnUpdate);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(214, 115, 167, 58);
		panel_1.add(scrollPane_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(267, 364, 89, 23);
		panel_1.add(btnBack);
	}

}

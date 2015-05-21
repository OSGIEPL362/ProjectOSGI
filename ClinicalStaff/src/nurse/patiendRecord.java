package nurse;

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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import doctorFactory.doctorServiceFactory;
import doctorsModel.doctorFunctions;

import javax.swing.JTable;

import nurseFactory.nurseServiceFactory;
import nurseModel.nurseFunctions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class patiendRecord extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JComboBox comboBox;
	private JTextArea textArea;
	JRadioButton rdbtnYes_1 ;
	JRadioButton rdbtnYes;
	private int selfHarm;
	private ArrayList<Integer>l = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					patiendRecord frame = new patiendRecord(943221);
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
	public patiendRecord(int p_id) {
		
		/****************Patient Data from DB*************************/
		String name = "";
		Integer patient_id = 0;
		Integer lm = 0;
		Integer d = -1;
		Integer sh = -1;
		String email = "";
		nurseFunctions factory = nurseServiceFactory.getFactory();
		ResultSet rs = factory.getInfoForPatient(p_id);
		try {
			if (rs.next()) {
				patient_id = rs.getInt("Patient_ID");
				name = rs.getString("Name");
				lm = rs.getInt("Madness_level");
				d = rs.getInt("Dead");
				sh = rs.getInt("Self_harm");
				selfHarm = sh;
				email = rs.getString("Relative_Email");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*********************************************************************/
		
		/****************Get Last condition and medication*************************/
		
		String [] cond_med= factory.getLastConditionAndMedicationOfPatient(patient_id);
		//System.out.println("c: "+cond_med[0]+" m: "+cond_med[1]);
		
		/*********************************************************************/

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Patient Record", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(26, 30, 515, 563);
		panel.add(panel_1);
		
		
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(24, 26, 93, 14);
		panel_1.add(lblPatientId);
		
		JLabel lblPatientName = new JLabel("Patient Name:");
		lblPatientName.setBounds(24, 57, 93, 14);
		panel_1.add(lblPatientName);
		
		JLabel lblCondition = new JLabel("Condition:");
		lblCondition.setBounds(24, 180, 93, 14);
		panel_1.add(lblCondition);
		
		JLabel lblMedicant = new JLabel("Medicants:");
		lblMedicant.setBounds(24, 255, 93, 14);
		panel_1.add(lblMedicant);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 258, 311, 101);
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
		
		factory = nurseServiceFactory.getFactory();
		ResultSet dr = factory.getDrugs();
		try {
			while (dr.next()) {
				Integer med = dr.getInt("Medication_ID");
				medID.add(med);
				String name2 = dr.getString("Medication_Name");
				names.add(name2);
				String di = dr.getString("Discreption");
				disc.add(di);
				l.add(med);
				
				model.addRow(new Object[] { med, name2,di});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*********************************************************************/

		
		JLabel lblSelectMedicent = new JLabel("Last Medicent:");
		lblSelectMedicent.setBounds(24, 370, 116, 14);
		panel_1.add(lblSelectMedicent);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(136, 370, 89, 20);
		textField.setText(cond_med[1]);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(136, 26, 86, 20);
		textField_1.setText(patient_id.toString());
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(136, 54, 217, 20);
		textField_2.setText(name);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblLevelOfMaddness = new JLabel("Level of Madness:");
		lblLevelOfMaddness.setBounds(24, 90, 105, 14);
		panel_1.add(lblLevelOfMaddness);
		
		JLabel lblDead = new JLabel("Dead:");
		lblDead.setBounds(24, 122, 46, 14);
		panel_1.add(lblDead);
		
		JLabel lblRelativeEmail = new JLabel("Relative Email:");
		lblRelativeEmail.setBounds(24, 155, 93, 14);
		panel_1.add(lblRelativeEmail);
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(136, 114, 52, 23);
		if (d==1){
			rdbtnYes.setSelected(true);
		}
		panel_1.add(rdbtnYes);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(136, 85, 52, 20);
		comboBox.setSelectedIndex(lm-1);
		panel_1.add(comboBox);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(136, 152, 217, 20);
		textField_3.setText(email);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSelfHurm = new JLabel("Self Hurm:");
		lblSelfHurm.setBounds(229, 122, 78, 14);
		panel_1.add(lblSelfHurm);
		
		rdbtnYes_1 = new JRadioButton("Yes");
		rdbtnYes_1.setBounds(296, 114, 57, 23);
		if (sh==1){
			rdbtnYes_1.setSelected(true);
		}
		panel_1.add(rdbtnYes_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				doctor_gui frame = new doctor_gui(d_id);
//				
//				frame.setVisible(true);
//				setVisible(false);
			}
		});
		btnBack.setBounds(204, 524, 89, 23);
		panel_1.add(btnBack);
		
		JLabel lblA = new JLabel("Old Commentd:");
		lblA.setBounds(24, 423, 93, 14);
		panel_1.add(lblA);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(136, 411, 311, 102);
		panel_1.add(scrollPane_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEditable(false);
		textArea_2.setEnabled(false);
		textArea_2.setText(cond_med[2]);
		textArea_2.setLineWrap(true);
		
		scrollPane_1.setViewportView(textArea_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(136, 183, 311, 54);
		panel_1.add(scrollPane_2);
		
		 textArea = new JTextArea();
		textArea.setText(cond_med[0]);
		scrollPane_2.setViewportView(textArea);
	}
}

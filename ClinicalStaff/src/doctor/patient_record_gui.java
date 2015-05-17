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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class patient_record_gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JComboBox comboBox;
	private JTextArea textArea_1;
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
					patient_record_gui frame = new patient_record_gui(1111,1);
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
	public patient_record_gui(int d_id, int randevou_id) {
		
		/****************Patient Data from DB*************************/
		String name = "";
		Integer patient_id = 0;
		Integer lm = 0;
		Integer d = -1;
		Integer sh = -1;
		String email = "";
		doctorFunctions factory = doctorServiceFactory.getFactory();
		ResultSet rs = factory.getRandevouInfo(randevou_id);
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
		setBounds(100, 100, 593, 731);
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
		panel_1.setBounds(26, 30, 515, 642);
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
				l.add(med);
				
				model.addRow(new Object[] { med, name2,di});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*********************************************************************/

		
		JLabel lblSelectMedicent = new JLabel("Select Medicent:");
		lblSelectMedicent.setBounds(24, 370, 116, 14);
		panel_1.add(lblSelectMedicent);
		
		textField = new JTextField();
		textField.setBounds(136, 370, 89, 20);
		textField.setText(cond_med[1]);
		panel_1.add(textField);
		textField.setColumns(10);
		
		
		JButton btnCheckForAllergies = new JButton("Check for Allergies");
		btnCheckForAllergies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doctorFunctions factory1 = doctorServiceFactory.getFactory();
				String s =textField.getText();
				if (s.matches("[0-9]+")){
					final int med_id = Integer.parseInt(textField.getText()); 
					if(l.contains(med_id)){
						final int pet_id = Integer.parseInt(textField_1.getText());
					 	String details = factory1.checkForAllergies(pet_id, med_id);
					 	
					 	if (details.equals("")){
					 		JOptionPane.showMessageDialog(null, "No Allergy.");
					 		
					 	}else{
					 		JOptionPane.showMessageDialog(null,
					 				"There is an allergy in this drug.\n" +details ,
					 			    "Allergy Detecte",
					 			    JOptionPane.ERROR_MESSAGE);
							
					 	}
					}else{
						JOptionPane.showMessageDialog(null,"Erron! error input",
							    "Insert error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Erron! error input",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
				 	
			}
		});
		btnCheckForAllergies.setBounds(266, 369, 151, 23);
		panel_1.add(btnCheckForAllergies);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				doctorFunctions factory1 = doctorServiceFactory.getFactory();
				String details="";
				String s =textField.getText();
				boolean flag=true;
				
				int pet_id = 0;
				final int med_id;
				
				
				if (s.matches("[0-9]+")){
					med_id = Integer.parseInt(textField.getText()); 
					if(l.contains(med_id)){
						pet_id = Integer.parseInt(textField_1.getText());
					 	details = factory1.checkForAllergies(pet_id, med_id);	
					}
					else{
						flag=false;
					}
				}
				else{
					med_id=0;
					pet_id=0;
					flag=false;
					
				}
				
				
				
			 	//String details = factory1.checkForAllergies(pet_id, med_id);
			 	
			 	int overrule = -1;
			 	if (details.equals("")){
			 		overrule = 0;			 		
			 	}else{
			 		overrule = 1;
			 	}
			 	
			 	int md = comboBox.getSelectedIndex();
			 	String condition = textArea.getText();
			 	String comments = textArea_1.getText();
			 	
			 	int d = -1;
			 	if(rdbtnYes.isSelected()){
			 		d=1;
			 	}else{
			 		d=0;
			 	}
			 	
			 	int sh = -1;
			 	if (rdbtnYes_1.isSelected()){
			 		sh = 1;
			 	}
			 	else{
			 		sh = 0;
			 	}
			 	
			 	if(selfHarm == 0 && sh == 1){
			 		String remail=textField_3.getText();
			 		String Name =textField_2.getText();
			 		if (factory1.SendEmailtoALL(pet_id, remail, Name)){
			 			JOptionPane.showMessageDialog(null, "You have Send Email");
			 		}else{
			 			JOptionPane.showMessageDialog(null,"Erron! Could not Send Email",
							    "Insert error",
							    JOptionPane.ERROR_MESSAGE);
			 		}
			 			
			 	}
			 	
			 	if(flag){
				 	if (factory1.updateRandevou(randevou_id, condition, med_id , comments,overrule, md, d, sh, pet_id)){
				 		JOptionPane.showMessageDialog(null, "You have update Patient's Record!");
						doctor_gui frame = new doctor_gui(d_id);
						frame.setVisible(true);
						setVisible(false);
				 	}
				}else{
					JOptionPane.showMessageDialog(null,"Erron! Could not update patient's record",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
			 	
			}
		});
		btnUpdate.setBounds(139, 608, 89, 23);
		panel_1.add(btnUpdate);
		
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
		
		JButton btnInsertIncetent = new JButton("Insert Incetent");
		btnInsertIncetent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				final int p_id = Integer.parseInt(textField_1.getText()); 
				incitent frame = new incitent( p_id, d_id,randevou_id);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnInsertIncetent.setBounds(269, 608, 151, 23);
		panel_1.add(btnInsertIncetent);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(136, 524, 311, 73);
		panel_1.add(textArea_1);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(24, 524, 78, 14);
		panel_1.add(lblComments);
		
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
				doctor_gui frame = new doctor_gui(d_id);
				
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBounds(24, 608, 89, 23);
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

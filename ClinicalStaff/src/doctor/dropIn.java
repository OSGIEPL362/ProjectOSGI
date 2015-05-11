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

public class dropIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox;
	private JTextArea textArea_1;
	JRadioButton rdbtnYes_1 ;
	JRadioButton rdbtnYes;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dropIn frame = new dropIn(1111,2);
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
	public dropIn(int d_id, int randevou_id) {
		
		/****************Patient Data from DB*************************/
		String name = "";
		Integer patient_id = 0;
		Integer lm = 0;
		Integer d = -1;
		Integer sh = -1;
		String email = "";
		Integer med = 0;
		String cond = "";
		doctorFunctions factory = doctorServiceFactory.getFactory();
		ResultSet rs = factory.getRandevouInfo(randevou_id);
		try {
			if (rs.next()) {
				patient_id = rs.getInt("Patient_ID");
				name = rs.getString("Name");
				lm = rs.getInt("Madness_level");
				d = rs.getInt("Dead");
				sh = rs.getInt("Self_harm");
				email = rs.getString("Relative_Email");
				med = rs.getInt("Medication_ID");
				cond = rs.getString("Conditions");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*********************************************************************/
		String med_name = "";
		String med_det = "";
		String [] medication = factory.getDrugInfo(med);
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 614);
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
		panel_1.setBounds(26, 30, 515, 492);
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
		

		
		JLabel lblSelectMedicent = new JLabel("Select Medicent:");
		lblSelectMedicent.setBounds(24, 249, 116, 14);
		panel_1.add(lblSelectMedicent);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(136, 249, 46, 20);
		textField.setText(med.toString());
		panel_1.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(136, 183, 311, 55);
		textArea.setText(cond);
		panel_1.add(textArea);
		
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
		
		JButton btnInsertIncetent = new JButton("Complete Drop In Session");
		btnInsertIncetent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doctorFunctions factory = doctorServiceFactory.getFactory();

				if (factory.updateDropIn(randevou_id, d_id)){
					JOptionPane.showMessageDialog(null, "Drop In session is update!");
					doctor_gui frame = new doctor_gui( d_id);
					frame.setVisible(true);
					setVisible(false);
					
				}else{
					JOptionPane.showMessageDialog(null,
			 				"Session was not updated",
			 			    "Session Erro",
			 			    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnInsertIncetent.setBounds(136, 447, 200, 23);
		panel_1.add(btnInsertIncetent);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(136, 347, 311, 73);
		panel_1.add(textArea_1);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(24, 352, 78, 14);
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
		rdbtnYes.setEnabled(false);
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
		rdbtnYes_1.setEnabled(false);
		rdbtnYes_1.setBounds(296, 114, 57, 23);
		if (sh==1){
			rdbtnYes_1.setSelected(true);
		}
		panel_1.add(rdbtnYes_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(192, 249, 200, 20);
		textField_4.setText(medication[0]);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setEnabled(false);
		textArea_2.setBounds(136, 274, 254, 47);
		textArea_2.setText(medication[1]);
		panel_1.add(textArea_2);
	}
}

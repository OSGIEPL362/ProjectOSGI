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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;


import doctorFactory.doctorServiceFactory;
import doctorsModel.doctorFunctions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Doctor_Rantevous extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private ArrayList<Integer>l = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor_Rantevous frame = new Doctor_Rantevous(1111);
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
	public Doctor_Rantevous(int doctor_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Randevou List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(26, 30, 545, 361);
		panel.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 11, 424, 269);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		DefaultTableModel model = new DefaultTableModel(0, 0);
		
		table.setModel(model);
		
		String header[] = new String[] { "Randevou ID","Patient Name", "Date", "Time" };
		model.setColumnIdentifiers(header);	
			
		final ArrayList<Integer> randevouID = new ArrayList<Integer>();
		final ArrayList<String> names = new ArrayList<String>();
		final ArrayList<String> date = new ArrayList<String>();
		final ArrayList<String> time = new ArrayList<String>();

		
		doctorFunctions factory = doctorServiceFactory.getFactory();
		ResultSet rs = factory.getRandevous(doctor_id);
		try {
			while (rs.next()) {
				Integer randevou = rs.getInt("Randevou_ID");
				randevouID.add(randevou);
				String name2 = rs.getString("Name");
				names.add(name2);
				String date1 = rs.getString("Date");
				date.add(date1);
				String time1 = rs.getString("Time");
				time1=time1+".00";
				time.add(time1);
				model.addRow(new Object[] { randevou, name2,date1 , time1});
				l.add(randevou);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		JLabel lblSelectRandvouNumber = new JLabel("Select Randvou Number:");
		lblSelectRandvouNumber.setBounds(53, 290, 149, 29);
		panel_1.add(lblSelectRandvouNumber);
		
		textField = new JTextField();
		textField.setBounds(209, 294, 117, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnGoGoCoco = new JButton("Go Go Coco");
		btnGoGoCoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=textField.getText();
				if (s.matches("[0-9]+")) {
					
					final int no = Integer.parseInt(textField.getText()); 
					if(l.contains(no)){
						patient_record_gui frame = new patient_record_gui(doctor_id,no);
						
						frame.setVisible(true);
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null,"Erron! error input",
							    "Insert error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					System.out.print("oxi");
					JOptionPane.showMessageDialog(null,"Erron! error input",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		btnGoGoCoco.setBounds(355, 291, 101, 23);
		panel_1.add(btnGoGoCoco);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				doctor_gui frame = new doctor_gui(doctor_id);
				
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(355, 325, 101, 23);
		panel_1.add(btnBack);
	}
}

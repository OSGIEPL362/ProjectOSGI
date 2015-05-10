package doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class incitent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					incitent frame = new incitent();
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
	public incitent() {
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
		panel_1.setBounds(26, 30, 515, 409);
		panel.add(panel_1);
		
		JLabel lblPatientId = new JLabel("Patient ID:");
		lblPatientId.setBounds(42, 30, 74, 14);
		panel_1.add(lblPatientId);
		
		JLabel lblPatientName = new JLabel("Patient Name:");
		lblPatientName.setBounds(42, 60, 97, 14);
		panel_1.add(lblPatientName);
		
		JLabel lblDetails = new JLabel("Details:");
		lblDetails.setBounds(42, 89, 74, 14);
		panel_1.add(lblDetails);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(149, 27, 148, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(149, 57, 148, 20);
		panel_1.add(textField_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(149, 89, 281, 105);
		panel_1.add(textArea);
		
		JLabel lblMedi = new JLabel("Medicants:");
		lblMedi.setBounds(42, 212, 93, 14);
		panel_1.add(lblMedi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 212, 281, 101);
		panel_1.add(scrollPane);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(149, 324, 89, 20);
		panel_1.add(textField_2);
		
		JLabel label = new JLabel("Select Medicent:");
		label.setBounds(42, 327, 116, 14);
		panel_1.add(label);
		
		JButton btnUpdate = new JButton("Update");
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

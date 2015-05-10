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

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class General extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					General frame = new General(1);
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
	public General(int ID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 349);
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
		panel_1.setBounds(10, 32, 361, 259);
		panel.add(panel_1);
		
		JButton btnInsertPatients = new JButton("Insert Patients");
		btnInsertPatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				GUI_Patients frame = new GUI_Patients(ID);
				frame.setVisible(true);
				setVisible(false);

			}
		});
		btnInsertPatients.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnInsertPatients.setBounds(34, 75, 126, 23);
		panel_1.add(btnInsertPatients);
		
		JLabel lblInsertPatientsOr = new JLabel("Insert Patients OR Staff :");
		lblInsertPatientsOr.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblInsertPatientsOr.setBounds(34, 36, 233, 16);
		panel_1.add(lblInsertPatientsOr);
		
		JButton btnInsertStaff = new JButton("Insert Staff");
		btnInsertStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUI_Staff frame = new GUI_Staff(ID);
				frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnInsertStaff.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnInsertStaff.setBounds(188, 75, 126, 23);
		panel_1.add(btnInsertStaff);
		
		JLabel lblEditPatientsOr = new JLabel("Edit Patients OR Staff :");
		lblEditPatientsOr.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEditPatientsOr.setBounds(34, 139, 233, 16);
		panel_1.add(lblEditPatientsOr);
		
		JButton btnEditPatients = new JButton("Edit Patients");
		btnEditPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Patient_list frame = new Patient_list(ID);
				frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnEditPatients.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEditPatients.setBounds(34, 173, 126, 23);
		panel_1.add(btnEditPatients);
		
		JButton btnEditStaff = new JButton("Edit Staff");
		btnEditStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Staff_list frame = new Staff_list(ID);
				frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnEditStaff.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEditStaff.setBounds(188, 173, 126, 23);
		panel_1.add(btnEditStaff);
	}
}

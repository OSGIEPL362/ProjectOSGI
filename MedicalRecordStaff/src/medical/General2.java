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

public class General2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					General2 frame = new General2();
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
	public General2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 424);
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
		panel_1.setBounds(10, 32, 515, 330);
		panel.add(panel_1);
		
		JButton btnInsertPatients = new JButton("Insert Patients");
		btnInsertPatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				GUI_Patients2 frame = new GUI_Patients2();
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
				
				GUI_Staff2 frame = new GUI_Staff2();
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
		
		JButton button = new JButton("Insert Patients");
		button.setFont(new Font("Calibri", Font.PLAIN, 14));
		button.setBounds(34, 173, 126, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Insert Staff");
		button_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		button_1.setBounds(188, 173, 126, 23);
		panel_1.add(button_1);
	}
}

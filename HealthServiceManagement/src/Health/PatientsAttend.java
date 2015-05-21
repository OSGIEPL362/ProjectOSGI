package Health;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import HealthFactory.HealthFactoryService;
import HealthModel.HealthFuctions;

public class PatientsAttend extends JFrame {
	private JPanel contentPane;
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientsAttend frame = new PatientsAttend("Clinic1");
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
	public PatientsAttend(String clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(
				new LineBorder(new Color(0, 0, 128), 3), "Clinics List",
				TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
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

		String header[] = new String[] { "Date", "Attend Randevou", "Attend Drop In","Treadments" };
		model.setColumnIdentifiers(header);


		HealthFuctions factory = HealthFactoryService.getFactory();
		String[][] table = factory.getWeaklyReaport(clinic);
		for(int i=0;i<table.length;i++){
			model.addRow(new Object[] { table[i][0], table[i][1], table[i][2],table[i][1] });
		}

		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clinicsList frame = new clinicsList();

				 frame.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(195, 305, 101, 23);
		panel_1.add(btnBack);

	}

}

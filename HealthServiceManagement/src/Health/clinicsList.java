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

public class clinicsList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private ArrayList<String> l = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clinicsList frame = new clinicsList();
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
	public clinicsList() {
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

		String header[] = new String[] { "Clinic Name", "Address", "City" };
		model.setColumnIdentifiers(header);

		final ArrayList<String> names = new ArrayList<String>();
		final ArrayList<String> addr = new ArrayList<String>();
		final ArrayList<String> city = new ArrayList<String>();

		HealthFuctions factory = HealthFactoryService.getFactory();
		ResultSet rs = factory.getClinics();
		try {
			while (rs.next()) {

				String name2 = rs.getString("Cinic_Name");
				names.add(name2);
				String date1 = rs.getString("Address");
				addr.add(date1);
				String time1 = rs.getString("City");

				city.add(time1);
				model.addRow(new Object[] { name2, date1, time1 });
				l.add(name2);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblSelectRandvouNumber = new JLabel("Select Clinic Number:");
		lblSelectRandvouNumber.setBounds(53, 290, 149, 29);
		panel_1.add(lblSelectRandvouNumber);

		textField = new JTextField();
		textField.setBounds(209, 294, 117, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		JButton btnGoGoCoco = new JButton("Go Go Coco");
		btnGoGoCoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = textField.getText();

				if (l.contains(s)) {
					PatientsAttend frame = new  PatientsAttend(s);

					 frame.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Erron! error input",
							"Insert error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnGoGoCoco.setBounds(355, 291, 101, 23);
		panel_1.add(btnGoGoCoco);

	}

}

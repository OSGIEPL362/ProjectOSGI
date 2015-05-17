package doctor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import doctorFactory.doctorServiceFactory;
import doctorsModel.doctorFunctions;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class dropInSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dropInSearch frame = new dropInSearch(1111);
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
	public dropInSearch(int d_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 3), "Drop In Session", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128)));
		panel_1.setBackground(new Color(173, 216, 230));
		panel_1.setBounds(21, 27, 260, 213);
		panel.add(panel_1);
		
		JLabel lblGiveRandevouId = new JLabel("Give Randevou ID:");
		lblGiveRandevouId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGiveRandevouId.setBounds(75, 52, 126, 30);
		panel_1.add(lblGiveRandevouId);
		
		textField = new JTextField();
		textField.setBounds(88, 93, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnChec = new JButton("Check for Drop in");
		btnChec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=textField.getText();
				if (s.matches("[0-9]+")){
					final int no = Integer.parseInt(textField.getText());
					doctorFunctions factory = doctorServiceFactory.getFactory();
					ResultSet rs1 = factory.getRandevouInfo(no);
					try {
						if (rs1.next()) {
							String name = rs1.getString("Date");
							String surname = rs1.getString("Name");
							System.out.println("Randevou "+name + "Patienr "+surname );
							int dr = rs1.getInt("drop_in");
							if (dr == 1){
								JOptionPane.showMessageDialog(null,"Patient have allready took the medicent...",
									    "Insert error",
									    JOptionPane.ERROR_MESSAGE);
							}
							else{
								dropIn frame = new dropIn(d_id, no);
								frame.setVisible(true);
								setVisible(false);
							}
							
						}else{
							JOptionPane.showMessageDialog(null,"Not exist rantevou",
								    "Insert error",
								    JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null,"Error Input",
						    "Insert error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnChec.setBounds(59, 124, 142, 30);
		panel_1.add(btnChec);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doctor_gui frame = new doctor_gui(d_id);

				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBounds(59, 165, 142, 30);
		panel_1.add(btnBack);
		
		
	}
}

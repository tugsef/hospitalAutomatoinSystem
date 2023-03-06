package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import helper.Item;
import model.Clinic;
import model.Patient;
import model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Toolkit;

public class PatientGUI extends JFrame {

	private JPanel w_patientPane;
	private static Patient patient = new Patient();
	private JTable tb_doctor;
	private Clinic clinic = new Clinic();
	private DefaultTableModel doctorClinicModel;
	private Object[] doctorData = null;
	private JTable tb_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourdate = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
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
	public PatientGUI(Patient patient) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));
		
		doctorClinicModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Tc Kimlik No";
		doctorClinicModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		
		whourModel= new DefaultTableModel();
		Object[] colwhour = new Object[2];
		colwhour[0] = "ID";
		colwhour[1] = "Tc Kimlik No";
		whourModel.setColumnIdentifiers(colDoctor);
		whourdate = new Object[2];
		
		
		
		
		setResizable(false);
		setTitle("Hastane  Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_patientPane = new JPanel();
		w_patientPane.setBackground(new Color(255, 255, 255));
		w_patientPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_patientPane);
		w_patientPane.setLayout(null);
		
		JLabel lb_message = new JLabel("");
		JLabel lblNewLabel_1 = new JLabel("Hoş Geldiniz Sayın <dynamic>");
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(48, 12, 226, 36);
		w_patientPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.setBounds(568, 11, 109, 35);
		w_patientPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		tabbedPane.setBounds(32, 65, 656, 370);
		w_patientPane.add(tabbedPane);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Randevu Al", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 42, 258, 300);
		w_appointment.add(scrollPane);
		
		tb_doctor = new JTable(doctorClinicModel);
		scrollPane.setViewportView(tb_doctor);
		
		JLabel lblNewLabel = new JLabel("Doctor Listesi");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 17, 122, 14);
		w_appointment.add(lblNewLabel);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		select_clinic.setModel(new DefaultComboBoxModel(new String[] {"Poliklinik Seç"}));
		select_clinic.setBounds(268, 78, 115, 22);
		try {
			for (int i = 0; i < clinic.getCiinicList().size(); i++) {
				select_clinic.addItem(new Item(clinic.getCiinicList().get(i).getId(),clinic.getCiinicList().get(i).getName()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		select_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_clinic.getSelectedIndex() != 0) {
					JComboBox comboBox = (JComboBox) e.getSource();
					Item item = (Item) comboBox.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) tb_doctor.getModel();
					clearModel.setRowCount(0);
					
					try {
						for (int j = 0; j < clinic.getClinicDoctorList(item.getKey()).size(); j++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(j).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(j).getName();
							doctorClinicModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				
					
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) tb_doctor.getModel();
					clearModel.setRowCount(0);
				}
				
			}
		});
		w_appointment.add(select_clinic);
		
		JScrollPane w_whourList = new JScrollPane();
		w_whourList.setBounds(393, 42, 258, 300);
		w_appointment.add(w_whourList);
		
		tb_whour = new JTable(whourModel);
		w_whourList.setViewportView(tb_whour);
		tb_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tb_doctor.getSelectedRow();
				if(row >= 0) {
					String value = tb_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tb_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourdate[0] =  whour.getWhourList(id).get(i).getId();
							whourdate[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourdate);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					lb_message.setText("Seçim Yapınız...");
				}
			}
		});
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\double_right_24px.png"));
		btnNewButton_1.setBounds(279, 167, 89, 23);
		w_appointment.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Doctor Seç");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel_2.setBounds(289, 193, 79, 14);
		w_appointment.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Doctor Listesi");
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_3.setBounds(393, 19, 122, 14);
		w_appointment.add(lblNewLabel_3);
		
		
		lb_message.setBounds(279, 223, 89, 14);
		w_appointment.add(lb_message);
	}
}

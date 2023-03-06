package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helper.Config;
import model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class UpdateClinicGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_updateClinicName;
	private static Clinic clinic = new Clinic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 219, 197);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fld_clinicName = new JLabel("Poliklinik Adı");
		fld_clinicName.setBounds(58, 11, 77, 17);
		fld_clinicName.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		contentPane.add(fld_clinicName);

		fld_updateClinicName = new JTextField();
		fld_updateClinicName.setBounds(43, 39, 124, 20);
		fld_updateClinicName.setColumns(10);
		fld_updateClinicName.setText(clinic.getName());
		contentPane.add(fld_updateClinicName);

		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Config.confirm()) {
					try {
						clinic.updateClinic(clinic.getId(), fld_updateClinicName.getText());
						Config.showMsg("İşlem Başarılı");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateClinic.setBounds(53, 72, 108, 23);
		contentPane.add(btn_updateClinic);
	}

}

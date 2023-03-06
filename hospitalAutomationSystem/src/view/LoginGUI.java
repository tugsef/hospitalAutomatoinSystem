package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import helper.Config;
import helper.DbConnection;
import helper.DbPostgreConnection;
import model.ChiefPhysicion;
import model.Doctor;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.ComponentOrientation;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class LoginGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField field_hasta;
	private JTextField fld_doctorTc;
	private JPasswordField fld_patientPassword;
	private JPasswordField fld_doctorPassword;
	private DbConnection dbConnection = new DbPostgreConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {

		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight());

		setBackground(new Color(61, 217, 235));
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(null);

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));
		lblNewLabel.setBounds(229, 24, 48, 48);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedPane.setForeground(new Color(0, 0, 0));
		w_tabbedPane.setBorder(null);
		w_tabbedPane.setBounds(10, 132, 464, 218);

		w_pane.add(w_tabbedPane);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setLayout(null);
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabbedPane.addTab("Doktor Girişi", null, w_doctorLogin, null);

		JLabel lblNewLabel_1_1_2 = new JLabel("T.C. Kimlik Numaranız :");
		lblNewLabel_1_1_2.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblNewLabel_1_1_2.setBounds(57, 35, 171, 23);
		w_doctorLogin.add(lblNewLabel_1_1_2);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(257, 36, 139, 20);
		w_doctorLogin.add(fld_doctorTc);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Şifreniz :");
		lblNewLabel_1_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setBounds(57, 84, 171, 23);
		w_doctorLogin.add(lblNewLabel_1_1_1_1);

		JButton btn_doctorEnter = new JButton("Giriş Yap");
		btn_doctorEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorPassword.getText().length() == 0 || fld_doctorTc.getText().length() == 0) {
					Config.showMsg("fill");
				} else {

					try {
						Connection connection = dbConnection.connection();
						Statement statement = connection.createStatement();
						ResultSet rsResultSet = statement.executeQuery("SELECT * FROM users");

						while (rsResultSet.next()) {

							if (fld_doctorPassword.getText().equals(rsResultSet.getString("password"))
									&& fld_doctorTc.getText().equals(rsResultSet.getString("tcno"))) {
								if (rsResultSet.getString("person").equals("bashekim")) {
									ChiefPhysicion physicion = new ChiefPhysicion();
									physicion.setId(rsResultSet.getInt("id"));
									physicion.setTcno(rsResultSet.getString("tcno"));
									physicion.setPassword(rsResultSet.getString("password"));
									physicion.setName(rsResultSet.getString("name"));
									physicion.setType(rsResultSet.getString("person"));

									ChiefPhysicionGUI physicionGUI = new ChiefPhysicionGUI(physicion);
									physicionGUI.setVisible(true);
									dispose(); // var olan diğer ekranı kapatır.
								}
								if (rsResultSet.getString("person").equals("doctor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rsResultSet.getInt("id"));
									doctor.setTcno(rsResultSet.getString("tcno"));
									doctor.setPassword(rsResultSet.getString("password"));
									doctor.setName(rsResultSet.getString("name"));
									doctor.setType(rsResultSet.getString("person"));

									DoctorGUI doctorGUI = new DoctorGUI(doctor);
									doctorGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorEnter.setBounds(173, 132, 139, 29);
		w_doctorLogin.add(btn_doctorEnter);

		fld_doctorPassword = new JPasswordField();
		fld_doctorPassword.setBounds(257, 85, 139, 20);
		w_doctorLogin.add(fld_doctorPassword);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBorder(null);
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabbedPane.addTab("Hasta Kayıt", null, w_hastaLogin, null);
		w_tabbedPane.setBackgroundAt(1, new Color(255, 255, 255));
		w_hastaLogin.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("T.C. Kimlik Numaranız :");
		lblNewLabel_1_1.setBounds(57, 35, 171, 23);
		lblNewLabel_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		w_hastaLogin.add(lblNewLabel_1_1);

		field_hasta = new JTextField();
		field_hasta.setBounds(257, 36, 139, 20);
		w_hastaLogin.add(field_hasta);
		field_hasta.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Şifreniz :");
		lblNewLabel_1_1_1.setFont(new Font("Cambria Math", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(57, 84, 171, 23);
		w_hastaLogin.add(lblNewLabel_1_1_1);

		JButton btn_patientRegistration = new JButton("Kayıt Ol");
		btn_patientRegistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_patientRegistration.setBorder(null);
		btn_patientRegistration.setBackground(new Color(255, 255, 255));
		btn_patientRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI registerGUI = new RegisterGUI();
				registerGUI.setVisible(true);
				dispose();
			}
		});
		btn_patientRegistration.setBounds(321, 132, 98, 23);
		w_hastaLogin.add(btn_patientRegistration);

		JButton btn_patientEntered = new JButton("Giriş Yap");

		btn_patientEntered.setBounds(173, 132, 139, 29);
		w_hastaLogin.add(btn_patientEntered);

		fld_patientPassword = new JPasswordField();
		fld_patientPassword.setBounds(257, 85, 139, 20);
		w_hastaLogin.add(fld_patientPassword);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(342, 155, 54, 2);
		w_hastaLogin.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Hastane Sistemine Hoşgeldiniz.....");
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(118, 72, 271, 37);
		w_pane.add(lblNewLabel_1);
	}
}

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Patient;
import model.User;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class RegisterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel w_paneRegister;
	private JTextField fld_registerName;
	private JLabel lblTc;
	private JTextField fld_registerTcno;
	private JLabel lblifre;
	private JTextField fld_registerPassword;
	private JLabel lb_registerMassage;
	
	private Patient patient = new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_paneRegister = new JPanel();
		w_paneRegister.setBackground(new Color(255, 255, 255));
		w_paneRegister.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_paneRegister);
		w_paneRegister.setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Ad Soyad");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel.setBounds(34, 19, 97, 17);
		w_paneRegister.add(lblNewLabel);

		fld_registerName = new JTextField();
		fld_registerName.setColumns(10);
		fld_registerName.setBounds(34, 47, 214, 20);
		w_paneRegister.add(fld_registerName);

		lblTc = new JLabel("T.C. Kimlik Numarası");
		lblTc.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblTc.setBounds(34, 92, 154, 17);
		w_paneRegister.add(lblTc);

		fld_registerTcno = new JTextField();
		fld_registerTcno.setColumns(10);
		fld_registerTcno.setBounds(34, 120, 214, 20);
		w_paneRegister.add(fld_registerTcno);

		lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblifre.setBounds(34, 162, 63, 17);
		w_paneRegister.add(lblifre);

		fld_registerPassword = new JTextField();
		fld_registerPassword.setColumns(10);
		fld_registerPassword.setBounds(34, 190, 214, 20);
		w_paneRegister.add(fld_registerPassword);

		lb_registerMassage = new JLabel("");
		JButton btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_registerTcno.getText().isEmpty() || fld_registerPassword.getText().isEmpty()) {
					lb_registerMassage.setText("Eksik veya Hatali Bilgi...");
				}else {
					try {
						boolean control = patient.addPatient(fld_registerTcno.getText(), fld_registerPassword.getText(), fld_registerName.getText());
						if (control) {
							lb_registerMassage.setText("İslem Başarılı...");
							LoginGUI loginGUI = new LoginGUI();
							loginGUI.setVisible(true);
							dispose();
							
						}else {
							lb_registerMassage.setText("İslem Başarısız...");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 11));
		btnNewButton.setBounds(107, 238, 89, 23);
		w_paneRegister.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(
				"C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\back_to_48px.png"));
		lblNewLabel_1.setBounds(34, 221, 46, 59);
		w_paneRegister.add(lblNewLabel_1);
		
		
		lb_registerMassage.setForeground(new Color(255, 0, 0));
		lb_registerMassage.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lb_registerMassage.setBounds(90, 272, 134, 17);
		w_paneRegister.add(lb_registerMassage);
	}
}

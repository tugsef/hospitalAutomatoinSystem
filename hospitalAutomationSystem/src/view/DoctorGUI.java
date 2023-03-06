package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import model.Doctor;
import java.awt.Toolkit;

public class DoctorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Doctor doctor = new Doctor();
	private JPanel w_pane;
	private JTable tb_whour;
	private DefaultTableModel whourModel = null;



	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return super.getFont();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		Object[] whourData = new Object[2];
		try {
			for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
				whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lb_doctorMessage = new JLabel("");
		lb_doctorMessage.setForeground(new Color(255, 0, 0));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Hoş Geldiniz Sayın " + doctor.getName());
		lblNewLabel_1.setBounds(30, 12, 226, 36);
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		w_pane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setBounds(550, 11, 109, 35);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		w_pane.add(btnNewButton);

		JTabbedPane w_doctorTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_doctorTabbedPane.setBounds(30, 71, 669, 379);
		w_pane.add(w_doctorTabbedPane);

		JPanel w_whour = new JPanel();
		w_whour.setBackground(new Color(255, 255, 255));
		w_doctorTabbedPane.addTab("Çalışma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.getCalendarButton().setForeground(new Color(0, 0, 0));
		select_date.setBackground(new Color(255, 255, 255));
		select_date.setBounds(359, 11, 139, 25);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] { "", "09:30", "10:00", "10:30", "11:00", "11:30",
				"12:00", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30" }));
		select_time.setBounds(508, 11, 68, 25);
		w_whour.add(select_time);

		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";

				try {
					date = simpleDateFormat.format(select_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}

				if (date.length() == 0) {

					lb_doctorMessage.setText("Lütfen Tarih Seçiniz...");
				} else {
					int selectTime = select_time.getSelectedItem().toString().length();
					if (selectTime == 0) {
						lb_doctorMessage.setText("Lütfen Saat Seçiniz...");
					} else {
						String time = " " + select_time.getSelectedItem().toString() + ":00";
						String selectData = date + time;

						try {
							boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectData);
							if (control) {
								lb_doctorMessage.setText("İşlem Başarılı");
								updateWhourModel(doctor);
							} else {
								lb_doctorMessage.setText("İşlem Başarısız..");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btn_addWhour.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btn_addWhour.setBounds(586, 11, 68, 25);
		w_whour.add(btn_addWhour);

		JScrollPane scrollPane_whour = new JScrollPane();
		scrollPane_whour.setBounds(0, 47, 664, 304);
		w_whour.add(scrollPane_whour);

		tb_whour = new JTable(whourModel);
		scrollPane_whour.setViewportView(tb_whour);

		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = tb_whour.getSelectedRow();
				if (selectRow >= 0) {
					String selectRowId = tb_whour.getModel().getValueAt(selectRow, 0).toString();
					int selectId = Integer.parseInt(selectRowId);
					boolean control;
					try {
						control = doctor.deleteWhour(selectId);
						if (control) {
							lb_doctorMessage.setText("Silme İşlemi Başarılı");
							update(getGraphics());
						} else {
							lb_doctorMessage.setText("İşlem Başarısız...");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					lb_doctorMessage.setText("Tablodan Seçim Yapınız...");
				}
			}
		});
		btn_deleteWhour.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btn_deleteWhour.setBounds(10, 13, 68, 25);
		w_whour.add(btn_deleteWhour);

		lb_doctorMessage.setBounds(494, 57, 152, 14);
		w_pane.add(lb_doctorMessage);
	}

	public void updateWhourModel(Doctor doctor) {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_whour.getModel();
		clearDefaultTableModel.setRowCount(0);

		try {
			Object[] whourData = new Object[2];
			for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {

				whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

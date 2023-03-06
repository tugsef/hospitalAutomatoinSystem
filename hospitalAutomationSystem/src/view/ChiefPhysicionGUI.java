package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import helper.Config;
import helper.Item;
import model.ChiefPhysicion;
import model.Clinic;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class ChiefPhysicionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	static ChiefPhysicion physicion = new ChiefPhysicion();
	private JTextField fld_doktorName;
	private JTextField fld_doctorTcno;
	private JTextField fld_doctorPassword;
	private JTextField btn_doctorUserID;

	private DefaultTableModel doctorModel = null;
	private Object[] column = null;
	private Object[] user = null;

	private DefaultTableModel clinicModel = null;
	private Object[] clinicColumn;
	private Object[] clinics;
	private Clinic clinic = new Clinic();

	private JTable tb_doctor;
	private JTextField fld_nameClinic;
	private JTable tb_clinic;
	private JPopupMenu popupMenu_clinic = null;
	private JTable tb_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefPhysicionGUI frame = new ChiefPhysicionGUI(physicion);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ChiefPhysicionGUI(ChiefPhysicion physicion) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\sefad\\OneDrive\\Masaüstü\\Java\\Patika\\Orta Seviye Java ile Web Development Patikası\\hospitalAutomatoinSystem\\hospitalAutomationSystem\\src\\view\\hospital_sign_48px.png"));
		setResizable(false);

		// Doctor DefaultModel
		doctorModel = new DefaultTableModel();

		column = new Object[4];
		user = new Object[4];

		column[0] = "ID";
		column[1] = "Tc Kimlik No";
		column[2] = "Ad Soyad";
		column[3] = "Şifre";

		doctorModel.setColumnIdentifiers(column);

		for (int j = 0; j < physicion.getDoctorList().size(); j++) {
			user[0] = physicion.getDoctorList().get(j).getId();
			user[1] = physicion.getDoctorList().get(j).getTcno();
			user[2] = physicion.getDoctorList().get(j).getName();
			user[3] = physicion.getDoctorList().get(j).getPassword();
			doctorModel.addRow(user);
		}

		// Clinic DefaultModel
		clinicModel = new DefaultTableModel();
		clinicColumn = new Object[2];
		clinicColumn[0] = "ID";
		clinicColumn[1] = "Poliklinik";
		clinicModel.setColumnIdentifiers(clinicColumn);

		clinics = new Object[2];

		for (int i = 0; i < clinic.getCiinicList().size(); i++) {
			clinics[0] = clinic.getCiinicList().get(i).getId();
			clinics[1] = clinic.getCiinicList().get(i).getName();
			clinicModel.addRow(clinics);

		}

		// Worker DefaultModel
		DefaultTableModel workerDefaultTableModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "id";
		colWorker[1] = "Ad Soyad";
		workerDefaultTableModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		tb_doctor = new JTable(doctorModel);
		tb_doctor.addVetoableChangeListener(new VetoableChangeListener() {
			public void vetoableChange(PropertyChangeEvent evt) {
			}
		});
		tb_doctor.setColumnSelectionAllowed(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setForeground(new Color(128, 128, 0));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setTitle("Hastane Yönetim Sistemi");
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(571, 10, 109, 35);
		w_pane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Hoş Geldiniz Sayın " + physicion.getName());
		lblNewLabel_1.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(31, 11, 226, 36);
		w_pane.add(lblNewLabel_1);

		JTabbedPane w_doctorTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_doctorTabbedPane.setBounds(31, 56, 651, 354);
		w_pane.add(w_doctorTabbedPane);

		JPanel w_panel = new JPanel();
		w_panel.setBackground(new Color(255, 255, 255));
		w_doctorTabbedPane.addTab("Doktor Yönetimi", null, w_panel, null);
		w_panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ad Soyad");
		lblNewLabel.setBounds(511, 11, 63, 17);
		w_panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 13));

		JLabel lblTcno = new JLabel("T.C.No");
		lblTcno.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblTcno.setBounds(511, 59, 63, 17);
		w_panel.add(lblTcno);

		JLabel lblifre = new JLabel("Şifre");
		lblifre.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblifre.setBounds(511, 107, 63, 17);
		w_panel.add(lblifre);
		fld_doktorName = new JTextField();
		fld_doktorName.setBounds(511, 28, 108, 20);
		w_panel.add(fld_doktorName);
		fld_doktorName.setColumns(10);

		fld_doctorTcno = new JTextField();
		fld_doctorTcno.setColumns(10);
		fld_doctorTcno.setBounds(511, 76, 108, 20);
		w_panel.add(fld_doctorTcno);

		fld_doctorPassword = new JTextField();
		fld_doctorPassword.setColumns(10);
		fld_doctorPassword.setBounds(511, 123, 108, 20);
		w_panel.add(fld_doctorPassword);

		btn_doctorUserID = new JTextField();
		btn_doctorUserID.setEnabled(false);
		btn_doctorUserID.setColumns(10);
		btn_doctorUserID.setBounds(511, 204, 108, 20);
		w_panel.add(btn_doctorUserID);

		JLabel lb_message = new JLabel("");
		lb_message.setForeground(new Color(255, 0, 0));
		lb_message.setBounds(31, 0, 226, 14);
		w_pane.add(lb_message);

		JButton btn_doctoreAdd = new JButton("Ekle");
		btn_doctoreAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorPassword.getText().isEmpty() || fld_doctorTcno.getText().isEmpty()
						|| fld_doktorName.getText().isEmpty()) {
					lb_message.setText("Alanlar boş bırakılamaz...");
				} else {
					try {
						boolean control = physicion.addDoctor(fld_doctorTcno.getText() , fld_doctorPassword.getText(),
								fld_doktorName.getText());
						if (control) {
							lb_message.setText("Kayıt Başarılı...");
							fld_doctorPassword.setText(null);
							fld_doctorTcno.setText(null);
							fld_doktorName.setText(null);
							updateDoctorModel();

						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctoreAdd.setBounds(511, 154, 108, 23);
		w_panel.add(btn_doctoreAdd);

		JLabel lblKullancId = new JLabel("Kullanıcı ID");
		lblKullancId.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		lblKullancId.setBounds(511, 188, 92, 17);
		w_panel.add(lblKullancId);

		JButton btn_doctorDelete = new JButton("Sil");
		btn_doctorDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btn_doctorUserID.getText().isEmpty()) {
					lb_message.setText("ID seçimi yapınız...");
				} else {
					boolean select = Config.confirm();

					if (select) {

						int selectID = Integer.parseInt(btn_doctorUserID.getText());

						try {
							boolean control = physicion.deleteDoctor(selectID);
							lb_message.setText("Silme İşlemi Başarılı");
							btn_doctorUserID.setText(null);
							if (!control) {
								lb_message.setText("İşlem Başarısız...");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						updateDoctorModel();
					}

				}
			}
		});
		btn_doctorDelete.setBounds(511, 235, 108, 23);
		w_panel.add(btn_doctorDelete);
		tb_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					btn_doctorUserID.setText(tb_doctor.getValueAt(tb_doctor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 11, 491, 286);
		w_panel.add(scrollPane);
		scrollPane.setViewportView(tb_doctor);

		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(new Color(255, 255, 255));
		w_doctorTabbedPane.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane scrollPane_clic = new JScrollPane();
		scrollPane_clic.setBounds(10, 11, 227, 304);
		w_clinic.add(scrollPane_clic);

		popupMenu_clinic = new JPopupMenu();
		JMenuItem updateItem = new JMenuItem("Güncelleme");
		JMenuItem deleteItem = new JMenuItem("Sil");
		updateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selClinicId = Integer.parseInt(tb_clinic.getValueAt(tb_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selClinicId);
				UpdateClinicGUI updateClinicGUI = new UpdateClinicGUI(selectClinic);
				updateClinicGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateClinicGUI.setVisible(rootPaneCheckingEnabled);
				updateClinicGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						updateClinicsModel();
					}
				});
			}
		});

		deleteItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Config.confirm()) {
					int selClinicId = Integer.parseInt(tb_clinic.getValueAt(tb_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selClinicId)) {
							Config.showMsg("İşlem Başarılı");
							updateClinicsModel();
						} else {
							Config.showMsg("EROR");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		popupMenu_clinic.add(updateItem);
		popupMenu_clinic.add(deleteItem);

		tb_clinic = new JTable(clinicModel);
		tb_clinic.setComponentPopupMenu(popupMenu_clinic);
		tb_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = tb_clinic.rowAtPoint(point);
				tb_clinic.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scrollPane_clic.setViewportView(tb_clinic);

		fld_nameClinic = new JTextField();
		fld_nameClinic.setColumns(10);
		fld_nameClinic.setBounds(269, 40, 108, 20);

		w_clinic.add(fld_nameClinic);

		JLabel fld_clinicName = new JLabel("Poliklinik Adı");
		fld_clinicName.setFont(new Font("Cambria Math", Font.PLAIN, 13));
		fld_clinicName.setBounds(269, 20, 82, 17);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_nameClinic.getText().isEmpty()) {
					lb_message.setText("Lütfen Polikilinik yazınız..");
				} else {
					try {
						if (clinic.addClinic(fld_nameClinic.getText())) {
							lb_message.setText("Poliklinik eklendi...");
							fld_nameClinic.setText("");
							updateClinicsModel();

						} else {
							lb_message.setText("Poliklinik ekleme başarısız...");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setBounds(269, 71, 108, 23);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollPaneWorker = new JScrollPane();
		w_scrollPaneWorker.setBounds(409, 11, 227, 304);
		w_clinic.add(w_scrollPaneWorker);

		tb_worker = new JTable();
		w_scrollPaneWorker.setViewportView(tb_worker);

		JComboBox cbox_select_doctor = new JComboBox();
		cbox_select_doctor.setModel(new DefaultComboBoxModel(new String[] {""}));
		for (int i = 0; i < physicion.getDoctorList().size(); i++) {
			cbox_select_doctor.addItem(
					new Item(physicion.getDoctorList().get(i).getId(), physicion.getDoctorList().get(i).getName()));
		}

		cbox_select_doctor.addActionListener(e -> {
			JComboBox comboBox = (JComboBox) e.getSource();
			Item item = (Item) comboBox.getSelectedItem();
			System.out.println(item.getKey() + " : " + item.getValue());

		});
		cbox_select_doctor.setBounds(269, 224, 108, 22);
		w_clinic.add(cbox_select_doctor);
		JLabel lb_clinicMessage = new JLabel("");
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = tb_clinic.getSelectedRow();
				if (selectRow >= 0) {
					String selClinic = tb_clinic.getModel().getValueAt(selectRow, 0).toString();
					int selClinicId = Integer.parseInt(selClinic);
					Item doctoritem = (Item) cbox_select_doctor.getSelectedItem();
					try {
						boolean control = physicion.addWorker(doctoritem.getKey(), selClinicId);
						if (control) {
							lb_clinicMessage.setText("İşlem Başarılı...");
							DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_worker.getModel();
							clearDefaultTableModel.setRowCount(0);
							for (int i = 0; i < physicion.getClinicDoctorList(selClinicId).size(); i++) {
								workerData[0] = physicion.getClinicDoctorList(selClinicId).get(i).getId();
								workerData[1] = physicion.getClinicDoctorList(selClinicId).get(i).getName();
								workerDefaultTableModel.addRow(workerData);
							}
							tb_worker.setModel(workerDefaultTableModel);
						} else {
							lb_clinicMessage.setText("İşlem Başarısız...");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					lb_clinicMessage.setText("Seçim Yapınız...");
				}
			}
		});
		btn_addWorker.setBounds(269, 257, 108, 23);
		w_clinic.add(btn_addWorker);

		lb_clinicMessage.setBounds(269, 193, 108, 20);
		w_clinic.add(lb_clinicMessage);

		JButton btn_addClinic_1 = new JButton("Seç");
		btn_addClinic_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = tb_clinic.getSelectedRow();

				if (selectRow >= 0) {
					String selClinic = tb_clinic.getModel().getValueAt(selectRow, 0).toString();
					int selClinicId = Integer.parseInt(selClinic);
					DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_worker.getModel();
					clearDefaultTableModel.setRowCount(0);

					try {
						for (int i = 0; i < physicion.getClinicDoctorList(selClinicId).size(); i++) {
							workerData[0] = physicion.getClinicDoctorList(selClinicId).get(i).getId();
							workerData[1] = physicion.getClinicDoctorList(selClinicId).get(i).getName();
							workerDefaultTableModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					tb_worker.setModel(workerDefaultTableModel);
				} else {
					lb_clinicMessage.setText("Lütfen Poliklinik Seçiniz...");

				}
			}
		});
		btn_addClinic_1.setBounds(269, 149, 108, 23);
		w_clinic.add(btn_addClinic_1);

		tb_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectId = Integer.parseInt(tb_doctor.getValueAt(tb_doctor.getSelectedRow(), 0).toString());
					String selecttcno = tb_doctor.getValueAt(tb_doctor.getSelectedRow(), 1).toString();
					String selectpassword = tb_doctor.getValueAt(tb_doctor.getSelectedRow(), 2).toString();
					String selectname = tb_doctor.getValueAt(tb_doctor.getSelectedRow(), 3).toString();

					try {
						boolean approval = Config.confirm();
						if (approval) {

							boolean count = physicion.updateDoctor(selectId, selecttcno, selectpassword, selectname);

							if (count) {
								lb_message.setText("Güncelleme işlemi başarılı");

							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

	}

	public void updateClinicsModel() {
		DefaultTableModel clearModel = (DefaultTableModel) tb_clinic.getModel();
		clearModel.setRowCount(0);
		clinics = new Object[2];

		try {
			for (int i = 0; i < clinic.getCiinicList().size(); i++) {
				clinics[0] = clinic.getCiinicList().get(i).getId();
				clinics[1] = clinic.getCiinicList().get(i).getName();
				clinicModel.addRow(clinics);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateDoctorModel() {
		DefaultTableModel clearDefaultTableModel = (DefaultTableModel) tb_doctor.getModel();
		clearDefaultTableModel.setRowCount(0);

		try {
			for (int j = 0; j < physicion.getDoctorList().size(); j++) {
				user[0] = physicion.getDoctorList().get(j).getId();
				user[1] = physicion.getDoctorList().get(j).getTcno();
				user[2] = physicion.getDoctorList().get(j).getName();
				user[3] = physicion.getDoctorList().get(j).getPassword();
				doctorModel.addRow(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

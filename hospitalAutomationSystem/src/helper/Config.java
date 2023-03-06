package helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Config {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionalPane.cancelButtonText", "İptal");
		UIManager.put("OptionalPane.yesButtonText", "Evet");
		UIManager.put("OptionalPane.noButtonText", "Hayır");
		UIManager.put("OptionalPane.okButtonText", "Tamam");
		
	}
	
	
	public static void showMsg(String str) {
		optionPaneChangeButtonText();
		
		String message;
		
		switch (str) {
		case "fill":
			message = "Lütfen Tüm Alanları Doldurunuz...";
			JOptionPane.showMessageDialog(null, message ,"Mesaj" ,  JOptionPane.INFORMATION_MESSAGE);
			break;

		default:
			message = str;
			JOptionPane.showMessageDialog(null, message ,"Mesaj" ,  JOptionPane.INFORMATION_MESSAGE);
			break;
			
			
		}
	}
	
	public static boolean confirm() {
		optionPaneChangeButtonText();
		String message = "İşlem Onayı";
		
		int resault = JOptionPane.showConfirmDialog(null, message , "Önemli" , JOptionPane.YES_NO_OPTION);
		
		if(resault ==0) {
			return true;
		}else {
			return false;
		}
		
	}
}

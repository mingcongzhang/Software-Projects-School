package lab8;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args){
		String cmd = "";
		String showEn = "";
		String showDe = "";
		String input = "";
		while(true){
			if(cmd.compareToIgnoreCase("D")!=0){
				cmd = JOptionPane.showInputDialog(null, "Please choose an option: \nA.Creat a string\nB.Show encryption\nC.Show decryption\nD.End");
				if(cmd == null){
					System.exit(0);
				}
				if(cmd.compareToIgnoreCase("A")==0){
					input = "";
					input = JOptionPane.showInputDialog(null, "Please input a string: ");
					if(cmd == null){
						System.exit(0);
					}
				}
				if(cmd.compareToIgnoreCase("B")==0){
					if(cmd == null){
						System.exit(0);
					}
					showEn = "";
					int num = input.length();
					for(int i=0;i<num;i++){
						cryptoString a = new cryptoString(input.substring(i,i+1));
						showEn = showEn+a.encrypt();
					}
					JOptionPane.showMessageDialog(null,"The encryption of the string is: "+showEn);
				}
				if(cmd.compareToIgnoreCase("C")==0){
					if(cmd == null){
						System.exit(0);
					}
					showDe = "";
					int num = showEn.length();
					for(int i=0;i<num;++i){
						cryptoString a = new cryptoString(showEn.substring(i,i+1));
						showDe = showDe+a.decrypt();
					}
					JOptionPane.showMessageDialog(null,"The encryption of the string is: "+showDe);
				}
				if(cmd.compareToIgnoreCase("D")==0){
					System.exit(0);
				}
			}
		}	
	}
}

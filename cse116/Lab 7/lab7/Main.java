package lab7;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) throws IOException{
		String cmd = "";
		File loc = new File("H:\\01_IT219_Java02\\week02\\demo\\demoDataFile.txt");
		RandomAccessFile store = new RandomAccessFile(loc, "rw");
		while(true){
			if(cmd.compareToIgnoreCase("D")!=0){
				cmd = JOptionPane.showInputDialog(null, "Please choose an option: \nA.Create a new array\nB.Add value to a position\nC.Show an element\nD.End");
				if(cmd == null){
					System.exit(0);
				}
				if(cmd.compareToIgnoreCase("A")==0){
					int num, numSec, remainder;	
					File temp = new File("H:\\01_IT219_Java02\\week02\\demo\\demoDataFile.txt");
					RandomAccessFile s = new RandomAccessFile(temp, "rw");
					store = s;
					while(true){
						try{
							cmd = JOptionPane.showInputDialog(null, "Please input length of the array(1-1000): ");
							if(cmd == null){
								System.exit(0);
							}
							num = Integer.parseInt(cmd);
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Your input is invalid. Please input an integer.");
							continue;
						}
						break;
					}
					numSec = num/100;
					if(num>100){
						JOptionPane.showMessageDialog(null,"The array you created has more than 100 elements."+(numSec+1)+" separate arrays will be created.");
					}
					remainder = num - numSec*100;
					for (int i = 0; i<numSec; ++i){
						vMem a = new vMem();
						for(int j=0; j<100; ++j){
							store.writeUTF(a.getV(j));
						}		
					}
					if(remainder!=0){
						vMem a = new vMem(remainder);
						for(int i=0;i<remainder;++i){
							store.writeUTF(a.getV(i));
						}
					}
				}
				if(cmd.compareToIgnoreCase("B")==0){
					int num;
					while(true){
						try{
							cmd = JOptionPane.showInputDialog(null, "Please input the position of the element: ");
							if(cmd == null){
								System.exit(0);
							}
							num = Integer.parseInt(cmd);
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Your input is invalid. Please input an integer.");
							continue;
						}
						break;
					}
					cmd = JOptionPane.showInputDialog(null, "Please input the value of the element: ");
					int a = cmd.length();
					for(int i=0;i<(20-a);++i){
						cmd = cmd + " ";
					}	
					store.seek((20+2)*(num-1));
					store.writeUTF(cmd);
				}
				if(cmd.compareToIgnoreCase("C")==0){
					int num;
					while(true){
						try{
							cmd = JOptionPane.showInputDialog(null, "Please input the position of the element: ");
							if(cmd == null){
								System.exit(0);
							}
							num = Integer.parseInt(cmd);
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null,"Your input is invalid. Please input an integer.");
							continue;
						}
						break;
					}
					store.seek((20+2)*(num-1));
					String s = store.readUTF();
					if(!s.equals("                    ")){
						JOptionPane.showMessageDialog(null,"The element on position "+num+" is: "+s);
					}else{
						JOptionPane.showMessageDialog(null,"The element on position "+num+" does not have a value");
					}
				}
			}else{
				System.exit(0);
			}	
		}	
	}
}

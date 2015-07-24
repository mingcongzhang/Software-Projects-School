package lab2;

import java.io.*;
import javax.swing.JOptionPane;

public class Main {
	
	final private static int MAX_RECORD_NUMBER = 10;
	final private static int RECORD_LENGTH = 46;
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		File loc = new File("H:\\01_IT219_Java02\\week02\\demo\\demoDataFile.txt");
		RandomAccessFile store = new RandomAccessFile(loc, "rw");
		String Dummy = "      1                            None None";
		for (int i = 0; i<MAX_RECORD_NUMBER; ++i){
			store.seek((RECORD_LENGTH+2)*i);
			store.writeUTF(Dummy);
		}
		String Description = "";
		int recLocation = 0;
		int number_on_hand = 0;
		String where = "0";
		String cmd = "start";
		while(cmd.compareToIgnoreCase("end")!=0){
			Description = "";
			cmd = JOptionPane.showInputDialog(null, "Please input a command:");
			if(cmd == null){
				System.exit(0);
			}
			if(cmd.compareToIgnoreCase("new")==0){
				inner1:
				while(true){
					try{
						String recStr = JOptionPane.showInputDialog(null, "Please input your an ID number(1-10): ");
						if(recStr == null){
							System.exit(0);
						}
						recLocation = Integer.parseInt(recStr);
						if(recLocation>10){
							throw new NumberFormatException();
						}
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "The ID number you entered is not valid.\nPlease enter an integer(1-10)!");
						continue inner1;
					}
					break inner1;
				}
				Description = JOptionPane.showInputDialog(null,"Please input a description: ");
				if(Description == null){
					System.exit(0);
				}
				inner2:
				while(true){
					try{
						String nohStr = JOptionPane.showInputDialog(null, "Please input your number_on_hand: ");
						if(nohStr == null){
							System.exit(0);
						}
						number_on_hand = Integer.parseInt(nohStr);
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "The ID number you entered is not valid.\nPlease enter an integer!");
						continue inner2;
					}
					break inner2;
				}
				if(recLocation == 0){
					recLocation = 1;
				}
				String id = ""+recLocation;
				int idlength = id.length();
				if(idlength<=7){
					for(int i=0;i<(7-idlength);i++){
						id = " "+ id;
					}
				}else{
					id = id.substring(0,6);
				}
				String noh = ""+number_on_hand;
				int nohlength = noh.length();
				if(nohlength<=5){
					for(int i=0;i<(5-nohlength);i++){
						noh = " "+ noh;
					}
				}else{
					noh = noh.substring(0,4);
				}
				int descriptionlength = Description.length();
				if(descriptionlength>32){
					Description=Description.substring(0,32);
				}else{
					for(int i=0;i<(32-descriptionlength);i++){
						Description = " "+ Description;
					}
				}
				Description = id + Description + noh;
				store.seek((RECORD_LENGTH+2)*(recLocation-1));
				store.writeUTF(Description);
			}
			if(cmd.compareToIgnoreCase("old")==0){
				Description = "";
				inner3:
				while(true){
					try{
						where = JOptionPane.showInputDialog(null,"Input record(ID) number:");
						recLocation = Integer.parseInt(where);
						if(recLocation>10){
							throw new NumberFormatException();
						}
					}
					catch(NumberFormatException e){
						JOptionPane.showMessageDialog(null, "The number you entered is not valid.\nPlease enter your record(ID) number!");
						continue inner3;
					}
					break inner3;
				}
				store.seek((RECORD_LENGTH+2)*(recLocation-1));
				Description = store.readUTF();
				//System.out.println(Description + Description.length());
				String DescriptionToShow = "";
				String NOHToShow = "";
				for(int i = 7;i<39;i++){
					if(Description.charAt(i)!= ' '){
						DescriptionToShow = Description.substring(i,39);
						break;
					}
				}
				for(int i = 40; i<44;i++){
					if(Description.charAt(i)!= ' '){
						NOHToShow = Description.substring(i,44);
						break;
					}
				}
				JOptionPane.showMessageDialog(null, "Record Description: "+ DescriptionToShow + "\n"
						+ "Number on hand: " + NOHToShow);
			}
		}
	}

}


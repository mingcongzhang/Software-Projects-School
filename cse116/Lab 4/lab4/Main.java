package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	private static String _des,_type,_name;
	private static int _alti,_speed;
	
	public static void main(String[] args){
		
		String cmd = "";
		ATCControl a = new ATCControl(20);
		int counter = 0;
		
		while(cmd.compareToIgnoreCase("end")!=0){
			
			System.out.println("Please input a command: ");
			Scanner sc = new Scanner(System.in);
			cmd = sc.next();
			
			if(cmd.compareToIgnoreCase("Entering")==0||cmd.compareToIgnoreCase("entering")==0){
				
					while(true){
						try{
							System.out.println("Please choose the aircraft type: ");
							System.out.println("a.Wide-body airliner\nb.Regional airliner\nc.Private plane\nd.Military\ne.Cargo only\nf.Unknown");
							_type = sc.next();
							if(!_type.equals("a")&&!_type.equals("b")&&!_type.equals("c")&&!_type.equals("d")&&!_type.equals("e")&&!_type.equals("f")){
								throw new InputMismatchException();
							}
						}
						catch(InputMismatchException e){
							System.out.println("Your input is invalid!\nPlease choose from the list.");
							continue;
						}
						break;
					}
				
					while(true){
						try{
							System.out.println("Please input the altitude(Meter): ");
							_alti = Integer.parseInt(sc.next());
						}
						catch(NumberFormatException e){
							System.out.println("Your input is invalid! Please input an integer.\n");
							continue;
						}
						break;
					}
				
					while(true){
						try{
							System.out.println("Please input the speed(Meter): ");
							_speed = Integer.parseInt(sc.next());
						}
						catch(NumberFormatException e){
							System.out.println("Your input is invalid! Please input an integer.\n");
							continue;
						}
						break;
					}
				
				System.out.println("Please input the name: ");
				_name = sc.next();
				_des = _type + "*" + _alti + "#" + _speed + "?" + _name;
				counter++;
				a.put(_des, counter);
				System.out.println();
			}
			if(cmd.compareToIgnoreCase("Leaving")==0||cmd.compareToIgnoreCase("leaving")==0){
				String name;
				System.out.println("Please enter the aircraft name: ");
				name = sc.next();
				for(int i = 1; i <= 20;i++){
					int j = a.get(i).indexOf("?");
					String na = a.get(i).substring(j+1);
					if(name.equals(na)){
						a.put("", i);
					}
				}
				System.out.println("The specified aircraft has been removed from your problem.");
				System.out.println();
			}
			if(cmd.compareToIgnoreCase("Show")==0||cmd.compareToIgnoreCase("show")==0){
				String name;
				System.out.println("Please enter the aircraft name: ");
				name = sc.next();
				for(int i = 1; i <= 20;i++){
					int j = a.get(i).indexOf("?");
					String na = a.get(i).substring(j+1);
					if(name.equals(na)){
						String type = ""+a.get(i).charAt(0);
						int k = a.get(i).indexOf("*");
						int l = a.get(i).indexOf("#");
						String alti = a.get(i).substring(k+1,l);
						String speed = a.get(i).substring(l+1,j);
						if(type.equals("a")){
							type = "Wide-body airliner";
						}else if(type.equals("b")){
							type = "Regional airliner";
						}else if(type.equals("c")){
							type = "Private plane";
						}else if(type.equals("d")){
							type = "Military";
						}else if(type.equals("e")){
							type = "Cargo only";
						}else{
							type = "Unknown";
						}
						System.out.println("Type: "+ type);
						System.out.println("Altitude: "+ alti);
						System.out.println("Speed: "+speed);
						System.out.println("Name: "+name);
						break;
					}
				}
				System.out.println();
			}
			if(cmd.compareToIgnoreCase("Display")==0||cmd.compareToIgnoreCase("display")==0){
				for(int i = 1; i <= 20;i++){
					int j = a.get(i).indexOf("?");
					if(!a.get(i).equals(null)&&!a.get(i).equals("")){
						String type = ""+a.get(i).charAt(0);
						int k = a.get(i).indexOf("*");
						int l = a.get(i).indexOf("#");
						String alti = a.get(i).substring(k+1,l);
						String speed = a.get(i).substring(l+1,j);
						String name = a.get(i).substring(j+1);
						if(type.equals("a")){
							type = "Wide-body airliner";
						}else if(type.equals("b")){
							type = "Regional airliner";
						}else if(type.equals("c")){
							type = "Private plane";
						}else if(type.equals("d")){
							type = "Military";
						}else if(type.equals("e")){
							type = "Cargo only";
						}else{
							type = "Unknown";
						}
						System.out.println("Type: "+ type);
						System.out.println("Altitude: "+ alti);
						System.out.println("Speed: "+speed);
						System.out.println("Name: "+name);
						System.out.println("-----------------------");
					}
				}
			}
		}
		
	}
	

}

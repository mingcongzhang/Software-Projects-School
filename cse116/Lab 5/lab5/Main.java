package lab5;

import java.util.Scanner;

public class Main {
	
	private static node nd;
	private static String cmd;
	private static int a,counter;
	
	public static void main(String[] args){
		counter = 0;
		nd = new node(50);
		cmd = "";	
		while(cmd.compareToIgnoreCase("end")!=0){
			
			System.out.println("Please input a command: ");
			Scanner sc = new Scanner(System.in);
			cmd = sc.next();
			System.out.println();
			
			if(cmd.compareToIgnoreCase("insert")==0||cmd.compareToIgnoreCase("Insert")==0){
				while(true){
					try{
						System.out.println("Please insert an integer: ");
						a = Integer.parseInt(sc.next());
					}
					catch(NumberFormatException e){
						System.out.println("Your input is invalid. Please insert an integer!\n");
						continue;
					}
					break;
				}
				if(counter == 0){
					node nd1 = new node(a);
					nd = nd1;
					counter++;
				}else{
					insert(a,nd);
				}		
				System.out.println();
			}
			if(cmd.compareToIgnoreCase("display")==0||cmd.compareToIgnoreCase("Display")==0){
				display(nd);
				System.out.println();
			}
		}
	}
	
	public static void insert(int n, node vtx){
		if(vtx.getValue() > n){
			if(vtx.left == null){
				vtx.left = new node(n);
				return;
			}else{
				insert(n,vtx.left);
			}
		}else{
			if(vtx.right == null){
				vtx.right = new node(n);
				return;
			}else{
				insert(n,vtx.right);
			}
		}
	}

	public static void display(node node) {
		  if (node != null) {
			  display(node.right);
			  
			  System.out.println(node.value);
			  display(node.left);
		  }
	}
	
	static class node {	
		private int value;
		public node left,right = null;	
		public node(int v){
			value = v;
		}
		public int getValue(){
			return value;
		}
	}
}

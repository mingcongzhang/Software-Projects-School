package lab1;

import java.util.*;

public class calculator {
	
	public static void main(String[] args){
		
			boolean repeat = true;
			do{
			System.out.println("Usage: operand1 operator operand2");
			System.out.println("       (operands are integers)");
			System.out.println("       (operators: + - * /)");
			
			int result = 0;
			int operand1 = 0;
			int operand2 = 0;
			String operator = "";
			char op = ' ';
			do{
				Scanner in = new Scanner(System.in);
			try{
				operand1 = in.nextInt();
				operator = in.next();
				op = operator.charAt(0);
				if(op!='+' && op!='-' && op!='*' && op!='/'){
					throw new EmptyStackException();
				}
				operand2 = in.nextInt();
				if(operand2 == 0 && op == '/'){
					throw new ArithmeticException();
				}
				}
			catch(InputMismatchException e){
				System.out.println("You typed a non-numeric operand. Please re-enter:");
				repeat = true;
				continue;
			}
			catch(ArithmeticException e){
				System.out.println("You typed a divider of 0.  Please re-enter:");
				repeat = true;
				continue;
			}
			catch(EmptyStackException e){
				System.out.println("You typed a unknown operator other than '+  - * /'.  Please re-enter:");
				repeat = true;
				continue;
        	}
			switch(op){
				case '+': result = operand1 + operand2;
				break;
				case '-': result = operand1 - operand2;
				break;
				case '*': result = operand1 * operand2;
				break;
				case '/': result = operand1 / operand2;
				break;
			}
				System.out.println("Answer: " + operand1 + ' ' + op + ' ' + operand2 + " = " + result);
				System.out.println("Press 'e' to exit or any other key to continue...");
				if(in.nextLine().equals("E")||in.nextLine().equals("e")){
					System.exit(0);
				}
				repeat = false;
			} while (repeat);
			} while (true);	
	}

}

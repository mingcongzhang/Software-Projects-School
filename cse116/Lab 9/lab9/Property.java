package lab9;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Property {
	
	public int _numberStories, _age, _id;
	public String _address = "";
	public JFrame frame = new JFrame("ZMC Inc.");
	public JFrame display = new JFrame("ZMC Inc.");
	public JTextField[] textList = new JTextField[4];
	
	public Property(int a){
		_id = a+1;
	}
	public void initialize(){
		JFrame a = new JFrame("ZMC Inc.");
		frame = a;
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		String[] name = {"Address","Number of stories","Age"};
		JPanel jpForWhole = new JPanel();	
		JPanel jpForTitle = new JPanel();
		JLabel jll = new JLabel("Property ID: "+_id);
		jpForTitle.setLayout((new FlowLayout(FlowLayout.CENTER)));
		jpForTitle.add(jll);
		jpForWhole.add(jpForTitle);
		frame.getContentPane().setLayout(new GridLayout(0,1));;
		jpForWhole.setLayout(new GridLayout(0,1));
		for(int i=0;i<3;i++){
			JPanel jp = new JPanel();		
			JLabel jl = new JLabel(name[i]+": ");
			JTextField jt = new JTextField(15);
			jp.add(jl);
			jp.add(jt);
			jp.setLayout((new FlowLayout(FlowLayout.LEFT)));
			jpForWhole.add(jp);
			textList[i] = new JTextField();
			textList[i] = jt;
		}
		JPanel jpT = new JPanel();
		jpT.setLayout((new FlowLayout(FlowLayout.CENTER)));
		frame.add(jpForWhole);
	}
	public void display(){
		JFrame a = new JFrame("ZMC Inc.");
		display = a;
		display.setLocationRelativeTo(null);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setResizable(false);
		display.getContentPane().setLayout(new GridLayout(0,1));
		JPanel jpForTitle = new JPanel();
		JLabel jll = new JLabel("Retrieved information for property ID: "+_id);
		jpForTitle.setLayout((new FlowLayout(FlowLayout.CENTER)));
		jpForTitle.add(jll);
		display.add(jpForTitle);
		JLabel jl2 = new JLabel("Address: "+_address);
		display.add(jl2);
		JLabel jl3 = new JLabel("Number of stories: "+_numberStories);
		display.add(jl3);
		JLabel jl4 = new JLabel("Age: "+_age);
		display.add(jl4);
	}
	public int getId(){
		return _id;
	}
	public void calcTaxrate(){	
	}
}

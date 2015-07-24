package lab9;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ResidentialProperty extends Property{

	private boolean _multiple, _externalWater;
	private int _porchSize, _yardSize;	
	private Choice[] choiceList = new Choice[2];
	private JTextField[] textList2 = new JTextField[2];
	private JButton b = new JButton("Submit");
	public ResidentialProperty(int a){
		super(a);	
	}
	
	public void initialize(){
		super.initialize();
		String[] name = {"Multiple dwelling available","External water faucet available","Porch size","Yard size"};
		JPanel jpForWhole = new JPanel();	
		jpForWhole.setLayout(new GridLayout(0,1));
		for(int i=0;i<2;i++){
			Choice ch= new Choice();
			ch.add("Yes");
			ch.add("No");
			JPanel jp = new JPanel();		
			JLabel jl = new JLabel(name[i]+": ");
			jp.add(jl);
			jp.add(ch);
			jp.setLayout((new FlowLayout(FlowLayout.LEFT)));
			jpForWhole.add(jp);
			choiceList[i] = ch;
		}
		for(int i=0;i<2;i++){
			JPanel jp = new JPanel();		
			JLabel jl = new JLabel(name[i+2]+": ");
			JTextField jt = new JTextField(15);
			jp.add(jl);
			jp.add(jt);
			jp.setLayout((new FlowLayout(FlowLayout.LEFT)));
			jpForWhole.add(jp);
			textList2[i] = jt;
		}
		JPanel jpT = new JPanel();
		jpT.setLayout((new FlowLayout(FlowLayout.CENTER)));
		b.addActionListener(new ButtonListener());
		jpT.add(b);
		jpForWhole.add(jpT);
		frame.add(jpForWhole);
		frame.pack();
		frame.setVisible(true);
	}
	public void display(){
		
		super.display();
		JLabel jl5 = new JLabel("Purpose: "+"Residential");
		display.add(jl5);
		String m = "";
		if(_multiple){
			m = "Yes";
		}else{
			m = "No";
		}
		JLabel jl = new JLabel("Multiple dwelling available: "+m);
		display.add(jl);
		String e = "";
		if(_externalWater){
			e = "Yes";
		}else{
			e = "No";
		}
		JLabel jl2 = new JLabel("External water faucet available: "+e);
		display.add(jl2);
		JLabel jl3 = new JLabel("Porch size: "+_porchSize);
		display.add(jl3);
		JLabel jl4 = new JLabel("Yard size: "+_yardSize);
		display.add(jl4);
		JButton confirm = new JButton("OK");
		confirm.addActionListener(new GeneralButtonListener(display));
		JPanel jpb = new JPanel();
		jpb.setLayout((new FlowLayout(FlowLayout.CENTER)));
		jpb.add(confirm);
		display.add(jpb);
		display.pack();
		display.setVisible(true);
	}
	
	
	private class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String s = "";
			
			 _address = textList[0].getText();
			if(choiceList[0].getSelectedIndex()==0){
				_multiple =true;
			}else{
				_multiple =false;
			}
			if(choiceList[1].getSelectedIndex()==0){
				_externalWater = true;
			}else{
				_externalWater = false;
			}
			try{
				_porchSize = Integer.parseInt(textList2[0].getText());
				_yardSize = Integer.parseInt(textList2[1].getText());
				_numberStories = Integer.parseInt(textList[1].getText());
				_age = Integer.parseInt(textList[2].getText());
				 
			}
			catch(NumberFormatException e1){
					 JFrame jf = new JFrame("ZMC Inc.");
					 jf.setLocationRelativeTo(null);
					 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 jf.setResizable(false);
					 jf.getContentPane().setLayout(new GridLayout(0,1));
					 JLabel jl = new JLabel("The format of your input is wrong!");
					 JButton b3 = new JButton("OK");
					 b3.addActionListener(new GeneralButtonListener(jf));
					 JPanel jp = new JPanel();
					 jp.add(b3);
					 jf.getContentPane().add(jl);
					 jf.getContentPane().add(jp);
					 jf.pack();
					 jf.setVisible(true);	
					 s = e1.getLocalizedMessage();
			}
			if(!s.equals("For input string: \""+textList[1].getText()+"\"")&&!s.equals("For input string: \""+textList[2].getText()+"\"")&&!s.equals("For input string: \""+textList2[0].getText()+"\"")&&!s.equals("For input string: \""+textList2[1].getText()+"\"")){
					 JFrame jf = new JFrame("ZMC Inc.");
					 jf.setLocationRelativeTo(null);
					 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					 jf.setResizable(false);
					 jf.getContentPane().setLayout(new GridLayout(0,1));
					 JLabel jl = new JLabel("Information submitted!");
					 JButton b3 = new JButton("OK");
					 b3.addActionListener(new GeneralButtonListener(jf));
					 JPanel jp = new JPanel();
					 jp.add(b3);
					 jf.getContentPane().add(jl);
					 jf.getContentPane().add(jp);
					 jf.pack();
					 jf.setVisible(true);
					 frame.getContentPane().removeAll();
					 frame.dispose(); 
			}	
		}	
	}

}

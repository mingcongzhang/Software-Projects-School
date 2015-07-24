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

public class CommercialProperty extends Property{
	private int _mode;
	private boolean _food, _resAlso, _parking;
	private JButton b = new JButton("Submit");
	private Choice[] choiceList = new Choice[4];
	public CommercialProperty(int a){
		super(a);
	}
	
	public void initialize(){
		super.initialize();	
		String[] name = {"Mode","Food service available","A residence is combined","Off-street parking available"};
		JPanel jpForWhole = new JPanel();	
		jpForWhole.setLayout(new GridLayout(0,1));
		Choice ch= new Choice();
		ch.add("Office");
		ch.add("Retail");
		JPanel jp = new JPanel();
		JLabel jl = new JLabel(name[0]+": ");
		jp.add(jl);
		jp.add(ch);
		jp.setLayout((new FlowLayout(FlowLayout.LEFT)));
		jpForWhole.add(jp);
		choiceList[0] = ch;
		for(int i=1;i<4;i++){
			Choice ch1= new Choice();
			ch1.add("Yes");
			ch1.add("No");
			JPanel jp1 = new JPanel();		
			JLabel jl1 = new JLabel(name[i]+": ");
			jp1.add(jl1);
			jp1.add(ch1);
			jp1.setLayout((new FlowLayout(FlowLayout.LEFT)));
			jpForWhole.add(jp1);
			choiceList[i] = ch1;
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
		JLabel jl5 = new JLabel("Purpose: "+"Commercial");
		display.add(jl5);
		String m = "";
		if(_mode==0){
			m = "Office";
		}else{
			m = "Retail";
		}
		JLabel jl = new JLabel("Mode: "+m);
		display.add(jl);
		String e = "";
		if(_food){
			e = "Yes";
		}else{
			e = "No";
		}
		JLabel jl1 = new JLabel("Food service available: "+e);
		display.add(jl1);
		String f = "";
		if(_resAlso){
			f = "Yes";
		}else{
			f = "No";
		}
		JLabel jl2 = new JLabel("A residence is combined: "+f);
		display.add(jl2);
		String g = "";
		if(_parking){
			g = "Yes";
		}else{
			g = "No";
		}
		JLabel jl3 = new JLabel("Off-street parking available: "+g);
		display.add(jl3);
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
			try{
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
			if(!s.equals("For input string: \""+textList[1].getText()+"\"")&&!s.equals("For input string: \""+textList[2].getText()+"\"")){
				_mode = choiceList[0].getSelectedIndex();
				if(choiceList[1].getSelectedIndex()==0){
					_food = true;
				}else{
					_food = false;
				}
				if(choiceList[2].getSelectedIndex()==0){
					_resAlso = true;
				}else{
					_resAlso = false;
				}
				if(choiceList[3].getSelectedIndex()==0){
					_parking = true;
				}else{
					_parking = false;
				}
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
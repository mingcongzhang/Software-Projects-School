package lab9;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RealEstate{
	public static Property[] database = new Property[200];
	public static int counter = 0;
	private static JFrame frameM = new JFrame("ZMC Inc.");
	public static void main(String[] args){	
		frameM.setLocationRelativeTo(null);
		frameM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameM.setResizable(false);
		JLabel label = new JLabel("Please choose an option:");
		JButton bNew = new JButton("New");
		JButton bOld = new JButton("Old");
		JButton bEnd = new JButton("End");
		bNew.addActionListener(new ButtonListener(1));
		bOld.addActionListener(new ButtonListener(2));
		bEnd.addActionListener(new ButtonListener(3));
		JPanel jpForButton = new JPanel();
		frameM.getContentPane().add(label, BorderLayout.NORTH);
		jpForButton.add(bNew, BorderLayout.WEST);
		jpForButton.add(bOld, BorderLayout.CENTER);
		jpForButton.add(bEnd, BorderLayout.EAST);
		frameM.add(jpForButton);
		frameM.pack();
		frameM.setVisible(true);
	}
	private static class ButtonListener implements ActionListener{
		private int value;
		public ButtonListener(int n){
			value = n;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if(value == 1){		
				JFrame jf = new JFrame("ZMC Inc.");
				jf.setLocationRelativeTo(null);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.setResizable(false);
				jf.getContentPane().setLayout(new GridLayout(0,1));
				JPanel jpForTitle = new JPanel();
				JLabel jll = new JLabel("Please choose property type: ");
				Choice ch = new Choice();
				ch.add("Residential");
				ch.add("Commercial");
				jpForTitle.add(jll);
				jpForTitle.add(ch);
				JButton confirm = new JButton("OK");
				confirm.addActionListener(new GeneralButtonListener(jf,ch));
				JPanel jpb = new JPanel();
				jpb.setLayout((new FlowLayout(FlowLayout.CENTER)));
				jpb.add(confirm);
				jf.add(jpForTitle);
				jf.add(jpb);
				jf.pack();
				jf.setVisible(true);
				
			}
			if(value == 2){
				int num = 0;
				while(true){
					try{
						String cmd = JOptionPane.showInputDialog("Please enter the property ID", null);
						num = Integer.parseInt(cmd);
						if((num-1)<0||(num-1)>200){
							throw new NumberFormatException();
						}else if(database[num-1]==null){
							throw new NumberFormatException();
						}	
					}
					catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(null,"Please enter a valid ID number!");
						continue;
					}
					break;
				}
				database[num-1].display();	
			}
			if(value == 3){
				System.exit(0);
			}
		}
	}
}

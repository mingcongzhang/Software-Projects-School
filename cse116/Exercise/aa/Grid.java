package aa;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid extends JFrame{
	private final int WINDOW_WIDTH = 400;
	private final int WINDOW_HEIGHT = 400;
	public Grid(){
		super("Grid Layout");
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,3));
		
		JButton b1 = new JButton("Button 1");
		JButton b2 = new JButton("Button 2");
		JButton b3 = new JButton("Button 3");
		JButton b4 = new JButton("Button 4");
		JButton b5 = new JButton("Button 5");
		JButton b6 = new JButton("Button 6");
		
		JLabel l1 = new JLabel("This is cell 1");
		JLabel l2 = new JLabel("This is cell 2");
		JLabel l3 = new JLabel("This is cell 3");
		JLabel l4 = new JLabel("This is cell 4");
		JLabel l5 = new JLabel("This is cell 5");
		JLabel l6 = new JLabel("This is cell 6");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		
		p1.add(l1);
		p2.add(l2);
		p3.add(l3);
		p4.add(l4);
		p5.add(l5);
		p6.add(l6);
		
		p1.add(b1);
		p2.add(b2);
		p3.add(b3);
		p4.add(b4);
		p5.add(b5);
		p6.add(b6);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Grid();
	}
}

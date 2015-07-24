package lab9;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GeneralButtonListener implements ActionListener{
	private JFrame jf;
	private Choice ch;
	public GeneralButtonListener(JFrame j) {
		jf = j;
	}
	public GeneralButtonListener(JFrame j,Choice ch) {
		this.ch = ch;
		jf = j;
	}
	public void actionPerformed(ActionEvent e) {
		if(ch!=null){
			if(ch.getSelectedIndex()==0){
				ResidentialProperty rp = new ResidentialProperty(RealEstate.counter);
				rp.initialize();
				RealEstate.database[RealEstate.counter]=rp;
				RealEstate.counter++;
			}else{
				CommercialProperty cp = new CommercialProperty(RealEstate.counter);
				cp.initialize();
				RealEstate.database[RealEstate.counter]=cp;
				RealEstate.counter++;
			}
		}
		jf.removeAll();
		jf.dispose();	
	}
}

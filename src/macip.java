import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class macip extends JFrame{
	
		JLabel lbl1=new JLabel("   IP Adress1") ;
		JLabel lbl2=new JLabel("   IP Adress2") ;
		JLabel lblmac=new JLabel("   MAC Adress") ;
		JTextField txt1 =new JTextField(15);
		JTextField txt2 =new JTextField(15);
		JTextField txt3 =new JTextField(15);
		JButton btnok=new JButton("OK");
		JButton btncancel=new JButton("Cancel");
		JPanel pnl1=new JPanel();
		JPanel pnl2=new JPanel();
		JFrame frm=new JFrame();
		
		
		void create1(){
			frm.setLayout(new BorderLayout());
			pnl1.setLayout(new GridLayout(3,2));
			pnl2.setLayout(new GridLayout(4,1));
			pnl1.add(lbl1);
			pnl1.add(txt1);
			pnl1.add(lbl2);
			pnl1.add(txt2);
			pnl1.add(lblmac);
			pnl1.add(txt3);
			pnl2.add(btnok);
			pnl2.add(btncancel);
			frm.add(pnl1,BorderLayout.WEST);
			frm.add(pnl2,BorderLayout.EAST);
			frm.pack();
			frm.setVisible(true);
		    frm.setResizable(false);
		    frm.setLocationRelativeTo(null);

	}}




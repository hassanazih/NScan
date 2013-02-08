
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class fenetreexporte extends JFrame{
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JFrame frm= new JFrame();
	JButton btnok=new JButton("OK");
	JButton btncancel=new JButton("Cancel");
	JRadioButton radioButton1 = new JRadioButton();
	JRadioButton radioButton2 = new JRadioButton();
	JRadioButton radioButton3 = new JRadioButton();
	JRadioButton radioButton4 = new JRadioButton();
	JRadioButton radioButton5 = new JRadioButton();

	JCheckBox checkBox1 = new JCheckBox();
	JCheckBox checkBox2 = new JCheckBox();
	JCheckBox checkBox3 = new JCheckBox();
	JCheckBox checkBox4 = new JCheckBox();
	JCheckBox checkBox5 = new JCheckBox();

	void create3(final JTable table){
	frm.setLayout(new BorderLayout());
	panel1.setLayout(new GridLayout(0,1));
	panel2.setLayout(new GridLayout(0,1));
	panel3.setLayout(new FlowLayout());
    Border cadre1 = BorderFactory.createTitledBorder("MAC Adresse Format" );
    panel2.setBorder(cadre1);
	radioButton1.setText("Text File ");
	radioButton2.setText("Excel File     ");
	radioButton3.setText("ffffffffffff");
	radioButton4.setText("ff:ff:ff:ff:ff:ff");
	radioButton5.setText("ff-ff-ff-ff-ff-ff");
	checkBox1.setText("Num");
	checkBox2.setText("Adresse IP");
	checkBox3.setText("Adresse MAC");
	checkBox4.setText("Host Name");
	checkBox5.setText("N° de Port");
	btnok.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		if(radioButton1.isSelected())
		{
			try {
				DataOutputStream dos=new DataOutputStream(new FileOutputStream("Liste.txt"));
					for(int i=0;i<table.getRowCount();i++)
							{
						
							if(checkBox1.isSelected())dos.writeChars("Num: "+table.getValueAt(i, 0).toString()+"\t");
							if(checkBox2.isSelected())dos.writeChars("@IP: "+table.getValueAt(i, 1).toString()+"\t");
							if(checkBox4.isSelected())dos.writeChars("Host Name: "+table.getValueAt(i, 3).toString()+"\t");
							if(checkBox5.isSelected())dos.writeChars("N° de port: "+table.getValueAt(i, 4).toString()+"\t");
							if(checkBox3.isSelected())
								{
								if(radioButton3.isSelected())
								{
									String str=table.getValueAt(i, 2).toString();
									StringTokenizer st=new StringTokenizer(str,"-");
									StringBuffer sb=new StringBuffer("@MAC: ");
									for(int j=0;j<6;j++)
										sb.append(st.nextToken());
									dos.writeChars(sb.toString()+"\t");
								}
								else if(radioButton4.isSelected())
								{
									String str=table.getValueAt(i, 2).toString();
									StringTokenizer st=new StringTokenizer(str,"-");
									StringBuffer sb=new StringBuffer("@MAC: ");
									for(int j=0;j<6;j++)
									{
									if(j!=0) sb.append(":");
									sb.append(st.nextToken());
									}
									dos.writeChars(sb.toString()+"\t");
								}
								else
									dos.writeChars("@MAC: "+table.getValueAt(i, 2).toString()+"\t");
								}
							
							}
					dos.close();
			}
								
			 catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(frm, "Enregistrement terminé avec succées");
		}			
			else 
			{
			WritableWorkbook source;
			try {
				source = Workbook.createWorkbook(new File("Liste.xls"));
				WritableSheet sheet1=source.createSheet("Liste1",0);
				
				if(checkBox1.isSelected())sheet1.addCell(new Label(0,0,"Num"));
				if(checkBox2.isSelected())sheet1.addCell(new Label(1,0,"Adresse IP"));
				if(checkBox4.isSelected())sheet1.addCell(new Label(3,0,"Host Name"));
				if(checkBox5.isSelected())sheet1.addCell(new Label(4,0,"N° de Port"));
				if(checkBox3.isSelected())sheet1.addCell(new Label(2,0,"Adresse MAC"));
					
				for(int i=0;i<table.getRowCount();i++)
				{
				if(checkBox1.isSelected())sheet1.addCell(new Label(0,i+1,table.getValueAt(i,0).toString()));
				if(checkBox2.isSelected())sheet1.addCell(new Label(1,i+1,table.getValueAt(i,1).toString()));
				if(checkBox4.isSelected())sheet1.addCell(new Label(3,i+1,table.getValueAt(i,3).toString()));
				if(checkBox5.isSelected())sheet1.addCell(new Label(4,i+1,table.getValueAt(i,4).toString()));
				if(checkBox3.isSelected())
					{
					if(radioButton3.isSelected())
					{
						String str=table.getValueAt(i, 2).toString();
						StringTokenizer st=new StringTokenizer(str,":");
						StringBuffer sb=new StringBuffer("");
						for(int j=0;j<6;j++)
							sb.append(st.nextToken());
						sheet1.addCell(new Label(2,i+1,sb.toString()));
					}
					else if(radioButton4.isSelected())
					{
						String str=table.getValueAt(i, 2).toString();
						StringTokenizer st=new StringTokenizer(str,":");
						StringBuffer sb=new StringBuffer("");
						for(int j=0;j<6;j++)
						{
						if(j!=0) sb.append(":");
						sb.append(st.nextToken());
						}
						sheet1.addCell(new Label(2,i+1,sb.toString()));
					}
					else
						sheet1.addCell(new Label(2,i+1,table.getValueAt(i,2).toString()));
					}
				
				}

				source.write();
				source.close();
				JOptionPane.showMessageDialog(frm, "Enregistrement terminé avec succées");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		
			}	
frm.setVisible(false);
		}
		
	});
	panel1.add(radioButton1);
	panel1.add(radioButton2);
	panel1.add(checkBox1);
	panel1.add(checkBox2);
	panel1.add(checkBox3);
	panel1.add(checkBox4);
	panel1.add(checkBox5);
	
	panel2.add(radioButton3);
	panel2.add(radioButton4);
	panel2.add(radioButton5);
	
	panel3.add(btnok);
	btncancel.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		frm.setVisible(false);	
		}
	});
	panel3.add(btncancel);
	
	ButtonGroup bg1 = new ButtonGroup();
	bg1.add(radioButton1);
	bg1.add(radioButton2);
	ButtonGroup bg2 = new ButtonGroup();
	bg2.add(radioButton3);
	bg2.add(radioButton4);
	bg2.add(radioButton5);
	frm.add(panel1, BorderLayout.WEST);
	frm.add(panel2, BorderLayout.CENTER);
	frm.add(panel3, BorderLayout.SOUTH);
	frm.setSize(370,250);
	frm.setTitle("Exporte Format");
	frm.setVisible(true);
    frm.setResizable(false);
    frm.setLocationRelativeTo(null);
    
	
	
	
}}
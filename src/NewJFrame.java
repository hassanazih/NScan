import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

import jpcap.NetworkInterface;
import jpcap.JpcapCaptor;



public class NewJFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton jButton_mactoip;
	private JButton jButton_quitter;  
	private JProgressBar jProgressBar1;
	private JButton jButton_autoscan;
	private JSeparator jSeparator3;
	private JScrollPane jScrollPane1;
	private JTable jTable1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator1;
	private JButton jButton_clear;
	private JButton jButton_export;
	private JButton jButton_iptomac;
	static DefaultTableModel jTable1Model ;
	static int nb=1;

	/**
	* Auto-generated main method to display this JFrame
	*/

	
	public NewJFrame() {
		super("Gestion Des Adresses Mac");
		initGUI();
	}
	
	private void initGUI() {
		try {
			jScrollPane1 = new JScrollPane(jTable1);
				jTable1Model= 
					new DefaultTableModel(
							new String[][] {  },
							new String[] { "N°", "Adresse IP", "Adresse MAC", "Hoste Name","N° de port" });
				jTable1 = new JTable();
				jScrollPane1.setViewportView(getJTable1());
				jTable1.setModel(jTable1Model);
				jTable1.setPreferredSize(new java.awt.Dimension(827, 158));
			
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
			}
		
			{
				
				jButton_iptomac = new JButton();
				jButton_iptomac.setText("IP -> Mac");
				jButton_iptomac.setIcon(new ImageIcon("images\\recherche.gif"));
				jButton_iptomac.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
					String str=JOptionPane.showInputDialog("Veuillez saisir l'adresse IP (Sous Format: XXX.XXX.XXX.XXX) : ");
					NetworkInterface[] devices=JpcapCaptor.getDeviceList();
					ipmac request;
					try {
						request = new ipmac(str,devices[0]);
						request.run();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Runtime.getRuntime().exec("arp -d");	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					}
				});
				}
			
			{
				jButton_mactoip = new JButton();
				jButton_mactoip.setText("Mac -> IP");
				jButton_mactoip.setIcon(new ImageIcon("images\\recherche.gif"));
				jButton_mactoip.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
					
						macip f1=new macip();
						f1.create1();
					}
				
				});	
			}
			
			{
				jButton_export = new JButton();
				jButton_export.setText("Export");
				jButton_export.setIcon(new ImageIcon("images\\exporter.gif"));
				jButton_export.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						fenetreexporte f3 = new fenetreexporte();
						f3.create3(jTable1);
						
						
					}
				});
			}
			{
			}
			{
				jButton_clear = new JButton();
				jButton_clear.setText("Clear");
				jButton_clear.setIcon(new ImageIcon("images\\supprimer.gif"));
				jButton_clear.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						int result=JOptionPane.showConfirmDialog(null,"Are you sure to clean the selected items?","Application Reseau",JOptionPane.YES_NO_OPTION);
						if(result==0){
						      
							/*for(int i=0; i< jTable1Model.getRowCount(); i++)
			                       for(int j=0; j<jTable1Model.getColumnCount(); j++)
			                    	   jTable1Model.setValueAt("",i,j);*/
							while (jTable1Model.getRowCount()!=0){ 
								jTable1Model.removeRow(0); 
						        }
							
						    }
						
						 
					}
				});
			}
			{
				jSeparator1 = new JSeparator();
			}
			{
				jSeparator2 = new JSeparator();
			}
			
				jSeparator3 = new JSeparator();
			{
				jButton_quitter = new JButton();
				jButton_quitter.setText("Quitter");
				//jButton_quitter.setSize(new Dimension(10, 40));
				jButton_quitter.setIcon(new ImageIcon("images\\sortie.gif"));
				jButton_quitter.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						 int result=JOptionPane.showConfirmDialog(null,"Are you sure to exit?","Application Reseau",JOptionPane.YES_NO_OPTION);
						    if(result==0){//reponse oui
						      
						      System.exit(1);
						    }
					
					}
				});
			}
			{
				jButton_autoscan = new JButton();
				jButton_autoscan.setText("Auto Scan");
				
				jButton_autoscan.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						ipmac request;
						String newstr;
						String str=JOptionPane.showInputDialog("Veuillez saisir l'id réseau (Sous Format: XXX.XXX.XXX.0) : ");	
						NetworkInterface[] devices=JpcapCaptor.getDeviceList();
						
						try {
							Runtime.getRuntime().exec("arp -d");
							jButton_autoscan.setEnabled(false);
							Thread p=new ProgressBar(devices[0],str,jProgressBar1,jButton_quitter,jButton_autoscan);
							p.start();
							} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				
			});
				}
			{
				jProgressBar1 = new JProgressBar(0,100);
				jProgressBar1.setStringPainted(true);
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(19, 19)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)

				    .addComponent(jButton_iptomac, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton_mactoip, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton_export, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton_clear, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(20)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(jSeparator1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jSeparator2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
				.addGap(28)
				.addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGap(27)
				.addGroup(thisLayout.createParallelGroup()
				    .addComponent(jProgressBar1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
				    .addComponent(jButton_autoscan, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(0, 18, Short.MAX_VALUE)
				.addComponent(jButton_quitter, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(57, 57)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jButton_autoscan, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)

				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addPreferredGap(jButton_autoscan, jSeparator1, LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				                .addGap(97)))
				        .addGap(18)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(jProgressBar1, GroupLayout.Alignment.LEADING, 0, 703, Short.MAX_VALUE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addPreferredGap(jProgressBar1, jButton_iptomac, LayoutStyle.ComponentPlacement.INDENT)
				                .addComponent(jButton_iptomac, 0, 109, Short.MAX_VALUE)
				                .addGap(27)
				                .addGroup(thisLayout.createParallelGroup()
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addComponent(jButton_mactoip,150,150,150)
				                        .addGap(40)
				                        .addComponent(jButton_export, 0, 109, Short.MAX_VALUE))
				                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                        .addGap(52)
				                        .addComponent(jButton_quitter, 150,150,150)
				                        .addGap(34)))
				                .addGap(35)
				                
				   
				                .addComponent(jButton_clear, 0, 109, Short.MAX_VALUE)
				                .addGap(0, 9, GroupLayout.PREFERRED_SIZE))))
				    .addComponent(jSeparator2, GroupLayout.Alignment.LEADING, 0, 830, Short.MAX_VALUE)
				    .addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, 0, 830, Short.MAX_VALUE)
				    .addComponent(jSeparator3, GroupLayout.Alignment.LEADING, 0, 830, Short.MAX_VALUE))
				.addContainerGap(47, 47));
			thisLayout.linkSize(SwingConstants.VERTICAL, new Component[] {jButton_autoscan, jProgressBar1});
			thisLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jButton_clear, jButton_mactoip, jButton_iptomac, jButton_export});
			pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public JButton getJButton_mactoip() {
		return jButton_mactoip;
	}
	
	public JButton getJButton_iptomac() {
		return jButton_iptomac;
	}
	
	public JButton getJButton_export() {
		return jButton_export;
	}
	
	
	public JButton getJButton_clear() {
		return jButton_clear;
	}
	
	public JSeparator getJSeparator2() {
		return jSeparator2;
	}
	
	public JTable getJTable1() {
		return jTable1;
	}
	
	public JSeparator getJSeparator3() {
		return jSeparator3;
	}
	
	public JButton getJButton_autoscan() {
		return jButton_autoscan;
	}
	
	public JProgressBar getJProgressBar1() {
		return jProgressBar1;
	}
	
	public JButton getJButton_quitter() {
		return jButton_quitter;
	}

}

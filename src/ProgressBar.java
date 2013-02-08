import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.event.*;

import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.ARPPacket;

public class ProgressBar extends Thread 
  {
  static JProgressBar maBarre;
  String newstr;
  String str;
  ipmac packet;
  NetworkInterface device;
  static JButton btn_quitter;
  static JButton btn_autoscan;
  
  
  public ProgressBar(NetworkInterface device,String str,JProgressBar maBaree,JButton btn_quitter,JButton btn_autoscan)
  {
	  this.str=str;
	  this.maBarre=maBaree;
	  this.btn_autoscan=btn_autoscan;
	  this.btn_quitter=btn_quitter;
	  this.device=device;
}
  
  
  
  @SuppressWarnings("deprecation")
public void run()
  {
	 
	  btn_quitter.setText("Wait ...");
	  btn_quitter.setEnabled(false);
			try {
				packet=new ipmac(str,device);
				packet.start();

			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						
			 
			
		
	  
  }
  }
  

  
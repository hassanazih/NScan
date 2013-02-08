import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;

import java.util.List;

import javax.swing.SwingUtilities;

import jpcap.JpcapCaptor;
import jpcap.packet.ARPPacket;
import jpcap.packet.EthernetPacket;


public class ipmac extends Thread{
	InetAddress ip;
	String adress;
	
	List<poste> l;
	JpcapCaptor jpcap;
	NetworkInterface device;
	EthernetPacket ether;
	ARPPacket arp;
	byte[] broadcast;
	JpcapSender sender;
	 InetAddress ancienip;
	 boolean isReseau;
		InetAddress srcip=null;
		
	 
	public ipmac(String adress,NetworkInterface device) throws IOException
	{
		this.adress=adress;
		broadcast=new byte[]{(byte)255,(byte)255,(byte)255,(byte)255,(byte)255,(byte)255};
		if(adress.endsWith(".0"))
		{
			isReseau=true;
		}
		else
		{
			isReseau=false;
			ip=InetAddress.getByName(adress);
		}
		this.device=device;
		jpcap = JpcapCaptor.openDevice(device, 2000, false, 20);
		jpcap.setFilter("arp", false);
		sender=jpcap.getJpcapSenderInstance();

		

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub*
		System.out.println("run");

		for(NetworkInterfaceAddress addr:device.addresses)
			if(addr.address instanceof InetAddress){
				srcip=addr.address;
				break;
			}
		Thread t=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				jpcap.loopPacket(-1, new ARP());
			}});

		t.start();
		
		if(!isReseau)
		{
			send(ip);
		}
		else
		{
			Runnable runner = new Runnable() {
		        public void run() {
		          int value = ProgressBar.maBarre.getValue();
		          ProgressBar.maBarre.setValue(value + 1);
			      ProgressBar.maBarre.setString("Progression : "+Math.floor(ProgressBar.maBarre.getPercentComplete()*100)+"%");
		        }
		      };
			for(int i=1;i<255;i++)
			{
				try {
					send(InetAddress.getByName(adress.substring(0,adress.lastIndexOf('.'))+"."+i));
					SwingUtilities.invokeAndWait(runner);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(5000);
				ProgressBar.btn_quitter.setText("Quitter");
				ProgressBar.btn_quitter.setEnabled(true);
				ProgressBar.btn_autoscan.setEnabled(true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void send(InetAddress ips)
	{

		arp=new ARPPacket();
		arp.hardtype=ARPPacket.HARDTYPE_ETHER;
		arp.prototype=ARPPacket.PROTOTYPE_IP;
		arp.operation=ARPPacket.ARP_REQUEST;
		arp.hlen=6;
		arp.plen=4;
		arp.sender_hardaddr=device.mac_address;
		arp.sender_protoaddr=srcip.getAddress();
		arp.target_hardaddr=broadcast;
		arp.target_protoaddr=ips.getAddress();
		ether=new EthernetPacket();
		ether.frametype=EthernetPacket.ETHERTYPE_ARP;
		ether.src_mac=device.mac_address;
		ether.dst_mac=broadcast;
		arp.datalink=ether;
			
		sender.sendPacket(arp);

	}

	
}

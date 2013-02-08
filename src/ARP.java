import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jpcap.PacketReceiver;
import jpcap.packet.ARPPacket;
import jpcap.packet.Packet;

class ARP implements PacketReceiver {
	private static List<poste> hosts;
	poste p;
	ArrayList ips;
	
	public static List<poste> getHosts() {
		return hosts;
	}

	public static void setHosts(List<poste> hosts) {
		ARP.hosts = hosts;
	}

	
	public ARP()
	{
		ips=new ArrayList<String>();
		hosts=new ArrayList<poste>();
	}

	public void receivePacket(Packet packet) {
		ARPPacket ap = (ARPPacket) packet;
		String ip=ap.getSenderProtocolAddress().toString().substring(1);
			try {
				p=new poste(ap.getSenderProtocolAddress().toString(),
						ap.getSenderHardwareAddress().toString(),InetAddress.getByName(ip).getHostName());
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if (!hosts.contains(p) && !ips.contains(ip)) {
			
			hosts.add(p);
			String[] row={String.valueOf(NewJFrame.nb++),p.getIp().substring(1),p.getMac(),p.getNom(),"80"};
			
			
				ips.add(ip);
				NewJFrame.jTable1Model.addRow(row);
				
				/*try {
					System.out.println("IP src : " + ap.getSenderProtocolAddress() + " Adresse MAC src : "
							+ ap.getSenderHardwareAddress()+" IP dest : "+ap.getTargetProtocolAddress()+" Mac dest :"
							+ap.getTargetHardwareAddress()+"Nom de la hote : "+InetAddress.getByName(ap.getSenderProtocolAddress().toString().substring(1)).getHostName());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			 
		}
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
}

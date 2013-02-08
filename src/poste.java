
public class poste {

	private String ip;
	private String mac;
	private String nom;
	
	
	public poste(String ip, String mac, String nom) {
		super();
		this.ip = ip;
		this.mac = mac;
		this.nom = nom;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}

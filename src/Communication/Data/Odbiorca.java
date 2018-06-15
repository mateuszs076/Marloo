package Communication.Data;

public class Odbiorca {
	
	private String nazwa;
	private String kraj;
	private String Miasto;
	private String adres;
	
	public Odbiorca(String nazwa, String kraj, String miasto, String adres) {
		super();
		this.nazwa = nazwa;
		this.kraj = kraj;
		Miasto = miasto;
		this.adres = adres;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getMiasto() {
		return Miasto;
	}

	public void setMiasto(String miasto) {
		Miasto = miasto;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
}

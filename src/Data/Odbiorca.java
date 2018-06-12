package Data;

public class Odbiorca {
	
	String nazwa;
	String kraj;
	String miasto;
	String adres;
	
	public Odbiorca(String nazwa, String kraj, String miasto, String adres) {
		super();
		this.nazwa = nazwa;
		this.kraj = kraj;
		this.miasto = miasto;
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
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}
}

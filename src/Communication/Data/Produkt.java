package Communication.Data;

public class Produkt {
	private String index;
	private String nazwa;
	private String jednostka;
	private double ilosc;
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getJednostka() {
		return jednostka;
	}

	public void setJednostka(String jednostka) {
		this.jednostka = jednostka;
	}

	public double getIlosc() {
		return ilosc;
	}

	public void setIlosc(double ilosc) {
		this.ilosc = ilosc;
	}

	public Produkt(String index, String nazwa, String jednostka, double ilosc) {
		super();
		this.index = index;
		this.nazwa = nazwa;
		this.jednostka = jednostka;
		this.ilosc = ilosc;
	}
	
	
}

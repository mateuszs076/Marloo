package Communication.Data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Produkt implements Serializable{
	int id;
	String index;
	String nazwa;
	String jednostka;
	double ilosc;
	
	
	public Produkt(int id, String index, String nazwa, String jednostka, double ilosc) {
		super();
		this.id = id;
		this.index = index;
		this.nazwa = nazwa;
		this.jednostka = jednostka;
		this.ilosc = ilosc;
	}

	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return id + " " + index + " " + nazwa + " =" + jednostka + " "
				+ ilosc;
	}
	
	
	
	
}

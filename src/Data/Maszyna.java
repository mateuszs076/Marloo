package Data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Maszyna implements Serializable{
String nazwa;
int czasprocesu;
Produkt produkowane;
String produkowane1;
public Maszyna(String nazwa, int czasprocesu, Produkt produkowane) {
	super();
	this.nazwa = nazwa;
	this.czasprocesu = czasprocesu;
	this.produkowane = produkowane;
}

public String getProdukowane1() {
	return produkowane1;
}

public void setProdukowane1(String produkowane1) {
	this.produkowane1 = produkowane1;
}

public Maszyna(String nazwa, int czasprocesu, String produkowane1) {
	super();
	this.nazwa = nazwa;
	this.czasprocesu = czasprocesu;
	this.produkowane1 = produkowane1;
}

public String getNazwa() {
	return nazwa;
}
public void setNazwa(String nazwa) {
	this.nazwa = nazwa;
}
public int getCzasprocesu() {
	return czasprocesu;
}
public void setCzasprocesu(int czasprocesu) {
	this.czasprocesu = czasprocesu;
}
public Produkt getProdukowane() {
	return produkowane;
}
public void setProdukowane(Produkt produkowane) {
	this.produkowane = produkowane;
}


}

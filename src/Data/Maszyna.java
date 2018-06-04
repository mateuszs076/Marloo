package Data;

public class Maszyna {
String nazwa;
int czasprocesu;
Produkt produkowane;
public Maszyna(String nazwa, int czasprocesu, Produkt produkowane) {
	super();
	this.nazwa = nazwa;
	this.czasprocesu = czasprocesu;
	this.produkowane = produkowane;
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

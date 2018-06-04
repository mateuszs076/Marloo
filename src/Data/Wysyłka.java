package Data;

import java.util.ArrayList;

public class Wysy³ka {
ArrayList<Produkt> produkty;
Odbiorca odbiorca;
public Wysy³ka(ArrayList<Produkt> produkty, Odbiorca odbiorca) {
	super();
	this.produkty = produkty;
	this.odbiorca = odbiorca;
}
public ArrayList<Produkt> getProdukty() {
	return produkty;
}
public void setProdukty(ArrayList<Produkt> produkty) {
	this.produkty = produkty;
}
public Odbiorca getOdbiorca() {
	return odbiorca;
}
public void setOdbiorca(Odbiorca odbiorca) {
	this.odbiorca = odbiorca;
}


}

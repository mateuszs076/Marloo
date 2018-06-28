package Communication.Data;

public class Zlecenie {
Maszyna maszyna;
Produkt produkt;
public Zlecenie(Maszyna maszyna, Produkt produkt) {
	super();
	this.maszyna = maszyna;
	this.produkt = produkt;
}
public Maszyna getMaszyna() {
	return maszyna;
}
public void setMaszyna(Maszyna maszyna) {
	this.maszyna = maszyna;
}
public Produkt getProdukt() {
	return produkt;
}
public void setProdukt(Produkt produkt) {
	this.produkt = produkt;
}


}

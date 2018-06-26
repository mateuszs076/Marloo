package Communication.Data;

import java.util.ArrayList;

public class Wysylka {
    private ArrayList<Produkt> produkty;
    private Odbiorca odbiorca;

    public Wysylka(ArrayList<Produkt> produkty, Odbiorca odbiorca) {
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

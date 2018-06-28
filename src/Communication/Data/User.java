package Communication.Data;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private int uprawnienia;
    private boolean isAddingUser;

    public User(int id, String login, String haslo, String imie, String nazwisko, int uprawnienia) {
        super();
        this.id = id;
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.uprawnienia = uprawnienia;
    }

    
    
   



	public User(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public User(String login, String haslo, String imie, String nazwisko, boolean isAddingUser) {
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.isAddingUser = isAddingUser;
    }
    
    public User(String login, String haslo, String imie, String nazwisko, boolean isAddingUser, int uprawnienia) {
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.isAddingUser = isAddingUser;
        this.uprawnienia = uprawnienia;
    }

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public void setAddingUser(boolean addingUser) {
        isAddingUser = addingUser;
    }

    public boolean isAddingUser() {
        return isAddingUser;
    }

    @Override
    public String toString() {
        return id +" "+ login + ": " + imie + " " + nazwisko + " uprawnienia=" + uprawnienia;
    }
}


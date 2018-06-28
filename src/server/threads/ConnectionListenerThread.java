package server.threads;

import static Communication.Communication.UNAUTHORIZED_LOGIN;

import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Communication.Communication;
import Communication.Data.Produkt;
import Communication.Data.User;
import server.connectors.ClientConnector;
import server.database.DatabasePostgree;
import server.database.DatabsaeMySQL;

public class ConnectionListenerThread implements Runnable {

    private ClientConnector cc;
    private ExecutorService executor;

    public ConnectionListenerThread(ClientConnector cc) {
        this.cc = cc;
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        Object obj;

        while (true) {
            try {
                obj = cc.receiveObject();
                if (obj != null) {
                    System.out.println("najs!");
                }
                if (obj instanceof User) {
                    if (!(((User) obj).isAddingUser())) {
                        System.out.println("got action1");
                        try {
                            User user = DatabsaeMySQL.getInstance().login(((User) obj).getLogin(), ((User) obj).getHaslo());
                            if (user != null) {
                                cc.sendObject((User) user);
                                System.out.println("tak!");
                            } else {
                                cc.sendObject(UNAUTHORIZED_LOGIN);
                                System.out.println("nie1");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
//                        cc.closeSocket();
                    } else {
                    	DatabsaeMySQL.getInstance().rmvUser(((User) obj).getId());
                        DatabsaeMySQL.getInstance().addUser(
                                ((User) obj).getImie(),
                                ((User) obj).getNazwisko(),
                                ((User) obj).getLogin(),
                                ((User) obj).getHaslo(),
                                ((User) obj).getUprawnienia());

                        System.out.println("Adding user server side");
                    }
                }
                if (obj instanceof Produkt) {
                    
                    
                    System.out.println("Dodaje do magazynu");
                   DatabasePostgree.getInstance().addProduct(((Produkt) obj));
                        
                }

                if (obj instanceof String) {
                    if (obj.equals("getprodukty")) {
                        System.out.println("pobieram produkty");
                        ArrayList<Produkt> produkty=DatabasePostgree.getInstance().readProdukty();
                            if (produkty != null) {
                                cc.sendObject((ArrayList<Produkt>) produkty);
                                System.out.println("tak!");
                            } else {
                                cc.sendObject(Communication.GET_PRODUKTY_FAILD);
                                System.out.println("nie1");
                            }
                    } 
                    if (obj.equals("getuserzy")) {
                        System.out.println("pobieram userow");
                        ArrayList<User> userzy=DatabsaeMySQL.getInstance().pobierzUserow();
                            if (userzy != null) {
                                cc.sendObject((ArrayList<User>) userzy);
                                System.out.println("tak!");
                            } else {
                                cc.sendObject(Communication.GET_PRODUKTY_FAILD);
                                System.out.println("nie1");
                            }
                    } 
                    if (((String) obj).substring(0, 1).equals("p")) {
                        System.out.println("wydaje na magazyn");
                        ArrayList<Produkt> produkty=DatabasePostgree.getInstance().readProdukty();
                        String tekst=(String) obj;
                        //int dlugoscdoubla=tekst.length()-2;
                        System.out.println(tekst);
                        double ilosc=Double.parseDouble(tekst.substring(2, tekst.length()));
                        System.out.println("Ilosc:"+ilosc);
                        for(int i=0; i<produkty.size(); i++)
                        {
                        	System.out.println("ID:"+((String) obj).substring(1, 2));
                        	if(Integer.parseInt(((String) obj).substring(1, 2))==(produkty.get(i).getId()))
                        	{
                        		produkty.get(i).setIlosc(produkty.get(i).getIlosc()-ilosc);
                        		System.out.println(produkty.get(i).getIlosc());
                        		DatabasePostgree.getInstance().uptadeProduct(produkty.get(i));
                        	}
                        }
                    } 
                    else {
                       
                        System.out.println("Wrong");
                    }
                }
               
            } catch (SocketException se) {
                cc.closeSocket();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}


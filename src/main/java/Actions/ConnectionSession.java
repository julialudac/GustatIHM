/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Commande;
import java.util.HashMap;

/**
 *
 * @author wth
 */
public class ConnectionSession {
    public final static ConnectionSession INSTANCE = new ConnectionSession();
    private HashMap<String, Client> clientsConnected = new HashMap<>();
    private HashMap<String, Long> commandesATraiter = new HashMap<>();
    private HashMap<Long, Commande> commandeEnCourant = new HashMap<>();
    
    public boolean connectUser(String sessionID, Client user) {
        if (clientsConnected.containsValue(user)) {
            return false;
        }
        else {
            clientsConnected.put(sessionID, user);
            return true;
        }
    } 
    
    public Client getUser(String sessionID) {
        if (clientsConnected.containsKey(sessionID)) {
            return clientsConnected.get(sessionID);
        }
        else {
            return null;
        }
    }
    
    public void creerCommande(long clientID, Commande c) {
        commandeEnCourant.put(clientID, c);
    }
    
    public void annulerCommande(long clientID) {
        commandeEnCourant.remove(clientID);
    }
    
    /**
     *
     * @param clientID
     * @return
     */
    public int getNombreDeCommandeATraiter () {
        return commandesATraiter.size();
    }
    
    public Commande getCommandeByClient (Long clientID) {
        return commandeEnCourant.get(clientID);
    }
    
    public void enregistrerCommande(String sessionID, long newCommandeID) {
        commandesATraiter.put(sessionID, newCommandeID);
    }
    
    public void supprimerCommande (String sessionID) {
        commandesATraiter.remove(sessionID);
    }
}

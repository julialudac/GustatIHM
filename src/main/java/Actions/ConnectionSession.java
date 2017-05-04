/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import java.util.HashMap;

/**
 *
 * @author wth
 */
public class ConnectionSession {
    public final static ConnectionSession INSTANCE = new ConnectionSession();
    private HashMap<String, Client> clients = new HashMap<>();
    
    public void connectClient(String email, Client c){
        clients.put(email, c);
    } 
}

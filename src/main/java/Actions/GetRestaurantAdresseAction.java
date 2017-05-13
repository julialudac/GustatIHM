/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Restaurant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wth
 */
public class GetRestaurantAdresseAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans action GetRestaurantAdresseAction");
        Client client = (Client) request.getSession().getAttribute("client");
        //ConnectionSession connectionSession = ConnectionSession.INSTANCE;    
        //Commande commande = connectionSession.getCommandeByClient(client.getId());
        Commande commande = (Commande) request.getSession().getAttribute("commande");
        Restaurant restaurant = commande.getRestaurant();
        
        JsonObject jsonProduit = new JsonObject();
        jsonProduit.addProperty("adresse", restaurant.getDenomination()+": "+restaurant.getAdresse());
        
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(GetRestaurantsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(jsonProduit);
        out.close();
    }    
}

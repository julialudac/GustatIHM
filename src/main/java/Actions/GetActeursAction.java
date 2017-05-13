/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.LivreurVelo;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julia
 */
public class GetActeursAction extends Action{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans action getActeurs");
        JsonObject jsonContainer = new JsonObject();
        
        // ---------------- récupération des restos---------------------
        JsonArray jsonListe = new JsonArray();
        List<Restaurant> listeResto = ServiceMetier.findAllRestaurants();
        for (Restaurant restaurant : listeResto) {
            JsonObject jsonRestaurant = new JsonObject();
            jsonRestaurant.addProperty("adresse",restaurant.getAdresse());
            jsonRestaurant.addProperty("denomination", restaurant.getDenomination());
            jsonRestaurant.addProperty("latitude", restaurant.getLatitude());
            jsonRestaurant.addProperty("longitude", restaurant.getLongitude());
            jsonListe.add(jsonRestaurant);
        }
        jsonContainer.add("restos", jsonListe);
        
        
        // ---------------- récupération des clients---------------------
        jsonListe = new JsonArray();
        List<Client> listeClient = ServiceMetier.findAllClients();
        for (Client client : listeClient) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("adresse",client.getAdresse());
            jsonClient.addProperty("nomC", client.getNom()+" "+client.getPrenom());
            jsonClient.addProperty("latitude", client.getLatitude());
            jsonClient.addProperty("longitude", client.getLongitude());
            jsonListe.add(jsonClient);
        }
        jsonContainer.add("clients", jsonListe);
        
        
        // ---------------- récupération des vélos---------------------
        jsonListe = new JsonArray();
        List<Livreur> listeVelos = ServiceMetier.findAllVelos();
        for (Livreur velo : listeVelos) {
            JsonObject jsonVelo = new JsonObject();
            jsonVelo.addProperty("adresse",velo.getAdresse());
            int indexTiret = velo.toString().indexOf(" -");
            int apresVeloIndex = velo.toString().indexOf("Vélo : ") + 7;
            String nomComplet = velo.toString().substring(apresVeloIndex,indexTiret);
            System.out.println("nom complet velo : "+nomComplet);
            jsonVelo.addProperty("adresse",velo.getAdresse());
            jsonVelo.addProperty("nomC", nomComplet);
            jsonVelo.addProperty("latitude", velo.getLatitude());
            jsonVelo.addProperty("longitude", velo.getLongitude());
            jsonListe.add(jsonVelo);
        }
        jsonContainer.add("velos", jsonListe);
        
        
        // ---------------- récupération des drones---------------------
        jsonListe = new JsonArray();
        List<Livreur> listeDrones = ServiceMetier.findAllDrones();
        for (Livreur drone : listeDrones) {
            JsonObject jsonDrone = new JsonObject();
            jsonDrone.addProperty("adresse",drone.getAdresse());
            jsonDrone.addProperty("latitude", drone.getLatitude());
            jsonDrone.addProperty("longitude", drone.getLongitude());
            jsonListe.add(jsonDrone);
        }
        jsonContainer.add("drones", jsonListe);
        

        
        // ---------------------envoi de la réponse Json------------------------
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(GetRestaurantsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(jsonContainer);
        out.close();
    }
}

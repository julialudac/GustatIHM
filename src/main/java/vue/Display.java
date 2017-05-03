/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Restaurant;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author twu2
 */
public class Display {
    
    public static void printListeRestaurants(PrintWriter out, List<Restaurant> restos){
        JsonArray jsonListe = new JsonArray();

        for(Restaurant r:restos){
            JsonObject jsonRestaurant = new JsonObject();
            
            jsonRestaurant.addProperty("id",r.getId());
            jsonRestaurant.addProperty("denomination",r.getDenomination());
            
           // jsonListe.add(jSonrestaurant);
            
        }
    }
    
}

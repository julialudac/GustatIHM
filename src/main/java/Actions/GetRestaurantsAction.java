/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
 * @author jludac
 */
public class GetRestaurantsAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans action getRestaurants");
        JsonArray jsonListe = new JsonArray();
        String recherche = request.getParameter("nomRestaurant");
        System.out.println("recherche: "+recherche);
        List<Restaurant> listeResto ;
        if (null==recherche){
            listeResto = ServiceMetier.findAllRestaurants();
        } else {
            listeResto = ServiceMetier.searchRestaurants(recherche);
        }
        for (Restaurant restaurant : listeResto) {
            //System.out.println(restaurant);
            JsonObject jsonRestaurant = new JsonObject();
            jsonRestaurant.addProperty("id",restaurant.getId());
            jsonRestaurant.addProperty("adresse",restaurant.getAdresse());
            jsonRestaurant.addProperty("denomination", restaurant.getDenomination());
            jsonRestaurant.addProperty("latitude", restaurant.getLatitude());
            jsonRestaurant.addProperty("longitude", restaurant.getLongitude());
            jsonRestaurant.addProperty("description", restaurant.getDescription());
            jsonListe.add(jsonRestaurant);
        }
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.add("restoos", jsonListe);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author jludac
 */
public class DetailRestaurantsAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        // récupérer l'instance concerné 
        System.out.println("Je suis dans action DetailRestaurantsAction");
        long id = parseLong((request.getParameter("idRestaurant")));
        //System.out.println(id);
        Restaurant restaurant = ServiceMetier.findAllRestaurants().get((int)id-1);
        //System.out.println(restaurant);
        // convertir en JSON
        JsonObject jsonRestaurant = new JsonObject();
        jsonRestaurant.addProperty("id", id);
        jsonRestaurant.addProperty("denomination", restaurant.getDenomination());
        jsonRestaurant.addProperty("adresse", restaurant.getAdresse());
        jsonRestaurant.addProperty("description", restaurant.getDescription());
        
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(GetRestaurantsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(jsonRestaurant);
        out.close();
    }
}

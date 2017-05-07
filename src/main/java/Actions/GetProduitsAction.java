/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Produit;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jludac
 */
public class GetProduitsAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans action getProduits");
        long idRestaurant = parseLong((request.getParameter("idRestaurant")));
        Restaurant restaurant = null;
        try {
            restaurant = ServiceMetier.findAllRestaurants().get((int)idRestaurant-1);
        } catch (Exception ex) {
            Logger.getLogger(GetProduitsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonArray jsonListe = new JsonArray();
        String recherche = request.getParameter("nomProduit");
        System.out.println("recherche: "+recherche);
        List<Produit> listeProduit = null ;
        if (recherche==null){
            try {
                listeProduit = ServiceMetier.searchProduits(" ",restaurant);
            } catch (Exception ex) {
                Logger.getLogger(GetProduitsAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            listeProduit = ServiceMetier.searchProduits(recherche,restaurant);
        }
        for (Produit produit : listeProduit) {
            //System.out.println(restaurant);
            JsonObject jsonProduit = new JsonObject();
            jsonProduit.addProperty("id",produit.getId());
            jsonProduit.addProperty("prix",produit.getPrix());
            jsonProduit.addProperty("denomination", produit.getDenomination());
            jsonListe.add(jsonProduit);
        }
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.add("produits", jsonListe);
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

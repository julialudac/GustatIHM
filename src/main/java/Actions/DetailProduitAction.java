/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

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
public class DetailProduitAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        // récupérer l'instance concerné 
        System.out.println("Je suis dans action DetailProduit");
        long idRestaurant = parseLong((request.getParameter("idRestaurant")));
        Restaurant restaurant = null;
        try {
            restaurant = ServiceMetier.findAllRestaurants().get((int)idRestaurant-1);
        } catch (Exception ex) {
            Logger.getLogger(GetProduitsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        long idProduit = parseLong((request.getParameter("idProduit")));
        System.out.println("idProduit: "+idProduit);
        List<Produit> produits = ServiceMetier.searchProduits(" ", restaurant);
        Produit produitFind = null;
        for (Produit produit:produits){
            if (produit.getId()==idProduit){
                produitFind = produit;
                break;
            }
        }
        //System.out.println(restaurant);
        // convertir en JSON
        JsonObject jsonProduit = new JsonObject();
        jsonProduit.addProperty("id", idProduit);
        jsonProduit.addProperty("denomination", produitFind.getDenomination());
        jsonProduit.addProperty("description", produitFind.getDescription());
        jsonProduit.addProperty("prix", produitFind.getPrix());
        
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

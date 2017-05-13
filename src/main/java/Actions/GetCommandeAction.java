/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.ProduitCommande;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wth
 */
public class GetCommandeAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        HttpSession session = request.getSession(true);
        Livreur livreur = (Livreur) session.getAttribute("livreur");
        Commande commande = livreur.getCmdeEnCours();

        List<ProduitCommande> produits = commande.getProduitCommande();
        
        JsonArray jsonListe = new JsonArray();
        for (ProduitCommande pc : produits) {
            //System.out.println(restaurant);
            JsonObject jsonProduitCommande = new JsonObject();
            jsonProduitCommande.addProperty("nom",pc.getProduit().getDenomination());
            jsonProduitCommande.addProperty("quantite",pc.getQte());
            jsonProduitCommande.addProperty("prix", pc.getProduit().getPrix()*pc.getQte());
            jsonListe.add(jsonProduitCommande);
        }
        JsonObject jsonContainer = new JsonObject();
        
        JsonObject jsonRestaurant = new JsonObject();
        jsonRestaurant.addProperty("adresseRestaurant", commande.getRestaurant().getAdresse());
                
        JsonObject jsonClient = new JsonObject();
        jsonClient.addProperty("adresseClient", commande.getClient().getAdresse());
        
        jsonContainer.add("commande", jsonListe);
        jsonContainer.add("restaurant", jsonRestaurant);
        jsonContainer.add("client", jsonClient);
        
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
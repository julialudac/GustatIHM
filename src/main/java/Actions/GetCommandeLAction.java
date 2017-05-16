/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.Produit;
import com.insa.gustatif.metier.modele.ProduitCommande;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
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
 * @author julia
 */
public class GetCommandeLAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("dans action GetCommandeLAction");
        
        // récupération des infos pour l'id du livreur donnée 
        long idLivreur = Long.parseLong(request.getParameter("idLivreur"));
        System.out.println("id lireur " + idLivreur);
        Livreur livreur = ServiceMetier.connexionLivreur(idLivreur);
        
        Commande commande = livreur.getCmdeEnCours();
        
        // Les adresses stockées dans un tableau (je comprends pas pq on ne peut stocker direct des objet non
        // tableau dans un container (qui conteint au moin sun autre tableau ???)
        JsonArray adressesJson = new JsonArray();
        
        // L'adresse client 
        JsonObject adrC = new JsonObject();
        Client client = commande.getClient();
        System.out.println("adresse vlien : "+client.getAdresse());
        adrC.addProperty("a", client.getAdresse());
        adrC.addProperty("adresse2", client.getAdresse());
        adressesJson.add(adrC);
        
        // L'adresse resto
        adrC = new JsonObject();
        Restaurant resto = commande.getRestaurant();
        adrC.addProperty("a", resto.getAdresse());
        adrC.addProperty("adresse2", client.getAdresse());
        adressesJson.add(adrC);
        
        
        // Les produits : chacun a un nom et une quantité
        JsonArray jsonListeP = new JsonArray();
        List<ProduitCommande> produits = commande.getProduitCommande();
        for (ProduitCommande pc : produits) {
            JsonObject jsonProduitCommande = new JsonObject();
            jsonProduitCommande.addProperty("nom",pc.getProduit().getDenomination());
            jsonProduitCommande.addProperty("quantite",pc.getQte());
            jsonListeP.add(jsonProduitCommande);
        }
            
        
        // Ajout des données dans un container
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.add("produits", jsonListeP);
        jsonContainer.add("adr", adressesJson);
        

        // Envoi de la réponse
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

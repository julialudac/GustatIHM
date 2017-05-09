/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Produit;
import com.insa.gustatif.metier.modele.ProduitCommande;
import com.insa.gustatif.metier.service.ServiceMetier;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wth
 */
public class ModifierCommandeAction extends Action{

    @Override
    @SuppressWarnings("empty-statement")
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans function ModifierCommandeAction");
        ConnectionSession connectionSession = ConnectionSession.INSTANCE;
        Client client = (Client) request.getSession().getAttribute("client");
        HttpSession session = request.getSession(true);
        String but = request.getParameter("but");
        if (but.equals("ajout")){
            System.out.println("Je suis dans cas ajout");
            //Commande commande = connectionSession.getCommandeByClient(client.getId());
            Commande commande = (Commande) session.getAttribute("commande");
            System.out.println("CommandeID: "+commande.getNumCommande());
            long idProduit = parseLong((request.getParameter("idProduit")));
            //System.out.println("idProduit: "+idProduit);
            List<Produit> produits = ServiceMetier.searchProduits(" ", commande.getRestaurant());
            Produit produitFind = null;
            for (Produit produit:produits){
                if (produit.getId()==idProduit){
                    produitFind = produit;
                    break;
                }
            }
            int qte = parseInt((request.getParameter("quantite")));
            ProduitCommande produitCommande = new ProduitCommande(produitFind,qte);
            ServiceMetier.addProduitCommande(commande, produitCommande);
            session.setAttribute("commande", commande);
            connectionSession.creerCommande(client.getId(), commande);
        } else {
            System.out.println("Je suis dans cas supprime");
            //Commande commande = connectionSession.getCommandeByClient(client.getId());
            Commande commande = (Commande) session.getAttribute("commande");
            long idProduit = parseLong((request.getParameter("idProduit")));
            //System.out.println("idProduit: "+idProduit);
            List<Produit> produits = ServiceMetier.searchProduits(" ", commande.getRestaurant());
            Produit produitFind = null;
            for (Produit produit:produits){
                if (produit.getId()==idProduit){
                    produitFind = produit;
                    break;
                }
            }
            int qte = parseInt((request.getParameter("quantite")));
            ProduitCommande produitCommande = new ProduitCommande(produitFind,qte);
            ServiceMetier.removeProduitCommande(commande, produitCommande);
            session.setAttribute("commande", commande);
            connectionSession.creerCommande(client.getId(), commande);
            System.out.println("CommandeID: "+commande.getNumCommande());
        }
        
    }
}

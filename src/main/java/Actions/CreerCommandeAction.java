/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import static java.lang.Long.parseLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wth
 */
public class CreerCommandeAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans function CreerCommande");
        ConnectionSession connectionSession = ConnectionSession.INSTANCE;
        HttpSession session = request.getSession(true);
        
        Client client = (Client) request.getSession().getAttribute("client");
        long idRestaurant = parseLong((request.getParameter("idRestaurant")));
        Restaurant restaurant = null;
        try {
            restaurant = ServiceMetier.findAllRestaurants().get((int)idRestaurant-1);
        } catch (Exception ex) {
            Logger.getLogger(GetProduitsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setRestaurant(restaurant);
        commande.setNumCommande((long)(connectionSession.getNombreDeCommandeATraiter()+1));
        session.setAttribute("commande", commande);
        connectionSession.creerCommande(client.getId(), commande);
    }
}

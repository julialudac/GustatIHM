/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Livreur;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author wth
 */
public class ValiderCommandeAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        // Recupere les datas de commande
        System.out.println("Je suis dans action ValiderCommandeAction");
        ConnectionSession connectionSession = ConnectionSession.INSTANCE;
        HttpSession session = request.getSession(true);
        Client client =  connectionSession.getUser(request.getSession().getId());
        //Commande commande = connectionSession.getCommandeByClient(client.getId());
        Commande commande = (Commande) session.getAttribute("commande");
        System.out.println(commande);
        
        //pour trouver si il y un livreur disponible
        List<Livreur> livreurs = ServiceMetier.findAllLivreurs();
        boolean livreurDisponi = false;
        for (Livreur livreur : livreurs){
            if (livreur.getCmdeEnCours()==null){
                livreurDisponi = true;
                break;
            }
        }
        System.out.println("livreurDisponi:" + livreurDisponi);
        
        reponse.setContentType("text/html;charset=UTF-8");
        JsonObject jsonMessage = new JsonObject();
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(GetRestaurantsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(livreurDisponi){
            ServiceMetier.traiterCommande(commande);
            jsonMessage.addProperty("info", "Votre commande est bien validé");
            out.println(jsonMessage);
            out.close();
        } else {
            System.out.println("Dans le cas pas de livreur disponible");
            ServiceMetier.traiterCommande(commande);
            jsonMessage.addProperty("info", "Aucun livreur n'est actuellement disponible pour traiter votre commande.Veuillez réessayer ultérieurement.");
            out.println(jsonMessage);
            out.close();
        }
        session.setAttribute("commande", commande);
        //connectionSession.enregistrerCommande(request.getSession().getId(),commande.getNumCommande());
    }
}

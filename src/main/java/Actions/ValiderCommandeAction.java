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
import java.io.IOException;
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
public class ValiderCommandeAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // Recupere les datas de commande
        System.out.println("Je suis dans action ValiderCommandeAction");
        ConnectionSession connectionSession = ConnectionSession.INSTANCE;
         HttpSession session = request.getSession(true);
        Client client =  connectionSession.getUser(request.getSession().getId());
        //Commande commande = connectionSession.getCommandeByClient(client.getId());
        Commande commande = (Commande) session.getAttribute("commande");
        System.out.println(commande);
        ServiceMetier.traiterCommande(commande);
        session.setAttribute("commande", commande);
        connectionSession.enregistrerCommande(request.getSession().getId(),commande.getNumCommande());
    }
}

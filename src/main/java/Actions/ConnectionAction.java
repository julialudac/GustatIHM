/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jludac
 */
public class ConnectionAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        //ConnectionSession connectionSession = ConnectionSession.INSTANCE;
        // récupération des param
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
       // Client cl = ServiceMetier.connexionClientEmail("asing8183@free.fr", 127);
        Client cl = ServiceMetier.findAllClients().get(0);
 
       
        System.out.println("Email :" +email +" et mdp :" + Long.parseLong(mdp) );
        
        // Gestion cas
            // cas données manquantes
        if (cl!=null){
            //TODO:enregistrer le client
            System.out.println(cl);
            try {
                reponse.sendRedirect("choixRestaurant.html");
            } catch (IOException ex) {
                Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                System.out.println("Client pas trouvé");
                reponse.sendRedirect("accueilClient.html");
            } catch (IOException ex) {
                Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}

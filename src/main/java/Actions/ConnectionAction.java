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
import javax.servlet.http.HttpSession;

/**
 *
 * @author jludac
 */
public class ConnectionAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        
        ConnectionSession connectionSession = ConnectionSession.INSTANCE;
        // récupération des param
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        // attention lors de la conversion du mdp
        long mdpl = -123; // cette ID n'existe pas
        try{
            mdpl = Long.parseLong(mdp);
        }catch(NumberFormatException e){
            System.out.println("Le mdp entré n'est pas un nb");
        }
        Client cl = null;
        try {
            cl = ServiceMetier.connexionClientEmail(email, mdpl);
            // A ne pas oublier, sinon le client sera quand même pris même si mauvais mdp
            if(cl.getId()!=mdpl){
                cl=null;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if (cl!=null&&connectionSession.connectUser(request.getSession().getId(), cl)){
            //TODO:enregistrer le client
            HttpSession session = request.getSession(true);
            session.setAttribute("user", email);
            session.setAttribute("client", cl);
            System.out.println("Client trouvé");
            System.out.println(cl);
            try {
                reponse.setContentType("text/html;charset=UTF-8");
                System.out.println("prénom :"+cl.getPrenom());
                // bizarre : ne supporte pas les caractères spéciaux, malgré UTF8
                reponse.sendRedirect("creationCommande.html?connection="+cl.getPrenom());
            } catch (IOException ex) {
                Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                System.out.println("Client pas trouvé");
                reponse.sendRedirect("accueilClient.html?info=1");
            } catch (IOException ex) {
                Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
    }
    
}

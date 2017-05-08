/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author julia
 */
public class ConnectionNonClientAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suisConnectionNonClient");
        
        String mdp = request.getParameter("mdp");
        // attention lors de la conversion du mdp
        long mdpl = -123; // cette ID n'existe pas
        try{
            mdpl = Long.parseLong(mdp);
        }catch(NumberFormatException e){
            System.out.println("Le mdp entré n'est pas un nb");
        }
        
        // Si c'est l'admin qui se connecte
        if(mdpl==0){
            try {
                reponse.sendRedirect("itfAdmin.html");
            } catch (IOException ex) {
                Logger.getLogger(ConnectionNonClientAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Sinon c'est que c'est un livreur qui se connecte
        else{
            Livreur l = null;
            try {
                l = ServiceMetier.connexionLivreur(mdpl);
                if(l.getTypeId().equals("drone")){
                    l=null;
                }
            } catch (Exception ex) {
                Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            // si livreur trouvé, direction itf livreur
            if (l!=null){
                //TODO:enregistrer le livreur
                System.out.println("Livreur trouvé");
                System.out.println(l);
                try {
                    reponse.setContentType("text/html;charset=UTF-8");
                    // bizarre : ne supporte pas les caractères spéciaux, malgré UTF8
                    reponse.sendRedirect("itfLivreur.html");
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            // sinon redirection connexion
            } else {
                try {
                    System.out.println("Livreur pas trouvé");
                    reponse.sendRedirect("accueilNonClient.html?info=1");
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
        }
        
    }
    
}

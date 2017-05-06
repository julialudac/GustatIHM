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
 * @author wth
 */
public class InscriptionAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // Recupere les identifiants de l'utilisateur a inscrire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mail = request.getParameter("adrMail");
        String adresse = request.getParameter("adrLivraison");
        //String codePostal = request.getParameter("cp");
        //String ville = request.getParameter("ville");
        
        Client c = new Client(nom,prenom,mail,adresse);
        if(ServiceMetier.creerClient(c)) {
            try {
                response.sendRedirect("accueilClient.html?info=2");
            } catch (IOException ex) {
                Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                response.sendRedirect("accueilClient.html?info=3");
            } catch (IOException ex) {
                Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

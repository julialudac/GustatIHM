/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author julia
 */
public class ConnectionNonClientAction extends Action{
    // renvoie le vélo ou pas et garde la session ou pas. On y arrive à là si bien sur le mdp est différent de 0
    // => pas l'admin

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        
        // Session !!!
        HttpSession session = request.getSession(true);
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConnectionNonClientAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Je suisConnectionNonClient");
        
        String mdp = request.getParameter("mdp");
        System.out.println("Mor de passe :"+mdp);
        // attention lors de la conversion du mdp
        long mdpl = -123; // cette ID n'existe pas
        try{
            mdpl = Long.parseLong(mdp);
        }catch(NumberFormatException e){
            System.out.println("Le mdp entré n'est pas un nb");
        }
        
        JsonObject jsonVelo = new JsonObject();
        Livreur l = null;
        try {
            l = ServiceMetier.connexionLivreur(mdpl);
            if(l.getTypeId().equals("drone")){
                l=null;
            }
        } catch (Exception ex) {
            Logger.getLogger(ConnectionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        // si livreur trouvé, enregistrement dans la session et l'objet Json est dit plein
        if (l!=null){
            // enregistrer le livreur pour la session
            session.setAttribute("livreur",l);
            System.out.println("Livreur trouvé");
            jsonVelo.addProperty("plein",1);
            int indexTiret = l.toString().indexOf(" -");
            int apresVeloIndex = l.toString().indexOf("Vélo : ") + 7;
            String nomComplet = l.toString().substring(apresVeloIndex,indexTiret);
            jsonVelo.addProperty("nomC",nomComplet);
            jsonVelo.addProperty("plein",1);
        }
        else{
            System.out.println("Livreur pas trouvé");
            jsonVelo.addProperty("plein",0);
        }
        
        // envoi de l'objet Json
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(GetRestaurantsAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(jsonVelo);
        out.close();
        
    }
    
}

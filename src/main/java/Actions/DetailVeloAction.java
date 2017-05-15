/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.LivreurDrone;
import com.insa.gustatif.metier.modele.LivreurVelo;
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
public class DetailVeloAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        // récupérer l'instance concerné 
        System.out.println("Je suis dans action DetailVeloAction");
        HttpSession session = request.getSession(true);
        long id = parseLong(request.getParameter("idVelo"));
        System.out.println(id);
        List<Livreur> listLivreur = ServiceMetier.findAllLivreurs();
        LivreurVelo velo = null;
        for(Livreur l:listLivreur){
            if (l.getIdLivreur()==id){
                velo =  (LivreurVelo) l;
                break;
            }
        }
        session.setAttribute("livreur", velo);
        String str = "";
        if (velo.getCmdeEnCours()==null){
            str = "OUI";
        } else {
            str = "NON";
        }
        JsonObject jsonVelo = new JsonObject();
        jsonVelo.addProperty("id", id);
        jsonVelo.addProperty("nom", velo.getNom()+" "+velo.getPrenom());
        jsonVelo.addProperty("disponibilite", str);
        jsonVelo.addProperty("adresse", velo.getAdresse());
        jsonVelo.addProperty("email", velo.getMail());
        jsonVelo.addProperty("poidsMax", velo.getChargeMaxi());
        
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(DetailDroneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(jsonVelo);
        out.close();
    }
    
}

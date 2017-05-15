/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.LivreurDrone;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Long.parseLong;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wth
 */
public class DetailDroneAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        // récupérer l'instance concerné 
        System.out.println("Je suis dans action DetailDrone");
        String idStr = request.getParameter("idDrone");
        System.out.println(idStr);
        long id = parseLong(idStr.substring(1, idStr.length()));
        System.out.println(id);
        List<Livreur> listDrone = ServiceMetier.findAllDrones();
        LivreurDrone drone = null;
        for(Livreur l:listDrone){
            if (l.getIdLivreur()==id){
                drone = (LivreurDrone) l;
                break;
            }
        }
        String str = "";
        if (drone.getCmdeEnCours()==null){
            str = "OUI";
        } else {
            str = "NON";
        }
        JsonObject jsonDrone = new JsonObject();
        jsonDrone.addProperty("id", id);
        jsonDrone.addProperty("disponibilite", str);
        jsonDrone.addProperty("adresse", drone.getAdresse());
        jsonDrone.addProperty("vitesse", drone.getVitesseMoyenne());
        jsonDrone.addProperty("poidsMax", drone.getChargeMaxi());
        
        reponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;    
        try {
            out = reponse.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(DetailDroneAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(jsonDrone);
        out.close();
    }
    
}

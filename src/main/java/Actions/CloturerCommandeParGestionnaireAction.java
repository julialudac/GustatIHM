/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.LivreurDrone;
import com.insa.gustatif.metier.service.ServiceMetier;
import static java.lang.Long.parseLong;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wth
 */
public class CloturerCommandeParGestionnaireAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // Recupere les datas de commande
        System.out.println("Je suis dans action CloturerCommandeAction");
        String idStr = request.getParameter("idDrone");
        System.out.println(idStr);
        long id = parseLong(idStr.substring(1));
        System.out.println(id);
        List<Livreur> listDrone = ServiceMetier.findAllDrones();
        LivreurDrone drone = null;
        for(Livreur l:listDrone){
            if (l.getIdLivreur()==id){
                drone = (LivreurDrone) l;
                break;
            }
        }
        ServiceMetier.cloturerCommandeLivreur(drone);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.service.ServiceMetier;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wth
 */
public class CloturerCommandeAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // Recupere les datas de commande
        System.out.println("Je suis dans action CloturerCommandeAction");
        HttpSession session = request.getSession(true);
        Livreur livreur = (Livreur) session.getAttribute("livreur");
        ServiceMetier.cloturerCommandeLivreur(livreur);
    }
}

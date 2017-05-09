/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.insa.gustatif.metier.modele.Client;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wth
 */
public class AnnulerCommandeAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        System.out.println("Je suis dans function AnnulerCommandeAction");
        ConnectionSession connectionSession = ConnectionSession.INSTANCE;    
        Client client = (Client) request.getSession().getAttribute("client");
        connectionSession.annulerCommande(client.getId());
    }
}

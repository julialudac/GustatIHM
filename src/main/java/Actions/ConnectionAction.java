/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jludac
 */
public class ConnectionAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse reponse) {
        
        // récupération des param
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        
        //System.out.println("Email :" +email +" et mdp :" + mdp );
        
        // Gestion cas
            // cas données manquantes
            
        
    }
    
}

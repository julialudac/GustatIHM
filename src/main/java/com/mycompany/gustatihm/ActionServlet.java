/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gustatihm;

import Actions.*;
import com.insa.gustatif.dao.JpaUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author twu2
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    public static Set<String> currentUserList = new HashSet<>();
    final static int CHARGE_MAX_LIMIT = 8000;
    @Override
    public void init() throws ServletException {
        System.out.println("init : fabrique jpaUtil");
        JpaUtil.init();
    }
    
    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException {
        System.out.println("je suis dans service");
        String todo = request.getParameter("action");
        System.out.println("Todo = "+todo);
        Action action;
        //HttpSession session = request.getSession(false);
        switch(todo){
            case "getRestaurants":
                action = new GetRestaurantsAction();
                action.execute(request,reponse);
                break;
            case "detailRestaurant":
                action = new DetailRestaurantsAction();
                action.execute(request,reponse);
                break;
            case "Se connecter":
                action = new ConnectionAction();
                action.execute(request,reponse);
                break;
            case "s'enregistrer":
                action = new InscriptionAction();
                action.execute(request,reponse);
                break;   
            case "getProduits":
                action = new GetProduitsAction();
                action.execute(request,reponse);
                break; 
            case "detailProduits":
                action = new DetailProduitAction();
                action.execute(request,reponse);
                break;     
            case "creerCommande":
                System.out.println("dans case creerCommande");
                action = new CreerCommandeAction();
                action.execute(request,reponse);
                break; 
            case "annulerCommande":
                System.out.println("dans case annulerCommande");
                action = new AnnulerCommandeAction();
                action.execute(request,reponse);
                break; 
            case "modifierCommande":
                System.out.println("dans case modifierCommande");
                action = new ModifierCommandeAction();
                action.execute(request,reponse);
                break; 
            case "validerCommande":
                System.out.println("dans case validerCommande");
                action = new ValiderCommandeAction();
                action.execute(request,reponse);
                break;
            case "Connexion" :
                action = new ConnectionNonClientAction();
                action.execute(request,reponse);
                break;
            case "getClientAdresse":
                action = new GetClientAdresseAction();
                action.execute(request, reponse);
                break;
            case "getRestaurantAdresse":
                action = new GetRestaurantAdresseAction();
                action.execute(request, reponse);
                break;
            case "getCommande":
                action = new GetCommandeAction();
                action.execute(request, reponse);
                break;
            case "cloturerCommande":
                action = new CloturerCommandeAction();
                action.execute(request, reponse);
                break;
            case "getClientsRestoLivreurs" :
                action = new GetActeursAction();
                action.execute(request, reponse);
                break;
            case "detailDrone":
                action = new DetailDroneAction();
                action.execute(request, reponse);
                break;
        }
    }

    //HttpSession session = request.getSession(true);
    //request.setCharacterEncoding("UTF-8");
}

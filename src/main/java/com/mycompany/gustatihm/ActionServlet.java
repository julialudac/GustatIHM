/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gustatihm;

import Actions.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.insa.gustatif.dao.JpaUtil;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
           case "Connexion":
               action = new ConnectionNonClientAction();
               action.execute(request,reponse);
               break;     
           case "getLivreurData":
               
               break;
       }
       
       
    }

    //HttpSession session = request.getSession(true);
    //request.setCharacterEncoding("UTF-8");
}

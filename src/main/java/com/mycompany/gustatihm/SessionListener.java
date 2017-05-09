package com.mycompany.gustatihm;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener
public class SessionListener implements HttpSessionAttributeListener,HttpSessionListener {

    // Public constructor is required by servlet spec
    public SessionListener() {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        String user = (String) se.getSession().getAttribute("user");
        ActionServlet.currentUserList.add(user);
      /* Session is being created. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String user = (String) httpSessionEvent.getSession().getAttribute("user");
        ActionServlet.currentUserList.remove(user);
    }
}

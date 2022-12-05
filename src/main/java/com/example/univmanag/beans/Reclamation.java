package com.example.univmanag.beans;

import com.example.univmanag.dao.LoginDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.logging.Logger;
import com.example.univmanag.jms.*;

@Named
@SessionScoped
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 109480188386363L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //validate login
    public String getCurrentUsername() {
            HttpSession session = SessionUtils.getSession();
            return (String) session.getAttribute("username");
    }


    //logout event, invalidate session
    public void sendMessage() throws Exception {
//        Logger logger = Logger.getLogger(getClass().getName());
//       logger.warning("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH: "+message);

        //Listener.listen();
        Publisher.publish(message);
    }

}
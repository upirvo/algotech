package pl.upir.algotechtest.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Locale;

/**
 * Created by Upir on 2017-09-17.
 */
@Controller
public class MainController implements ErrorController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private static final String PATH = "/error";

   /* @Autowired
    @Qualifier("myresolver")
    ViewResolver resolver;*/

   /* @RequestMapping(value = "/login")
    public String login() {
        return "/view/login.xhtml";
    }*/

    @RequestMapping(value = "/")
    public String redirect() throws Exception {
        return "/view/layout/dashboard.xhtml?faces-redirect=true";
    }


    /*@RequestMapping(value = "/logout1")
    public String logout(Principal principal) {
        return "/logout";
    }*/


    @RequestMapping(value = "/error")
    public String get404(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return "view/error/"+httpServletResponse.getStatus()+".xhtml";
    }


    @Override
    public String getErrorPath() {
        return PATH;
    }

}
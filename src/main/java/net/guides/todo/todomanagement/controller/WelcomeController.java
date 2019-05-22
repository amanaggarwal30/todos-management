package net.guides.todo.todomanagement.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showWelcomePage(ModelMap map){
        map.put("name", getLoggedInUser(map));
        return "Welcome";
    }

    public String getLoggedInUser(ModelMap map){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = "";
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        }
        else{
            userName = principal.toString();
        }
        return userName;
    }
}

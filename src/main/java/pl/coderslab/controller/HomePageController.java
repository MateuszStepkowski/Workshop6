package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {

    @GetMapping({"/", "/home"})
    public String homePage(HttpSession session) {

        if (session.getAttribute("thisUser") == null) return "redirect:/signUp";
        return "homePage";
    }

}

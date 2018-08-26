package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.UserService;
import pl.coderslab.validationGroups.UserLoggingValidationGroup;

import javax.servlet.http.HttpSession;

@Controller
public class LoggingController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/signIn")
    public String signInForm(Model model) {

        model.addAttribute("user", new User());

        return "forms/loggingForm";
    }

    @PostMapping("/signIn")
    public String signInVerify(@Validated(UserLoggingValidationGroup.class) User user, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors())  return "forms/loggingForm";


        User userFromDb = userRepository.findFirstByEmail(user.getEmail());

        if (userFromDb != null){

            if (UserService.checkPassword(user.getPassword(), userFromDb.getPassword())){

                session.setAttribute("thisUser", userFromDb);
                return "redirect:/home";
            }

        }
        model.addAttribute("info", "invalid email or password, please try again");

        return "forms/loggingForm";

    }
}

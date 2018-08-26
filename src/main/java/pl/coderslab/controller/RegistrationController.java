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
import pl.coderslab.validationGroups.UserRegistrationValidationGroup;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/signUp")
    public String signUpForm(Model model) {

        model.addAttribute("user", new User());

        return "forms/registrationForm";
    }

    @PostMapping("/signUp")
    public String signUpVerify(@Validated(UserRegistrationValidationGroup.class) User user, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) return "forms/registrationForm";

        if (userRepository.findFirstByEmail(user.getEmail()) == null) {
            user.setPassword(UserService.hashPassword(user.getPassword()));
            userRepository.save(user);
            session.setAttribute("thisUser", user);
            return "redirect:/home";
        }

        model.addAttribute("info", "entered email already exist, please try again");
        return "forms/registrationForm";

    }
}

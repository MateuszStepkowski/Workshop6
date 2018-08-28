package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dto.UserDTO;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/{id}/allTweets")
    public String showUserTweets(Model model, @PathVariable int id, @SessionAttribute("thisUser") User thisUser) {

        if (id != thisUser.getId()) {
            User user = userRepository.findOne(id);
            List<Tweet> userTweets = tweetRepository.findAllByUserOrderByCreatedDesc(user);
            model.addAttribute("user", user);
            model.addAttribute("userTweets", userTweets);
            model.addAttribute("message", new Message());

            return "userTweets";
        }
        User user = thisUser;
        List<Tweet> userTweets = tweetRepository.findAllByUserOrderByCreatedDesc(user);
        model.addAttribute("user", user);
        model.addAttribute("userTweets", userTweets);

        return "userTweets";
    }


    @GetMapping("/messages")
    public String showUserMessages(HttpSession session, Model model) {
        User user = (User) session.getAttribute("thisUser");
        List<Message> userMessages = messageRepository.findAllByReceiverOrSender(user);
        for (Message message : userMessages) {
            if (message.getText().length() > 30) {
                message.setText(message.getText().substring(0, 30) + "...");
            }
        }
        model.addAttribute("userMessages", userMessages);
        return "userMessages";
    }

    @GetMapping("/editUser")
    public String editUser(Model model, @SessionAttribute("thisUser") User thisUser) {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(thisUser.getEmail());
        model.addAttribute("userDTO", userDTO);

        return "forms/userEditForm";
    }


    @PostMapping("/editUser")
    public String editUserVerify(@Validated UserDTO userDTO, BindingResult bindingResult, @SessionAttribute("thisUser") User thisUser, Model model, HttpSession session) {

        String emailInfo=null;
        String newPasswordInfo=null;
        String currentPasswordInfo=null;

        if ((!thisUser.getEmail().equals(userDTO.getEmail())) && (userRepository.findFirstByEmail(userDTO.getEmail()) != null)) {

            emailInfo = "email already engaged, please try another";
            model.addAttribute("emailInfo", emailInfo);
        }

        if (!userDTO.getNewPassword().equals(userDTO.getNewPasswordConfirm())) {

            newPasswordInfo = "new Password does not match new Password Confirm, try again";
            model.addAttribute("newPasswordInfo", newPasswordInfo);

        }

        if ( !UserService.checkPassword(  userDTO.getCurrentPassword() , thisUser.getPassword() ) ){

            currentPasswordInfo="incorrect password";
            model.addAttribute("currentPasswordInfo", currentPasswordInfo);

        }

        if (bindingResult.hasErrors() || emailInfo != null || newPasswordInfo != null || currentPasswordInfo != null) {
            return "forms/userEditForm";
        }

        if ( !thisUser.getEmail().equals(userDTO.getEmail() ) ){

            thisUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getNewPassword().length() > 0){

            thisUser.setPassword ( UserService.hashPassword( userDTO.getNewPassword() ) );
        }

        session.setAttribute("thisUser", userRepository.saveAndFlush(thisUser) );
        return "redirect:/home";
    }
}

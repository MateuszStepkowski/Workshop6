package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/messageDetails/{id}")
    public String showMessageDetails(@PathVariable int id, HttpSession session, Model model) {

        Message message = messageRepository.findOne(id);
        if (message.getSender().equals((User) session.getAttribute("thisUser"))) {

            model.addAttribute("message", message);
            return "messageDetails";

        } else if (message.getReceiver().equals((User) session.getAttribute("thisUser"))) {
            message.setDisplayed(true);
            messageRepository.save(message);
            model.addAttribute("message", message);
            return "messageDetails";
        }

        return "inaccessibleUrl";
    }

    @PostMapping("/user/{id}/allTweets")
    public String sendMessage(@Validated Message message, BindingResult bindingResult, @PathVariable int id, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            User user = userRepository.findOne(id);
            List<Tweet> userTweets = tweetRepository.findAllByUserOrderByCreatedDesc(user);
            model.addAttribute("user", user);
            model.addAttribute("userTweets", userTweets);
            return "userTweets";
        }
        Message newMessage = new Message();
        newMessage.setText(message.getText());
        newMessage.setReceiver(userRepository.findOne(id));
        newMessage.setSender((User) session.getAttribute("thisUser"));
        newMessage.setSent(new Timestamp(System.currentTimeMillis()));
        messageRepository.save(newMessage);
        return "redirect:/user/"+id+"/allTweets";
    }
}

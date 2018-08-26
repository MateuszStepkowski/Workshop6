package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}/allTweets")
    public String showUserTweets(Model model, @PathVariable int id){

        User user = userRepository.findOne(id);
        List<Tweet> userTweets = tweetRepository.findAllByUserOrderByCreatedDesc(user);
        model.addAttribute("user", user);
        model.addAttribute("userTweets", userTweets);

        return "userTweets";
    }
}

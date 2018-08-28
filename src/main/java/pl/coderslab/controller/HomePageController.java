package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    TweetRepository tweetRepository;

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {

        List<Tweet> allTweets = tweetRepository.findAllOrderByCreatedDesc();
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("allTweets", allTweets);
        return "homePage";
    }


}

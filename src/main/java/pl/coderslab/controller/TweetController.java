package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Controller
public class TweetController {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping({"/", "/home"})
    public String addNewTweet(@Validated Tweet newTweet, BindingResult bindingResult, HttpSession session){

        if (bindingResult.hasErrors()){
            return "homePage";
        }

        Tweet createdTweet = new Tweet();
        createdTweet.setText( newTweet.getText() );
        createdTweet.setCreated( new Timestamp( System.currentTimeMillis() ) );
        createdTweet.setUser( (User)session.getAttribute("thisUser") );
        tweetRepository.save(createdTweet);
        return "redirect:/home";
    }

    @GetMapping("/tweet/{id}")
    public String tweetDetails(@PathVariable int id, Model model){

        model.addAttribute("tweet", tweetRepository.findOne(id));
        model.addAttribute("tweetComments", commentRepository.findAllByTweetIdOrderByCreatedDesc(id));

        return "tweetDetails";
    }


}

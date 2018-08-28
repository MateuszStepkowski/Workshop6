package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.groups.Default;
import java.sql.Timestamp;

@Controller
public class CommentController {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/tweet/{id}")
    private String addComment(@Validated Comment newComment, BindingResult bindingResult,
                              @PathVariable int id, HttpSession session, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("tweet", tweetRepository.findOne(id));
            model.addAttribute("tweetComments", commentRepository.findAllByTweetIdOrderByCreatedDesc(id));
            return "tweetDetails";
        }
        Comment comment = new Comment();
        comment.setText(newComment.getText());
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
        comment.setUser( (User) session.getAttribute("thisUser") );
        comment.setTweet( tweetRepository.findOne(id) );
        commentRepository.save(comment);

        return "redirect:/tweet/"+id;


    }


}

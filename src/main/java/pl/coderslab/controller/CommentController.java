package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class CommentController {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/tweet/{id}")
    private String addComment(@Validated Comment newComment, BindingResult bindingResult,
                              @PathVariable int id, HttpSession session){

        if (bindingResult.hasErrors()) return "tweetDetails";
        Comment comment = new Comment();
        comment.setText(newComment.getText());
        comment.setCreated(new Timestamp(System.currentTimeMillis()));
        comment.setUser( (User) session.getAttribute("thisUser") );
        comment.setTweet( tweetRepository.findOne(id) );
        commentRepository.save(comment);

        return "redirect:/tweet/"+id;


    }


}

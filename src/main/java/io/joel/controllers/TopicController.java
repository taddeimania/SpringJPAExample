package io.joel.controllers;

import io.joel.interfaces.TopicRepository;
import io.joel.interfaces.VoteRepository;
import io.joel.models.Topic;
import io.joel.models.User;
import io.joel.models.Vote;
import io.joel.models.VoteStyle;
import io.joel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class TopicController {

    @Autowired
    TopicRepository topicRepo;

    @Autowired
    VoteRepository voteRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        User user = userRepo.findByUsername(principal.getName());

        List<Topic> topics = topicRepo.findAllByUser(user);
        model.addAttribute("topics", topics);
        model.addAttribute("principal", principal);
        return "index";
    }

    @RequestMapping(value = "/createTopic", method = RequestMethod.POST)
    public String createTopic(@RequestParam("title") String title,
                              @RequestParam("description") String desc,
                              Principal principal) {

        User user = userRepo.findByUsername(principal.getName());
        topicRepo.save(new Topic(title, desc, user));
        return "redirect:/";
    }

    @RequestMapping(value = "/upVote", method = RequestMethod.POST)
    public String createUpVote(@RequestParam("id") long id) {
        Topic topic = topicRepo.findOne(id);
        voteRepo.save(new Vote(VoteStyle.UP, topic));
        return "redirect:/";
    }

    @RequestMapping(value = "/downVote", method = RequestMethod.POST)
    public String createDownVote(@RequestParam("id") long id) {
        Topic topic = topicRepo.findOne(id);
        voteRepo.save(new Vote(VoteStyle.DOWN, topic));
        return "redirect:/";
    }

    @RequestMapping("/{topicId}")
    public String topicDetail(@PathVariable("topicId") long id, Model model) {
        model.addAttribute("topic", topicRepo.findOne(id));
        return "topicDetail";
    }

    @RequestMapping(value = "/{topicId}/comment", method = RequestMethod.POST)
    public String createComment() {
        return "redirect:/";
    }
}

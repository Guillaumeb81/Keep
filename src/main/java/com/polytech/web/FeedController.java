package com.polytech.web;

import com.polytech.services.FeedService;
import com.polytech.services.PublicationService;
import com.polytech.services.Story;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class FeedController {
    private FeedService feedService;
    private PublicationService publicationService;
    private String content;

    public FeedController(FeedService feedService, PublicationService publicationService){
        this.feedService = feedService;
        this.publicationService = publicationService;
    }

    public void post(Story story) {
        publicationService.share(story);
    }

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public String post(String content, Principal principal) {
        publicationService.share(new Story(content, principal.getName()));
        return "redirect:/feed";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feed(Model model,Principal principal) {
        List<Story> stories = feedService.fetchAllFeeds(principal);
        model.addAttribute("stories",stories);
        return "feed";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(String content, Principal principal) {
        publicationService.delete(content, principal);
        return "redirect:/feed";
    }


    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(String newContent, Principal principal) {
        publicationService.edit(newContent,this.content,principal);
        return "redirect:/feed";
    }
    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public String modifyPage(){
        return "modify";
    }

    @RequestMapping(value = "/setContent", method = RequestMethod.POST)
    public String setContent(String content){
        this.content=content;
        return "redirect:/modify";
    }


}

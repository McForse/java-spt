package ru.mirea.pr15.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr15.payload.requests.PostOfficeRequest;
import ru.mirea.pr15.services.PostOfficeService;

import javax.validation.Valid;

@Controller
@RequestMapping("post")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    public PostOfficeController(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @PostMapping("add")
    public RedirectView add(@Valid @ModelAttribute("postOfficeRequest") PostOfficeRequest postOfficeRequest, BindingResult result) {
        if (!result.hasErrors()) {
            postOfficeService.save(postOfficeRequest);
        }

        return new RedirectView("/home");
    }

    @PostMapping("{id}/delete")
    public RedirectView delete(@PathVariable long id) {
        postOfficeService.delete(id);
        return new RedirectView("/home");
    }
}

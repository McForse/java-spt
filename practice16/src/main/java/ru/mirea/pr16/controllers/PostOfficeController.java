package ru.mirea.pr16.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr16.dto.PostOfficeRequest;
import ru.mirea.pr16.services.PostOfficeService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

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

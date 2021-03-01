package ru.mirea.pr16.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr16.dto.DepartureRequest;
import ru.mirea.pr16.services.DepartureService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("departure")
public class DepartureController {
    private final DepartureService departureService;

    @PostMapping("add")
    public RedirectView add(@Valid @ModelAttribute("departureRequest") DepartureRequest departureRequest, BindingResult result) {
        if (!result.hasErrors()) {
            departureService.save(departureRequest);
        }

        return new RedirectView("/home");
    }

    @PostMapping("{id}/delete")
    public RedirectView delete(@PathVariable long id) {
        departureService.delete(id);
        return new RedirectView("/home");
    }
}

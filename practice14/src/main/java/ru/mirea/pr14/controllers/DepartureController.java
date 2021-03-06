package ru.mirea.pr14.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr14.models.Departure;
import ru.mirea.pr14.services.DepartureService;

@Controller
@RequiredArgsConstructor
@RequestMapping("departure")
public class DepartureController {
    private final DepartureService departureService;

    @PostMapping("add")
    public RedirectView add(@RequestParam long postId, @RequestParam String type, @RequestParam String date) {
        Departure departure = Departure.builder()
                .type(type)
                .date(date)
                .build();

        departureService.save(postId, departure);
        return new RedirectView("/home");
    }

    @PostMapping("remove")
    public RedirectView remove(@RequestParam long id) {
        departureService.remove(id);
        return new RedirectView("/home");
    }
}

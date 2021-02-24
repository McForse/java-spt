package ru.mirea.pr14.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr14.models.PostOffice;
import ru.mirea.pr14.services.DepartureService;
import ru.mirea.pr14.services.PostOfficeService;

@Controller
@RequestMapping("post")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;
    private final DepartureService departureService;

    public PostOfficeController(PostOfficeService postOfficeService, DepartureService departureService) {
        this.postOfficeService = postOfficeService;
        this.departureService = departureService;
    }

    @PostMapping("add")
    public RedirectView add(@RequestParam String name, @RequestParam String cityName) {
        PostOffice office = PostOffice.builder()
                .name(name)
                .cityName(cityName)
                .build();

        postOfficeService.save(office);
        return new RedirectView("/home");
    }

    @PostMapping("remove")
    public RedirectView remove(@RequestParam long id) {
        if (departureService.getCountByPostId(id) == 0) {
            postOfficeService.remove(id);
        }
        return new RedirectView("/home");
    }
}

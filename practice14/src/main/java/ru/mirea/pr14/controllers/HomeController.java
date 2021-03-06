package ru.mirea.pr14.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.pr14.services.DepartureService;
import ru.mirea.pr14.services.PostOfficeService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final PostOfficeService postOfficeService;
    private final DepartureService departureService;

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("posts", postOfficeService.getAll());
        model.put("departures", departureService.getAll());
        return "home";
    }
}

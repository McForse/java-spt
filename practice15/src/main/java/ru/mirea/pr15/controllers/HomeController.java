package ru.mirea.pr15.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.pr15.dto.PostOfficeResponse;
import ru.mirea.pr15.services.PostOfficeService;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("home")
public class HomeController {
    private final PostOfficeService postOfficeService;

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("posts", postOfficeService.getAll().stream().map(PostOfficeResponse::new).collect(Collectors.toList()));
        return "home";
    }
}

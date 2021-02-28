package ru.mirea.pr17.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.pr17.dto.DtoConverter;
import ru.mirea.pr17.dto.PostOfficeResponse;
import ru.mirea.pr17.services.PostOfficeService;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final PostOfficeService postOfficeService;
    private final DtoConverter dtoConverter;

    public HomeController(PostOfficeService postOfficeService, DtoConverter dtoConverter) {
        this.postOfficeService = postOfficeService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("posts", postOfficeService.getAll(dtoConverter::toPostOfficeResponseList));
        return "home";
    }
}

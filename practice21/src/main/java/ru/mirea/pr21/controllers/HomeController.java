package ru.mirea.pr21.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.pr21.dto.DtoConverter;
import ru.mirea.pr21.services.PostOfficeService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final PostOfficeService postOfficeService;
    private final DtoConverter dtoConverter;

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("posts", postOfficeService.getAll(dtoConverter::toPostOfficeResponseList));
        return "home";
    }
}

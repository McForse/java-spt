package ru.mirea.pr17.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr17.dto.DtoConverter;
import ru.mirea.pr17.dto.PostOfficeRequest;
import ru.mirea.pr17.services.PostOfficeService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("post")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;
    private final DtoConverter dtoConverter;

    @GetMapping
    public String index(@RequestParam(required = false) String name,
                        @RequestParam(required = false) String cityName,
                        Map<String, Object> model) {
        if (name != null || cityName != null) {
            model.put("posts", postOfficeService.getFiltered(name, cityName, dtoConverter::toPostOfficeResponseList));
        } else {
            model.put("posts", postOfficeService.getAll(dtoConverter::toPostOfficeResponseList));
        }

        return "post";
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

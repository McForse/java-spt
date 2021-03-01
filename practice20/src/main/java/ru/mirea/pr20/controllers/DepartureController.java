package ru.mirea.pr20.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.mirea.pr20.dto.DepartureRequest;
import ru.mirea.pr20.dto.DtoConverter;
import ru.mirea.pr20.services.DepartureService;
import ru.mirea.pr20.services.PostOfficeService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("departure")
public class DepartureController {
    private final DepartureService departureService;
    private final PostOfficeService postOfficeService;
    private final DtoConverter dtoConverter;

    @GetMapping
    public String index(@RequestParam(required = false) String type,
                        @RequestParam(required = false) String date,
                        Map<String, Object> model) {
        if (type != null || date != null) {
            model.put("departures", departureService.getFiltered(type, date, dtoConverter::toDepartureResponseList));
        } else {
            model.put("departures", departureService.getAll(dtoConverter::toDepartureResponseList));
        }

        model.put("posts", postOfficeService.getAll(dtoConverter::toPostOfficeResponseList));
        return "departure";
    }

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

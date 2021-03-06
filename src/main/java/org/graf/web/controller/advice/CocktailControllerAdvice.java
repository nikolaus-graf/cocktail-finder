package org.graf.web.controller.advice;

import org.graf.web.controller.CocktailController;
import org.graf.web.controller.ZutatenController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = CocktailController.class)
public class CocktailControllerAdvice {

    @ModelAttribute("classActiveCocktail")
    public String cssActivePage() {
        return "active";
    }

}

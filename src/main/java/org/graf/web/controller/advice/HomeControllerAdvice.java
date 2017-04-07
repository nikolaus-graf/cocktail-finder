package org.graf.web.controller.advice;

import org.graf.web.controller.HomeController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = HomeController.class)
public class HomeControllerAdvice {

    @ModelAttribute("classActiveHome")
    public String cssActivePage() {
        return "active";
    }

}

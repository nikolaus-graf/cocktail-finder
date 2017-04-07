package org.graf.web.controller.advice;

import org.graf.web.controller.ZutatenController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = ZutatenController.class)
public class ZutatenControllerAdvice {

    @ModelAttribute("classActiveZutaten")
    public String cssActivePage() {
        return "active";
    }

}

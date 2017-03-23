package org.graf.web.controller.advice;

import org.graf.web.controller.AdminController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = AdminController.class)
public class AdminControllerAdvice {

    @ModelAttribute("classActiveAdmin")
    public String cssActivePage() {
        return "active";
    }

}

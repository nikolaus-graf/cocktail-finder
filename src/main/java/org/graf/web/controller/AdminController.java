package org.graf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView admin() {
        Map<String, Object> attributeMap = new HashMap<>();

        return new ModelAndView("admin", attributeMap);
    }
}

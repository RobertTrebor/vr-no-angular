package de.lengsfeld.apps.vr.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException x, Model model) {
        log.error("Unhandled Exception", x);
        model.addAttribute("errormsg", x.getMessage());
        return "error";
    }

}

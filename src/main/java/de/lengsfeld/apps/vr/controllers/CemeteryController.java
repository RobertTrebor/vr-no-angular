package de.lengsfeld.apps.vr.controllers;

import de.lengsfeld.apps.vr.entity.Cemetery;
import de.lengsfeld.apps.vr.service.CemeteryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cemeteries")
public class CemeteryController {

    private CemeteryService cemeteryService;

    public CemeteryController(CemeteryService cemeteryService) {
        this.cemeteryService = cemeteryService;
    }

    @GetMapping
    public String showCemeteries(Model model, @RequestParam(name = "count", defaultValue = "2") int count) {
        List<Cemetery> cemeteryList = cemeteryService.findAllCemeteries();
        model.addAttribute("cemeteries", cemeteryList);
        return "cemeteries";
    }


}

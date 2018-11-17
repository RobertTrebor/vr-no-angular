package de.lengsfeld.apps.vr.controllers;

import de.lengsfeld.apps.vr.entity.Grave;
import de.lengsfeld.apps.vr.repository.GraveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graves")
public class GraveController {

    private final GraveRepository graveRepository;

    @Autowired
    public GraveController(GraveRepository graveRepository) {
        this.graveRepository = graveRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Grave> getAllGraves() {
        return graveRepository.findAll();
    }

}

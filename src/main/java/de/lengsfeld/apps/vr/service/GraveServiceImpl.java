package de.lengsfeld.apps.vr.service;

import de.lengsfeld.apps.vr.entity.Cemetery;
import de.lengsfeld.apps.vr.entity.Grave;
import de.lengsfeld.apps.vr.repository.GraveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("graveService")
@Transactional
public class GraveServiceImpl implements GraveService {

    @Autowired
    private GraveRepository graveRepository;

    @Override
    public Optional<Grave> findById(Long id) {
        return graveRepository.findById(id);
    }

    @Override
    public Grave findByName(String name) {
        return graveRepository.findGraveByFirstName(name);
    }

    @Override
    public List<Grave> findByCemetery(Cemetery cemetery) {
        List<Grave> graves = graveRepository.findGraveByCemetery(cemetery);
        return graves;
    }

    @Override
    public void saveGrave(Grave grave) {
        graveRepository.save(grave);
    }

    @Override
    public void updateGrave(Grave grave) {
        saveGrave(grave);
    }

    @Override
    public void deleteGraveById(Long id) {
        graveRepository.deleteById(id);
    }

    @Override
    public void deleteAllGraves() {
        graveRepository.deleteAll();
    }

    @Override
    public List<Grave> findAllGraves() {
        Iterable<Grave> graveIterable = graveRepository.findAll();
        List<Grave> graveList = new ArrayList<>();
        graveIterable.forEach(graveList::add);
        return graveList;
    }

    @Override
    public boolean isGraveExist(Grave grave) {
        return findByName(grave.getFirstName()) != null;
    }
}

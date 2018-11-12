package de.lengsfeld.apps.vr.service;

import de.lengsfeld.apps.vr.entity.Cemetery;
import de.lengsfeld.apps.vr.entity.Grave;
import de.lengsfeld.apps.vr.repository.CemeteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("cemeteryService")
@Transactional
public class CemeteryServiceImpl implements CemeteryService {

    @Autowired
    private CemeteryRepository cemeteryRepository;

    @Override
    public Optional<Cemetery> findById(Long id) {
        return cemeteryRepository.findById(id);
    }

    @Override
    public Cemetery findByName(String name) {
        return cemeteryRepository.findByName(name);
    }

    @Override
    public void saveCemetery(Cemetery cemetery) {
        cemeteryRepository.save(cemetery);
    }

    @Override
    public void updateCemetery(Cemetery cemetery) {
        saveCemetery(cemetery);
    }

    @Override
    public void deleteCemeteryById(Long id) {
        try {
            cemeteryRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllCemeteries() {
        cemeteryRepository.deleteAll();
    }

    @Override
    public List<Cemetery> findAllCemeteries() {
        Iterable<Cemetery> cemeteryIterable = cemeteryRepository.findAll();
        List<Cemetery> cemeteryList = new ArrayList<>();
        cemeteryIterable.forEach(cemeteryList::add);
        return cemeteryList;
    }

    @Override
    public boolean isCemeteryExist(Cemetery cemetery) {
        return findByName(cemetery.getName()) != null;
    }

    public boolean isGraveExist(Long id, Grave grave) {
        Optional<Cemetery> cemetery = findById(id);
        if (cemetery.get() != null) {
            List<Grave> graves = cemetery.get().getGraves();
            return graves.contains(grave);
        }
        return false;
    }

    public void saveGrave(Long id, Grave grave) {
        Optional<Cemetery> cemetery = findById(id);
        if (cemetery.get() != null) {
            List<Grave> graves = cemetery.get().getGraves();
            graves.add(grave);
            updateCemetery(cemetery.get());
        }
    }

}

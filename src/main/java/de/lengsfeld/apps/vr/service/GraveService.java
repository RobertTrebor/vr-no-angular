package de.lengsfeld.apps.vr.service;

import de.lengsfeld.apps.vr.entity.Cemetery;
import de.lengsfeld.apps.vr.entity.Grave;

import java.util.List;
import java.util.Optional;

public interface GraveService {

    Optional<Grave> findById(Long id);

    Grave findByName(String name);

    List<Grave> findByCemetery(Cemetery cemetery);

    void saveGrave(Grave grave);

    void updateGrave(Grave grave);

    void deleteGraveById(Long id);

    void deleteAllGraves();

    List<Grave> findAllGraves();

    boolean isGraveExist(Grave grave);
}

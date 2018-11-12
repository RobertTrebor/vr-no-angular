package de.lengsfeld.apps.vr.repository;

import de.lengsfeld.apps.vr.entity.VrUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<VrUser, Long> {

    Optional<VrUser> findByUserName(String userName);

    VrUser save(VrUser vrUser);

}

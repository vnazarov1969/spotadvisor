package ru.spotadvisor.backend.repository;

import ru.spotadvisor.backend.model.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vnazarov on 31/10/2017.
 */

public interface StuffRepository extends JpaRepository<Stuff, Integer> {
}


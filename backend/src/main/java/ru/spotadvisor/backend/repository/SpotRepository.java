package ru.spotadvisor.backend.repository;

import ru.spotadvisor.backend.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vnazarov on 30/10/2017.
 */

public interface SpotRepository extends JpaRepository<Spot, Integer> {
  List<Spot> findAllByOrderByName();
  List<Spot> findByCity_Name(String cityName);
}

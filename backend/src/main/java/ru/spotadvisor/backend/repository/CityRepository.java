package ru.spotadvisor.backend.repository;

//import org.springframework.data.repository.PagingAndSortingRepository;

import ru.spotadvisor.backend.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

;

/**
 * Created by vnazarov on 30/10/2017.
 */

public interface CityRepository extends JpaRepository<City, Integer> {
}

package ru.spotadvisor.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.spotadvisor.backend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vnazarov on 31/10/2017.
 */

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
  @Query(value = "select w1.* from workout w1 join \n" +
          "(select spot_id, date_trunc('day',max(start_time)) d from workout w group by spot_id) s1\n" +
          "on w1.spot_id = s1.spot_id and w1.start_time > s1.d ",
          nativeQuery = true
  )
  List<Workout> findLastDay();
}


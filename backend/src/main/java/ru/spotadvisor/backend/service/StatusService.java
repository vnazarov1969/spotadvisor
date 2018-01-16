package ru.spotadvisor.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spotadvisor.backend.dto.SpotStatusDTO;
import ru.spotadvisor.backend.model.Spot;
import ru.spotadvisor.backend.model.Workout;
import ru.spotadvisor.backend.repository.WorkoutRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created by vnazarov on 11/11/2017.
 */

@Service
public class StatusService {

  @Autowired
  private WorkoutRepository workoutRepository;


  public List<SpotStatusDTO> spotStatuses(List<Spot> spots){

    List<Workout> workouts = workoutRepository.findLastDay();

    //ToDo support distinct by user_id
//    Map<Integer, Long> spotCounts = workouts.stream()
//    .collect(groupingBy(((workout)->workout.getSpot().getId()), counting()));

    Map<Integer, Set<String>> spotUserIds = workouts.stream()
            .collect(groupingBy((Workout workout)->workout.getSpot().getId(), mapping(Workout::getUserId, toSet())));

    Map<Integer, Double> spotFuns = workouts.stream()
            .collect(groupingBy(((workout)->workout.getSpot().getId()), averagingDouble(
                    (workout)->workout.getFunRate() != null ? workout.getFunRate() : 0
            )));

    Map<Integer, Double> spotConditions = workouts.stream()
            .collect(groupingBy(((workout)->workout.getSpot().getId()), averagingDouble(
                    (workout)->workout.getConditionRate() != null ? workout.getConditionRate() : 0
            )));

    Map<Integer, Optional<Workout>> spotLastTimes = workouts.stream()
            .collect(groupingBy(((workout)->workout.getSpot().getId()),
                    maxBy(Comparator.comparing(Workout::getStartTime))));

    List<SpotStatusDTO> result = spots.stream().map(spot -> {
      SpotStatusDTO dto = new SpotStatusDTO();
      dto.setId(spot.getId());
      dto.setCityId(spot.getCity().getId());
      dto.setCityName(spot.getCity().getName());
      dto.setDisplayName(spot.getName());
      dto.setAvgPeople(spotUserIds.getOrDefault(spot.getId(), new HashSet<String>()).size());
      dto.setAvgFun(spotFuns.get(spot.getId()));
      dto.setAvgCondition(spotConditions.get(spot.getId()));
      spotLastTimes.getOrDefault(spot.getId(), Optional.empty()).ifPresent(workout->dto.setLastWorkout(workout.getStartTime()));
      return dto;
    }).collect(Collectors.toList());
    return result;
  }
}

package ru.spotadvisor.backend.service;

import ru.spotadvisor.backend.dto.WorkoutDTO;
import ru.spotadvisor.backend.model.City;
import ru.spotadvisor.backend.model.Spot;
import ru.spotadvisor.backend.model.Stuff;
import ru.spotadvisor.backend.model.Workout;
import ru.spotadvisor.backend.repository.CityRepository;
import ru.spotadvisor.backend.repository.SpotRepository;
import ru.spotadvisor.backend.repository.StuffRepository;
import ru.spotadvisor.backend.repository.WorkoutRepository;
import ru.spotadvisor.backend.user.SecurityContext;
import ru.spotadvisor.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by vnazarov on 11/11/2017.
 */

@Service
public class WorkoutService {

  @Autowired
  private WorkoutRepository workoutRepository;

  @Autowired
  private SpotRepository spotRepository;
  @Autowired
  private StuffRepository stuffRepository;
  @Autowired
  private CityRepository cityRepository;

  private static WorkoutDTO workoutDTOConvertor(Workout workout) {
    Integer[] arr = workout.getStuffs()==null? new Integer[]{} : workout.getStuffs().stream().map(stuff->stuff.getId()).collect(Collectors.toSet()).toArray(new Integer[]{});
    return new WorkoutDTO(workout.getId(),workout.getUserId(),workout.getSpot().getId(),workout.getSpot().getName(),workout.getStartTime(),workout.getEndTime(),workout.getFunRate(), workout.getConditionRate(), workout.getNotes(),workout.getType(), arr);
  }

  public List<WorkoutDTO> workouts(){
    return workoutRepository.findAll().stream().map(workout -> workoutDTOConvertor(workout)).collect(Collectors.toList());
  }

  public WorkoutDTO getWorkoutById(Integer id){
    Workout m = workoutRepository.findOne(id);
    return workoutDTOConvertor(m);
  }

  public WorkoutDTO updateWorkout(WorkoutDTO dto) {
    User currentUser = SecurityContext.getCurrentUser();
    //    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Workout workout = (dto.getId() == null)? new Workout(currentUser.getId()):workoutRepository.findOne(dto.getId());

    if (dto.getSpotId() != null) {
      workout.setSpot(spotRepository.findOne(dto.getSpotId()));
    }
    if (dto.getStartTime() != null){
      workout.setStartTime(dto.getStartTime());
    }
    workout.setEndTime(dto.getEndTime());

    workout.setNotes(dto.getNotes());

    if (dto.getStuffIds() != null) {
      Set<Stuff> stuffs =
        Arrays.stream(dto.getStuffIds()).map(id-> {
      try {
        return stuffRepository.findOne( id );
      }catch(Throwable throwable){
        return null;
      }}).filter(stuff -> stuff != null).collect(Collectors.toSet());
      workout.setStuffs(stuffs);
    }
    workout.setFunRate(dto.getFunRate());

    workout.setConditionRate(dto.getConditionRate());

    Workout m = workoutRepository.saveAndFlush(workout);
    return getWorkoutById(m.getId());
  }
  public boolean deleteWorkout(Integer id){
    workoutRepository.delete(id);
    return true;
  }

  public List<City> cities(){
    return cityRepository.findAll();
  }
  public List<Spot> spots(String city){
    if (city != null && !city.equalsIgnoreCase("all")) {
      return spotRepository.findByCity_Name(city);
    }
    return spotRepository.findAll();
  }

  public Spot updateSpot(Spot dto) {
    User currentUser = SecurityContext.getCurrentUser();
    //    CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Spot spot = (dto.getId() == null)? new Spot():spotRepository.findOne(dto.getId());
    spot.setName(dto.getName());
    spot.setNameLocal(dto.getNameLocal());
    spot.setLatitude(dto.getLatitude());
    spot.setLongitude(dto.getLongitude());
    if (dto.getCity() != null) {
      spot.setCity(cityRepository.findOne(dto.getCity().getId()));
    }
    Spot m = spotRepository.saveAndFlush(spot);
    return m;
  }


  public List<Stuff> stuffs(){
    return stuffRepository.findAll();
  }

}

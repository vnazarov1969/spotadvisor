package ru.spotadvisor.backend.controller;

import ru.spotadvisor.backend.dto.ProfileDTO;
import ru.spotadvisor.backend.dto.SpotStatusDTO;
import ru.spotadvisor.backend.dto.WorkoutDTO;
import ru.spotadvisor.backend.model.City;
import ru.spotadvisor.backend.model.Spot;
import ru.spotadvisor.backend.model.Stuff;
import ru.spotadvisor.backend.service.ProfileService;
import ru.spotadvisor.backend.service.StatusService;
import ru.spotadvisor.backend.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vnazarov on 10/11/2017.
 */
@RestController
@RequestMapping(value="/api")
public class WorkoutController {

  @Autowired
  WorkoutService workoutService;
  @Autowired
  StatusService statusService;


  @GetMapping(value="/workouts")
  public List<WorkoutDTO> workouts(){
    return workoutService.workouts();
  }

  @GetMapping(value="/cities")
  public List<City> cities(){
    return workoutService.cities();
  }

  @GetMapping(value="/stuffs")
  public List<Stuff> stuffs(){
    return workoutService.stuffs();
  }


  @GetMapping(value="/workouts/{id}")
  public WorkoutDTO getWorkoutById(@PathVariable Integer id){
    return workoutService.getWorkoutById(id);
  }

  @PostMapping(value="/workouts")
  public WorkoutDTO publishWorkout(@RequestBody WorkoutDTO workout){
    return workoutService.updateWorkout(workout);
//    return "Workout was published";
  }

  @PutMapping(value="/workouts")
  public String updateWorkout(@RequestBody WorkoutDTO workout){
    workoutService.updateWorkout(workout);
    return "Workout was updated";
  }

//  @GetMapping(value="/workouts/{username}")
//  public List<Workout> workoutsByUser(@PathVariable String username){
//    return workoutRepository.findAll(new Example<Workout>());
//  }

  @DeleteMapping(value = "/workouts/{id}")
  public boolean deleteWorkout(@PathVariable Integer id){
    workoutService.deleteWorkout(id);
    return true;
  }

  @GetMapping(value="/spots")
  public List<Spot> spots(@RequestParam(name = "city", required = false )String city){
    return workoutService.spots(city);
  }

  @PostMapping(value="/spots")
  public Spot publishSpot(@RequestBody Spot spot){
    return workoutService.updateSpot(spot);
  }

  @GetMapping(value="/spot_statuses")
  public List<SpotStatusDTO> spotStatuses(@RequestParam(name = "city", required = false )String city){
    List<Spot> spots = workoutService.spots(city);
    return statusService.spotStatuses(spots);
  }

}
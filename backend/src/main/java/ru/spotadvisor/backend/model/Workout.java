package ru.spotadvisor.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Set;


/**
 * Created by vnazarov on 31/10/2017.
 */

@Entity
public class Workout {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String userId;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "spot_id")
  private Spot spot;

  @Type(type ="org.hibernate.type.ZonedDateTimeType")
  private ZonedDateTime startTime;
  @Type(type ="org.hibernate.type.ZonedDateTimeType")
  private ZonedDateTime endTime;

  private Integer funRate;

  private Integer conditionRate;

  private String notes;

  private String type;

  @ManyToMany
  @JoinTable(name = "workout_stuff", joinColumns = @JoinColumn(name = "workout_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "stuff_id", referencedColumnName = "id"))
  private Set<Stuff> stuffs;

  public Workout(){};

  public Workout(String userId){
    this.userId = userId;
    this.startTime = ZonedDateTime.now();;
  };

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Spot getSpot() {
    return spot;
  }

  public void setSpot(Spot spot) {
    this.spot = spot;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z")
  public ZonedDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z")
  public ZonedDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(ZonedDateTime endTime) {
    this.endTime = endTime;
  }

  public Integer getFunRate() {
    return funRate;
  }

  public void setFunRate(Integer funRate) {
    this.funRate = funRate;
  }

  public Integer getConditionRate() {
    return conditionRate;
  }

  public void setConditionRate(Integer conditionRate) {
    this.conditionRate = conditionRate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Set<Stuff> getStuffs() {
    return stuffs;
  }

  public void setStuffs(Set<Stuff> stuffs) {
    this.stuffs = stuffs;
  }
};


package ru.spotadvisor.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class SpotStatusDTO {
  private Integer id;
  private Integer cityId;
  private String cityName;
  private String displayName;
  private Number avgCondition;
  private Number avgPeople;
  private Number avgFun;
  private ZonedDateTime lastWorkout;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Number getAvgCondition() {
    return avgCondition;
  }

  public void setAvgCondition(Number avgCondition) {
    this.avgCondition = avgCondition;
  }

  public Number getAvgPeople() {
    return avgPeople;
  }

  public void setAvgPeople(Number avgPeople) {
    this.avgPeople = avgPeople;
  }

  public Number getAvgFun() {
    return avgFun;
  }

  public void setAvgFun(Number avgFun) {
    this.avgFun = avgFun;
  }

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  public ZonedDateTime getLastWorkout() {
    return lastWorkout;
  }

  public void setLastWorkout(ZonedDateTime lastWorkout) {
    this.lastWorkout = lastWorkout;
  }
}

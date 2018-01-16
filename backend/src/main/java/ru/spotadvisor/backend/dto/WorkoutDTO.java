package ru.spotadvisor.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

/**
 * Created by vnazarov on 15/11/2017.
 */
public class WorkoutDTO {
  private Integer id;
  private String userId;
  private Integer spotId;
  private String spotName;

  private ZonedDateTime startTime;
  private ZonedDateTime endTime;

  private Integer funRate;

  private Integer conditionRate;

  private String notes;

  private String type;

  private Integer[] stuffIds;

  public WorkoutDTO(){};

  public WorkoutDTO(
          Integer id,
          String userId,
          Integer spotId,
          String spotName,
          ZonedDateTime startTime,
          ZonedDateTime endTime,
          Integer funRate,
          Integer conditionRate,
          String notes,
          String type,
          Integer[] stuffIds)
  {
    this.id=id;
    this.userId=userId;
    this.spotId=spotId;
    this.spotName=spotName;
    this.startTime=startTime;
    this.endTime=endTime;
    this.funRate=funRate;
    this.conditionRate=conditionRate;
    this.notes=notes;
    this.type=type;
    this.stuffIds=stuffIds;

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

  public Integer getSpotId() {
    return spotId;
  }

  public void setSpotId(Integer spotId) {
    this.spotId = spotId;
  }

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  public ZonedDateTime getStartTime() {
    return startTime;
  }
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  public ZonedDateTime getEndTime() {
    return endTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
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

  public Integer[] getStuffIds() {
    return stuffIds;
  }

  public void setStuffIds(Integer[] stuffIds) {
    this.stuffIds = stuffIds;
  }

  public String getSpotName() {
    return spotName;
  }

  public void setSpotName(String spotName) {
    this.spotName = spotName;
  }
}

package ru.spotadvisor.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * Created by vnazarov on 15/11/2017.
 */
@Entity
public class Stuff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String userId;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;
  private String name;
  private String model;
  private String modelBrand;
  private Integer modelYear;
  private String modelSize;
  private String notes;
  private Integer stuff_type_id;

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

  public ZonedDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(ZonedDateTime startTime) {
    this.startTime = startTime;
  }

  public ZonedDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(ZonedDateTime endTime) {
    this.endTime = endTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getModelBrand() {
    return modelBrand;
  }

  public void setModelBrand(String modelBrand) {
    this.modelBrand = modelBrand;
  }

  public Integer getModelYear() {
    return modelYear;
  }

  public void setModelYear(Integer modelYear) {
    this.modelYear = modelYear;
  }

  public String getModelSize() {
    return modelSize;
  }

  public void setModelSize(String modelSize) {
    this.modelSize = modelSize;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Integer getStuff_type_id() {
    return stuff_type_id;
  }

  public void setStuff_type_id(Integer stuff_type_id) {
    this.stuff_type_id = stuff_type_id;
  }
}

package ru.spotadvisor.backend.model;

import javax.persistence.*;

/**
 * Created by vnazarov on 29/10/2017.
 */

@Entity
public class Spot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  private String name;

  private String nameLocal;

  private Double longitude;

  private Double latitude;

  public Spot() {}

  public Spot(String name, City city) {
    this.name = name;
    this.city = city;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameLocal() {
    return nameLocal;
  }

  public void setNameLocal(String nameLocal) {
    this.nameLocal = nameLocal;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
}
package ru.spotadvisor.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vnazarov on 29/10/2017.
 */
@Entity
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String countryId;

  private String name;

  private String nameLocal;

  private String nameShort;

  @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
  @JsonIgnore()
  private Set<Spot> spots;

  public City(){};

  public City(String countryId, String name, String nameLocal, String nameShort ){
    this.countryId = countryId;
    this.name = name;
    this.nameLocal = nameLocal;
    this.nameShort = nameShort;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCountryId() {
    return countryId;
  }

  public void setCountryId(String countryId) {
    this.countryId = countryId;
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

  public String getNameShort() {
    return nameShort;
  }

  public void setNameShort(String nameShort) {
    this.nameShort = nameShort;
  }

  public Set<Spot> getSpots() {
    return spots;
  }

  public void setSpots(Set<Spot> spots) {
    this.spots = spots;
  }
}

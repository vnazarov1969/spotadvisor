package ru.spotadvisor.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

/**
 * Created by vnazarov on 15/11/2017.
 */
public class ProfileDTO {
  private String userId;
  private String displayName;
  private String pictureUrl;

  public ProfileDTO(){};

  public ProfileDTO(
          String userId,
          String displayName,
          String pictureUrl)
  {
    this.userId=userId;
    this.displayName=displayName;
    this.pictureUrl=pictureUrl;
  };

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }


  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }
}

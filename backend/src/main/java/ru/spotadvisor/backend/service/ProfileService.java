package ru.spotadvisor.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;
import ru.spotadvisor.backend.dto.ProfileDTO;
import ru.spotadvisor.backend.user.SecurityContext;

/**
 * Created by vnazarov on 11/11/2017.
 */

@Service
public class ProfileService {

  @Autowired
  private UsersConnectionRepository connectionRepository;

  public ProfileDTO getProfile(){
    String principal = SecurityContext.getCurrentUser().getId();
    Connection<Facebook> connection = connectionRepository.createConnectionRepository(principal).findPrimaryConnection(Facebook.class);

    if ( connection == null ){
      return null;
    }
    return new ProfileDTO(principal, connection.getDisplayName(), connection.getImageUrl());
  }
  public void signoutProfile(){
    String principal = SecurityContext.getCurrentUser().getId();
    connectionRepository.createConnectionRepository(principal).removeConnections("facebook");
    SecurityContext.remove();
  }


}

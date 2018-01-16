package ru.spotadvisor.backend.user;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

/**
 * Created by vnazarov on 27/10/2017.
 */
public class UserConnectionSignUp implements ConnectionSignUp {

  private final UserRepository userRepository;

  public UserConnectionSignUp(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String execute(Connection<?> connection) {
    UserProfile profile = connection.fetchUserProfile();
    User user = userRepository.findUserByUsername(profile.getName());
    if (user == null) {
      user = new User(profile.getEmail(), profile.getName());
      try {
        userRepository.createUser(user);
      } catch (UsernameAlreadyInUseException e) {
        return "";
      }
    }
    return user.getId();
  }

}
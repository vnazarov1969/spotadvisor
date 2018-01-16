package ru.spotadvisor.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spotadvisor.backend.dto.ProfileDTO;
import ru.spotadvisor.backend.dto.WorkoutDTO;
import ru.spotadvisor.backend.model.City;
import ru.spotadvisor.backend.model.Spot;
import ru.spotadvisor.backend.model.Stuff;
import ru.spotadvisor.backend.service.ProfileService;
import ru.spotadvisor.backend.service.WorkoutService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by vnazarov on 10/11/2017.
 */
@Controller
public class ProfileController {

  @Inject
  private Environment environment;

  @Autowired
  ProfileService profileService;

  @GetMapping(value="/api/profile")
  @ResponseBody
  public ProfileDTO profile(){
    return profileService.getProfile();
  }

  @GetMapping(value="/signout")
  public  String signout(){
    profileService.signoutProfile();
    return "redirect:" + this.environment.getProperty("dev.frontend.url");
  }

  @GetMapping(value="/login")
  public  String login(){
    profileService.signoutProfile();
    return "redirect:/signin";
  }

//  @GetMapping(value="/home")
//  @ResponseBody
//  public  String home(){
//   String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//    return "Current User: ".concat(userName);
//  }

//  @PostMapping(value="/")
//  public String tabFacebook(HttpServletRequest request,
//                            HttpServletResponse response, Model model){
//    return "redirect:/";
//  }

//  @GetMapping(value="/")
//  public String index(HttpServletRequest request,
//                            HttpServletResponse response, Model model){
//    return "redirect:/index.html/#/frame/track";
//  }


}
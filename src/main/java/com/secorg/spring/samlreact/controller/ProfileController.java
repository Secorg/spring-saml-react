package com.secorg.spring.samlreact.controller;

import com.secorg.spring.samlreact.services.profile.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * {@link ProfileController} handles request to get the logged in  user profile.
 */
@Controller
public class ProfileController {

  /**
   * Get's the logged in user {@link Profile}.
   * @param authentication {@link Authentication} holds the authenticated user.
   * @return {@link Profile} instance.
   */
  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  @ResponseBody
  public Profile getUserProfile(Authentication authentication) {
    String email = authentication.getName();
    return new Profile(email);
  }
}

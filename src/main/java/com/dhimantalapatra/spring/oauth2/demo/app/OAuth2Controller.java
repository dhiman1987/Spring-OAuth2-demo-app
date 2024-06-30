package com.dhimantalapatra.spring.oauth2.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class OAuth2Controller {

    Logger log = LoggerFactory.getLogger(OAuth2Controller.class);
    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/github";
    }

    @GetMapping("/home")
    public ModelAndView home(OAuth2AuthenticationToken authentication) {
        OAuth2User user = authentication.getPrincipal();
        String username = user.getAttribute("login");
        String email = user.getAttribute("email");
        log.info("Logged in user details. username={}, email={}",username,email);
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("username",username);
        mav.addObject("email",email);
        return mav;

    }
}
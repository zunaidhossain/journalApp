package com.edigest.journalApp.controller;


import com.edigest.journalApp.api.response.ProgrammingQuotes;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import com.edigest.journalApp.repository.UserRepositoryImpl;
import com.edigest.journalApp.service.ProgrammingQuotesService;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProgrammingQuotesService programmingQuotesService;

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @GetMapping("/sayhello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return "Hello "+username;
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ProgrammingQuotes programmingQuotes = programmingQuotesService.getQuote("Linus Torvalds");
        String msg = "";
        if(programmingQuotes!=null) {
            msg = ", a quote for you - '"+programmingQuotes.getContent()+"'";
        }
        return new ResponseEntity<>("Hi "+authentication.getName() + msg, HttpStatus.OK);
    }
}

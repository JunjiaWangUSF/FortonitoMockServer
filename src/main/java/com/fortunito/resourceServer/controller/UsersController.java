package com.fortunito.resourceServer.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/status/check")
    public String status() {
        return "Working..";
    }

    @Secured("ROLE_developer")  // Only developer can use this function to delete user
    @DeleteMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return "Delete user with id " + id;
    }

}

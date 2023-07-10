package com.fortunito.resourceServer.controller;



import com.fortunito.resourceServer.response.UserDetails;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/status/check")
    public String status() {
        return "Working..";
    }
    // only user has developer role and user id = jwt user id can invoke delete method.
    // # is method.
    @PreAuthorize("hasAnyAuthority('ROLE_developer') or #id == #jwt.subject")
    //@Secured("ROLE_developer")  // Only developer can use this function to delete user
    @DeleteMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {

        //JWT subject is user id
        return "Delete user with id " + id + " and JWT subject" + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserDetails getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        //TODO  select from database
        //JWT subject is user id
        return  new UserDetails("junjia", "wang", "81540bc2-047b-41d1-a074-802d26292793");
    }

}

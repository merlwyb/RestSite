package com.example.rest.controller;

import com.example.rest.entity.User;
import com.example.rest.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
            ResponseEntity<List<User>> all() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    User newUser(@RequestBody User newUser) {
        return userService.save(newUser);
    }

    @GetMapping("/{id}")
    EntityModel<User> findUserById(@PathVariable Long id) {
        User user = userService.findById(id);

        return EntityModel.of(user,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findUserById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).all()).withRel("users"));
        //return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

//    @PutMapping("/{id}")
//    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
//        return userService.findById(id)
//                .map(user -> {
//                    user.setFullName(newUser.getFullName());
//                    return userService.save(user);
//                }).orElseGet(() -> {
//                    newUser.setId(id);
//                    return userService.save(newUser);
//                });
//    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

}

package com.example.userdirectory.userdirectorycassandra.controller;

import com.example.userdirectory.userdirectorycassandra.UserNotFoundException;
import com.example.userdirectory.userdirectorycassandra.assembler.UserModelAssembler;
import com.example.userdirectory.userdirectorycassandra.model.User;
import com.example.userdirectory.userdirectorycassandra.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserController {

    private final UserRepository repository;

    private final UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }
    //aggregate root resource
    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all(){
        //use stream to sequentially iterator over items in collection
        //use method reference to simplify controller
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    //Use ResponseEntity to create an HTTP 201 Created status message and return the model-based version of the saved object.
    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser){

        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //single item
    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        User  user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id){

        User updatedUser = repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setPhoneNumber(newUser.getPhoneNumber());
                    user.setAddress(newUser.getAddress());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });

        //User object built from .save() is wrapped into an EntityModel<User> object by the assembler.
        EntityModel<User> entityModel = assembler.toModel(updatedUser);

        //return a more detailed response code using ReponseEntity wrapper and get link created by assembler with SELF relation. Then change link to URI.
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    //return ResponseEntity of HTTP 204 noContent response
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

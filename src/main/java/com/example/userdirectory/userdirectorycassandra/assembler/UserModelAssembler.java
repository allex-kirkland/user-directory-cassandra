package com.example.userdirectory.userdirectorycassandra.assembler;

import com.example.userdirectory.userdirectorycassandra.controller.UserController;
import com.example.userdirectory.userdirectorycassandra.model.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user){

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}

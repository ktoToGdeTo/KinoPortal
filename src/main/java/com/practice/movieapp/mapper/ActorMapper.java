package com.practice.movieapp.mapper;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.model.Actor;

public class ActorMapper {

    public static ActorDto mapToActorDto(Actor actor){
        return new ActorDto(
               actor.getActorId(),
               actor.getFullname(),
               actor.getBirthdate(),
               actor.getDescription()
        );

    }

}

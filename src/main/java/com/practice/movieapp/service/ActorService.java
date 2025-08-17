package com.practice.movieapp.service;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.model.Actor;

import java.util.List;

public interface ActorService {

    ActorDto getActorDtoById(Long actorId);

    Actor getActorById(Long actorId);

    List<ActorDto> getAllActors();

    List<ActorDto> foundActors(String name);

}

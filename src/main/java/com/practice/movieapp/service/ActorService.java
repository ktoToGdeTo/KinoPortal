package com.practice.movieapp.service;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.dto.MovieDto;

import java.util.List;

public interface ActorService {

    ActorDto getActorById(Long actorId);

    List<ActorDto> getAllActors();

    List<ActorDto> foundActors(String name);

}

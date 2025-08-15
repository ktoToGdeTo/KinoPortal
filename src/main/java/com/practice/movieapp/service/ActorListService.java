package com.practice.movieapp.service;

import com.practice.movieapp.model.Actorlist;

import java.util.List;

public interface ActorListService {

    List<Actorlist> getMoviesByActorId(Long id);

    List<Actorlist> getActorsByMovieId(Long id);
}

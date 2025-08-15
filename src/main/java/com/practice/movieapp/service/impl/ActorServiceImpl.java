package com.practice.movieapp.service.impl;

import com.practice.movieapp.dto.ActorDto;
import com.practice.movieapp.dto.MovieDto;
import com.practice.movieapp.exceptions.ResourceNotFoundExcepetion;
import com.practice.movieapp.mapper.ActorMapper;
import com.practice.movieapp.mapper.MovieMapper;
import com.practice.movieapp.model.Actor;
import com.practice.movieapp.model.Movie;
import com.practice.movieapp.repository.ActorRepository;
import com.practice.movieapp.service.ActorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;


    @Override
    public ActorDto getActorById(Long actorId) {
        Actor foundActor =  actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundExcepetion("Actor with ID " + actorId + " not found."));
        return ActorMapper.mapToActorDto(foundActor);
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream().map((actor) -> ActorMapper.mapToActorDto(actor))
                .collect(Collectors.toList());
    }

    @Override
    public List<ActorDto> foundActors(String name) {
        String searchName = name.replaceAll("\\s+", " ");
        List<Actor> actors = actorRepository.findAllByFullnameContainsIgnoreCase(searchName.trim());
        return actors.stream().map((actor) -> ActorMapper.mapToActorDto(actor))
                .collect(Collectors.toList());
    }

    public List<MovieDto> listActorsFilm(Long id){
    //    List<Movie> movies = actorRepository.findMoviesByActorId(id);


        return null;
    }
}

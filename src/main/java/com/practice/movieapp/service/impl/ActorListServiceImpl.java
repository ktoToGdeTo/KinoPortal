package com.practice.movieapp.service.impl;

import com.practice.movieapp.model.Actorlist;
import com.practice.movieapp.repository.ActorlistRepository;
import com.practice.movieapp.service.ActorListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ActorListServiceImpl implements ActorListService {

    private final ActorlistRepository actorlistRepository;

    @Override
    public List<Actorlist> getMoviesByActorId(Long id) {
        return actorlistRepository.findActorRolesByActorId(id);
    }

    @Override
    public List<Actorlist> getActorsByMovieId(Long id) {
        return actorlistRepository.findCastByMovieId(id);
    }
}

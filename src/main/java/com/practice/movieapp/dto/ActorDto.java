package com.practice.movieapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {

    private Long actor_id;

    private String fullname;

    private Date birthdate;

    private String description;
}

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
public class MovieDto {

    private Long movie_id;

    private String title;

    private String genre;

    private String description;

    private Date dateRelease;

    private String director;

}

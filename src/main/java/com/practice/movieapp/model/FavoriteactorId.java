package com.practice.movieapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FavoriteactorId implements Serializable {
    private static final long serialVersionUID = -6197140395142819661L;
    @Column(name = "userid", nullable = false)
    private Long userId;

    @Column(name = "actorid", nullable = false)
    private Long actorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FavoriteactorId entity = (FavoriteactorId) o;
        return Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, userId);
    }

}
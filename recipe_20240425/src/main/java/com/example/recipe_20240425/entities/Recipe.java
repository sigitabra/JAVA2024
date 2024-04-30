package com.example.recipe_20240425.entities;

import com.example.recipe_20240425.enums.RecipeDifficultyLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private RecipeDifficultyLevel difficulty;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @CreationTimestamp
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;


}

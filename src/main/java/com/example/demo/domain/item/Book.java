package com.example.demo.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")
@SuperBuilder
@NoArgsConstructor
public class Book extends Item{
    private String author;
    private String isbn;
}

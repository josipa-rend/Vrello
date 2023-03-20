package com.jrendulic.Vrello.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="card")
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @JoinColumn(name = "board")
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonIgnore
    private Board board;
    @Column(name = "board", insertable = false, updatable = false)
    private Long boardId;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    private Line line;

}

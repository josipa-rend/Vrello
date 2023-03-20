package com.jrendulic.Vrello.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name="line")
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "lines")
    @JsonBackReference(value = "board-line")
    private List<Board> boards = new ArrayList<>();
    @OneToMany(mappedBy = "line")
//    @JsonBackReference(value = "card-line")
    private List<Card> cards = new ArrayList<>();

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return id.equals(line.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

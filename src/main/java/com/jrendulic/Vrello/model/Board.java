package com.jrendulic.Vrello.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "board_users",
                joinColumns = @JoinColumn(name = "board_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
//    @JsonManagedReference
    private Set<User> members = new HashSet<>();
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "board_lines",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "line_id"))
//    @JsonManagedReference
    private Set<Line> lines = new HashSet<>();
    @OneToMany(mappedBy = "board")
    @JsonBackReference(value = "card-board")
    private List<Card> cards = new ArrayList<>();

    public void addMember(User user) {
        members.add(user);
        user.getBoards().add(this);
    }

    public void removeMember(User user) {
        members.remove(user);
        user.getBoards().remove(this);
    }

    public void addLine(Line line) {
        lines.add(line);
        line.getBoards().add(this);
    }

    public void removeLine(Line line) {
        lines.remove(line);
        line.getBoards().remove(this);
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return id.equals(board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

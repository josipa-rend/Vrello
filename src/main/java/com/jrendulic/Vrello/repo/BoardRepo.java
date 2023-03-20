package com.jrendulic.Vrello.repo;

import com.jrendulic.Vrello.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepo extends JpaRepository<Board, Long> {

    void deleteBoardById(Long id);

    Optional<Board> findBoardById(Long id);

    Optional<Board> findBoardByName(String boardName);
}

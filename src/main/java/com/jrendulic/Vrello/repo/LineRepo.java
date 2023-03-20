package com.jrendulic.Vrello.repo;

import com.jrendulic.Vrello.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LineRepo extends JpaRepository<Line, Long> {
    Optional<Line> findLineById(Long id);

    void deleteLineById(Long id);

    Optional<Line> findLineByName(String lineName);

}

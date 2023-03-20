package com.jrendulic.Vrello.repo;

import com.jrendulic.Vrello.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepo extends JpaRepository<Card, Long> {
    Optional<Card> findCardById(Long id);

    void deleteCardById(Long id);

}
package com.jrendulic.Vrello.controller;

import com.jrendulic.Vrello.model.Card;
import com.jrendulic.Vrello.service.CardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @GetMapping("/all")
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.findAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long id) {
        Card card = cardService.findCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        Card newCard = cardService.addCard(card);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        Card updateCard = cardService.updateCard(card);
        return new ResponseEntity<>(updateCard, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/setboard/{boardId}")
    public ResponseEntity<Card> addCardToBoard(@PathVariable("id") Long cardId, @PathVariable("boardId") Long boardId) {
        Card card = cardService.addCardToBoard(boardId, cardId);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/removeboard")
    public ResponseEntity<?> removeCardFromBoard(@PathVariable("id") Long id) {
        cardService.removeCardFromBoard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/setline/{lineId}")
    public ResponseEntity<?> addCardToLine(@PathVariable("id") Long cardId, @PathVariable("lineId") Long lineId) {
        cardService.addCardToLine(lineId, cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/removeline")
    public ResponseEntity<?> removeCardFromLine(@PathVariable("id") Long id) {
        cardService.removeCardFromLine(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

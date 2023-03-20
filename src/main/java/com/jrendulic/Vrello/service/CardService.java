package com.jrendulic.Vrello.service;

import com.jrendulic.Vrello.exception.EntityNotFoundException;
import com.jrendulic.Vrello.model.Board;
import com.jrendulic.Vrello.model.Card;
import com.jrendulic.Vrello.model.Line;
import com.jrendulic.Vrello.repo.BoardRepo;
import com.jrendulic.Vrello.repo.CardRepo;
import com.jrendulic.Vrello.repo.LineRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepo cardRepo;
    private final BoardRepo boardRepo;
    private final LineRepo lineRepo;

    public Card addCard(Card card) { return cardRepo.save(card); }

    public List<Card> findAllCards() { return cardRepo.findAll(); }

    public Card updateCard(Card card) { return cardRepo.save(card); }

    public Card findCardById(Long id) {
        return cardRepo.findCardById(id)
                .orElseThrow( () -> new EntityNotFoundException("Card by id " + id + " was not found"));
    }

    public void deleteCard(Long id) {
        cardRepo.deleteCardById(id);
    }

    @Transactional
    public Card addCardToBoard(Long boardId, Long cardId) {
        Board board = boardRepo.findBoardById(boardId)
                .orElseThrow( () -> new EntityNotFoundException("Board by id " + boardId + " was not found"));
        Card card = findCardById(cardId);
        if (card.getLine()!= null && !board.getLines().contains(card.getLine()))
            throw  new EntityNotFoundException("Respective line not found in board " + boardId);
        card.setBoard(board);
        return card;
    }

    @Transactional
    public void removeCardFromBoard(Long cardId) {
        Card card = findCardById(cardId);
        card.setBoard(null);
    }

    @Transactional
    public void addCardToLine(Long lineId, Long cardId) {
        Line line = lineRepo.findLineById(lineId)
                .orElseThrow( () -> new EntityNotFoundException("Line by id " + lineId + " was not found"));
        Card card = findCardById(cardId);
        if (card.getBoard() != null && !line.getBoards().contains(card.getBoard()))
            throw  new EntityNotFoundException("Line by id " + lineId + " was not found in the board");
        card.setLine(line);
    }

    @Transactional
    public void removeCardFromLine(Long cardId) {
        Card card = findCardById(cardId);
        card.setLine(null);
    }
}

package com.jrendulic.Vrello.controller;

import com.jrendulic.Vrello.model.Board;
import com.jrendulic.Vrello.model.Card;
import com.jrendulic.Vrello.model.Line;
import com.jrendulic.Vrello.model.User;
import com.jrendulic.Vrello.service.BoardService;
import com.jrendulic.Vrello.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/all")
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.findAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable("id") Long id) {
        Board board = boardService.findBoardById(id);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Board> addBoard(@RequestBody Board board) {
        Board newBoard = boardService.addBoard(board);
        return new ResponseEntity<>(newBoard, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Board> updateBoard(@RequestBody Board board) {
        Board updateBoard = boardService.updateBoard(board);
        return new ResponseEntity<>(updateBoard, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/member/add/{memberId}")
    public ResponseEntity<?> addMemberToBoard(@PathVariable("id") Long boardId, @PathVariable("memberId") Long memberId) {
        boardService.addMemberToBoard(boardId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/member/remove/{memberId}")
    public ResponseEntity<?> removeMemberFromBoard(@PathVariable("id") Long boardId, @PathVariable("memberId") Long memberId) {
        boardService.removeMemberFromBoard(boardId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<Set<User>> getMembersForBoard(@PathVariable("id") Long id) {
        Set<User> users = boardService.findBoardById(id).getMembers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{id}/line/add")
    public ResponseEntity<?> addLineToBoard(@PathVariable("id") Long boardId, @RequestBody Line line) {
        boardService.addLineToBoard(boardId, line);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/line/add/{lineId}")
    public ResponseEntity<?> addLineToBoard(@PathVariable("id") Long boardId, @PathVariable("lineId") Long lineId) {
        boardService.addLineToBoard(boardId, lineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/line/remove/{lineId}")
    public ResponseEntity<?> removeLineFromBoard(@PathVariable("id") Long boardId, @PathVariable("lineId") Long lineId) {
        boardService.removeLineFromBoard(boardId, lineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/lines")
    public ResponseEntity<Set<Line>> getLinesForBoard(@PathVariable("id") Long id) {
        Set<Line> lines = boardService.findBoardById(id).getLines();
        return new ResponseEntity<>(lines, HttpStatus.OK);
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getCardsForBoard(@PathVariable("id") Long id) {
        List<Card> cards = boardService.findBoardById(id).getCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}

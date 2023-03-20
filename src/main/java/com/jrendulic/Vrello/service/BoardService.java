package com.jrendulic.Vrello.service;

import com.jrendulic.Vrello.exception.EntityNotFoundException;
import com.jrendulic.Vrello.model.Board;
import com.jrendulic.Vrello.model.Line;
import com.jrendulic.Vrello.model.User;
import com.jrendulic.Vrello.repo.BoardRepo;
import com.jrendulic.Vrello.repo.LineRepo;
import com.jrendulic.Vrello.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepo boardRepo;
    private final UserRepo userRepo;
    private final LineRepo lineRepo;

    public Board addBoard(Board board) {
        return boardRepo.save(board);
    }

    public List<Board> findAllBoards() { return boardRepo.findAll(); }

    public Board updateBoard(Board board) { return boardRepo.save(board); }

    @Transactional
    public void addMemberToBoard(Long boardId, Long userId) {
        Board board = findBoardById(boardId);
        User user = userRepo.findUserById(userId).orElseThrow( () -> new EntityNotFoundException("Employee by id " + userId + " was not found"));
        board.addMember(user);
    }

    @Transactional
    public void removeMemberFromBoard(Long boardId, Long userId) {
        Board board = findBoardById(boardId);
        User user = userRepo.findUserById(userId).orElseThrow( () -> new EntityNotFoundException("Employee by id " + userId + " was not found"));
        board.removeMember(user);
    }

    @Transactional
    public void addLineToBoard(String boardName, String lineName) {
        Board board = boardRepo.findBoardByName(boardName)
                .orElseThrow( () -> new EntityNotFoundException("Board by name " + boardName + " was not found"));
        Line line = lineRepo.findLineByName(lineName)
                .orElseThrow( () -> new EntityNotFoundException("Line by name " + lineName + " was not found"));
        board.addLine(line);
    }

    @Transactional
    public void addLineToBoard(Long boardId, Long lineId) {
        Board board = findBoardById(boardId);
        Line line = lineRepo.findLineById(lineId).orElseThrow( () -> new EntityNotFoundException("Line by id " + lineId + " was not found"));
        board.addLine(line);
    }

    @Transactional
    public void addLineToBoard(Long boardId, Line line) {
        Board board = findBoardById(boardId);
        lineRepo.save(line);
        board.addLine(line);
    }


    @Transactional
    public void removeLineFromBoard(Long boardId, Long lineId) {
        Board board = findBoardById(boardId);
        Line line = lineRepo.findLineById(lineId)
                .orElseThrow( () -> new EntityNotFoundException("Line by id " + lineId + " was not found"));
        board.removeLine(line);
    }

    public Board findBoardById(Long id) {
        return boardRepo.findBoardById(id)
                .orElseThrow( () -> new EntityNotFoundException("Board by id " + id + " was not found"));
    }

    public void deleteBoard(Long id) {
        boardRepo.deleteBoardById(id);
    }
}

package com.jrendulic.Vrello.service;


import com.jrendulic.Vrello.exception.EntityNotFoundException;
import com.jrendulic.Vrello.model.Line;
import com.jrendulic.Vrello.repo.LineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineService {

    private final LineRepo lineRepo;

    public Line addLine(Line line) { return lineRepo.save(line); }

    public List<Line> findAllLines() { return lineRepo.findAll(); }

    public Line updateLine(Line line) { return lineRepo.save(line); }

    public Line findLineById(Long id) {
        return lineRepo.findLineById(id)
                .orElseThrow( () -> new EntityNotFoundException("Line by id " + id + " was not found"));
    }

    public void deleteLine(Long id) {
        lineRepo.deleteLineById(id);
    }
}

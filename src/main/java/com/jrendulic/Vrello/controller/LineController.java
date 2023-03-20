package com.jrendulic.Vrello.controller;

import com.jrendulic.Vrello.model.Line;
import com.jrendulic.Vrello.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/line")
public class LineController {
    private final LineService lineService;

    @GetMapping("/all")
    public ResponseEntity<List<Line>> getAllLines() {
        List<Line> lines = lineService.findAllLines();
        return new ResponseEntity<>(lines, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Line> getLineById(@PathVariable("id") Long id) {
        Line line = lineService.findLineById(id);
        return new ResponseEntity<>(line, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Line> addLine(@RequestBody Line line) {
        Line newLine = lineService.addLine(line);
        return new ResponseEntity<>(newLine, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Line> updateLine(@RequestBody Line line) {
        Line updateLine = lineService.updateLine(line);
        return new ResponseEntity<>(updateLine, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLine(@PathVariable("id") Long id) {
        lineService.deleteLine(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

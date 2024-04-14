package com.estivman.stacks_project.maze_solver.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estivman.stacks_project.maze_solver.model.MazeSection;
import com.estivman.stacks_project.maze_solver.service.MazeSolverService;

@RestController
@RequestMapping()
public class MazeSolverController {

    @Autowired
    private MazeSolverService mazeSolverService;
    @Value("${maze.example.location}")
    private String mazeExample;

    // TODO: Change for post method with file manager
    @GetMapping()
    public ResponseEntity<Object> loadMaze() throws IOException {
        try {
            MazeSection[][] maze = mazeSolverService.loadMaze(mazeExample);

            return ResponseEntity.status(HttpStatus.OK).body(maze);
        } catch (IOException e) {
            throw e;
        }

    }

    @GetMapping("/solve")
    public ResponseEntity<Object> solveMaze() throws IOException {
        this.loadMaze();
        List<MazeSection> result = mazeSolverService.solveMaze();
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
}

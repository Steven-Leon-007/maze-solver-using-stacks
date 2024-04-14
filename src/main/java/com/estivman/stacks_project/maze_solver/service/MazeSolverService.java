package com.estivman.stacks_project.maze_solver.service;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estivman.stacks_project.maze_solver.model.MazeSection;
import com.estivman.stacks_project.maze_solver.model.SectionType;
import com.estivman.stacks_project.maze_solver.utils.FileLoaderUtils;

@Service
public class MazeSolverService {
    private MazeSection[][] maze;
    private Stack<MazeSection> stack;
    FileLoaderUtils fileLoaderUtils;

    @Autowired
    public MazeSolverService() {
        this.fileLoaderUtils = new FileLoaderUtils();
    }

    public MazeSection[][] loadMaze(String path) throws IOException {
        List<String> lines = fileLoaderUtils.loadMazeFromFile(path);
        int rows = lines.size();
        int cols = lines.get(0).length();
        maze = new MazeSection[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                char character = line.charAt(j);
                maze[i][j] = convertToSection(character, i, j);
            }
        }
        return maze;
    }

    public void solveMaze(){
        
    }

    private MazeSection convertToSection(char character, int row, int col) {
        switch (character) {
            case 'X':
                return new MazeSection(row, col, SectionType.WALL, false);

            case ' ':
                return new MazeSection(row, col, SectionType.EMPTY_SPACE, false);

            case 'S':
                return new MazeSection(row, col, SectionType.START, false);

            case 'F':
                return new MazeSection(row, col, SectionType.FINISH, false);

            default:
                throw new IllegalArgumentException("Character unknown: " + character);
        }
    }
}

package com.estivman.stacks_project.maze_solver.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.estivman.stacks_project.maze_solver.model.MazeSection;
import com.estivman.stacks_project.maze_solver.model.SectionType;
import com.estivman.stacks_project.maze_solver.utils.FileLoaderUtils;

@Service
public class MazeSolverService {
    private MazeSection[][] maze;
    private Stack<MazeSection> stackMaze;
    FileLoaderUtils fileLoaderUtils;

    @Autowired
    public MazeSolverService() {
        this.fileLoaderUtils = new FileLoaderUtils();
    }

    public MazeSection[][] loadMaze(MultipartFile fileUploaded) throws IOException {

        List<String> lines = fileLoaderUtils.processFile(fileUploaded);
        if (lines == null) {
            return null;
        }
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

    public Stack<MazeSection> solveMaze() {
        stackMaze = new Stack<>();
        stackMaze.push(this.findMazeStart());

        while (!stackMaze.isEmpty()) {
            MazeSection current = stackMaze.peek();
            current.setSectionVisited(true);

            if (current.getType() == SectionType.FINISH) {
                break;
            }

            MazeSection[] sectionsAvailable = this.validSurroundings(current);

            boolean canMove = false;
            for (MazeSection section : sectionsAvailable) {
                if (!section.isSectionVisited()) {
                    stackMaze.push(section);
                    canMove = true;
                    break;
                }
            }
            if (!canMove) {
                stackMaze.pop();
            }

        }

        for (MazeSection section : stackMaze) {
            if (section.getType() == SectionType.EMPTY_SPACE) {
                section.setType(SectionType.SOLUTION);
            }
        }

        return stackMaze;
    }


    private MazeSection findMazeStart() {

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j].getType() == SectionType.START) {
                    return maze[i][j];
                }
            }
        }
        return null;
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
                throw new IllegalArgumentException("Character invalid: " + character);
        }
    }

    private MazeSection[] validSurroundings(MazeSection section) {
        int row = section.getRow();
        int col = section.getColumn();
        List<MazeSection> validNeighbors = new ArrayList<>();

        int[] possibleMovesRows = { -1, 0, 0, 1 };
        int[] possibleMovesCols = { 0, -1, 1, 0 };

        for (int i = 0; i < possibleMovesCols.length; i++) {

            int newRow = row + possibleMovesRows[i];
            int newCol = col + possibleMovesCols[i];

            if (newRow >= 0 && newRow < maze.length &&
                    newCol >= 0 && newCol < maze[0].length) {
                MazeSection neighbor = maze[newRow][newCol];
                if (neighbor.getType() != SectionType.WALL) {
                    validNeighbors.add(neighbor);
                }
            }
        }

        return validNeighbors.toArray(new MazeSection[0]);
    }
}

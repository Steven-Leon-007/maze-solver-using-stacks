package com.estivman.stacks_project.maze_solver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MazeSection {
    private int row;
    private int column;
    private SectionType type;
    private boolean sectionVisited = false;
}

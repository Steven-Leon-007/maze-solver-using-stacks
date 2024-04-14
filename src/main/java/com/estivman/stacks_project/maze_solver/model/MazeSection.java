package com.estivman.stacks_project.maze_solver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MazeSection {
    private int row;
    private int column;
    private SectionType type;
    private boolean sectionVisited = false;
}

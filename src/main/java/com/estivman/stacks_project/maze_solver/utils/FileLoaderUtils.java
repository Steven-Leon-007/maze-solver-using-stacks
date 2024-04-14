package com.estivman.stacks_project.maze_solver.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileLoaderUtils {
    public List<String> loadMazeFromFile(String path) throws IOException {
        return FileUtils.readLines(new File(path), "UTF-8");
    }
}

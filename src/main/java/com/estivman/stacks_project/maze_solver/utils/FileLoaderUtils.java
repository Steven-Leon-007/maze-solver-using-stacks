package com.estivman.stacks_project.maze_solver.utils;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileLoaderUtils {
    public List<String> processFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }
        
        List<String> lines = IOUtils.readLines(file.getInputStream(), "UTF-8");
        
        return lines;
    }
}

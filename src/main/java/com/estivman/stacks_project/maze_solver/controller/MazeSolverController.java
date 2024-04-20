package com.estivman.stacks_project.maze_solver.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.estivman.stacks_project.maze_solver.model.MazeSection;
import com.estivman.stacks_project.maze_solver.service.MazeSolverService;

@Controller
@RequestMapping()
public class MazeSolverController {

    @Autowired
    private MazeSolverService mazeSolverService;
    private MazeSection[][] maze;

    @GetMapping("/")
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("maze", maze);

        return modelAndView;
    }

    @PostMapping("/load")
    public String loadMaze(Model model, @RequestParam("file") MultipartFile fileUploaded) throws IOException {
        try {
            maze = mazeSolverService.loadMaze(fileUploaded);
            return "redirect:/";
        } catch (IOException e) {
            throw e;
        }
    }

    @GetMapping("/solve")
    public String solveMaze() throws IOException {
        List<MazeSection> result = mazeSolverService.solveMaze();
        return "redirect:/";

    }
}

# Maze Solver
## Developed with ♥ by Diego Rodriguez and Steven León (C124 Studios)

Maze Solver is a service dedicated to programatically solve mazes through the use of Java Stack structures, also known as LIFO (Last in, First Out). The stack is iterated as a recursion, adds the elements from the matrix to the stack, validates and keeps with the path that solves the maze. The project implements the use of TXT files uploaded by the user with a syntax that the project can understand and translate in standard maze's structure, there's a example file in the root of the project, in case is needed.


### Specifications
- Java JDK version 17 or newer versions
- 8080 Port open for let the app run correctly

#### Architecture used: RESTful API

# Files
## Model folder
Inside of this folder are the objects used to work in:
- *MazeSection*.java
	This class has 4 attributes and implements Lombok for generate Getter, Setters and constructors for the class:	
	- *row (int):* The row position in the maze of the specific section.
	- *col (int):* The column position in the maze of the specific section.
	- *type (SectionType):* The type of the section, it comes from an enumeration and is passed from the user like a char.
	- *sectionVisited (boolean):* A boolean value initialized as false that indicates the stack if the sections was already visited.
- *MazeSection*.java
    This enum contains the 5 types of sections allowed
    - EMPTY_SPACE: Represents a path that can be followed.
    - WALL: Represents a wall that can't be overpassed.
    - START: Represents the beginning position of the maze.
    - FINISH: Represents the end position of the maze.
    - SOLUTION: Represents the correct path that solves the maze.

## Controller folder
Inside of this folder we can find the controller, and inside of it there are the endpoints needed for the app.
- *MazeSolverController*.java
	This class is meant to manage the end-points of the project, there we can find 2 attributes:
	- *mazeSolverService (MazeSolverService)* The instance of the service in the controller for call their functions.
	- *maze (MazeSection[][]):* The main maze structure that is handled in the controller and filled with the returns of the service
    
	
We are going to give a table with the endpoints, the returns, and a short explanation about the functionality of each one.

| Endpoint         | Return| Functionality|
|----------------|---------------|-------------------------------------------|
| (GetMapping) index() "/"     | ModelAndView  | The main endpoint of the app, everything runs and redirects to this place, the root element of the server.    |
| (PostMapping) loadMaze(@RequestParam("file") MultipartFile fileUploaded) "/load"     | String  | This method let user to load the maze from his own TXT file through request param, the string returned is for redirect the user to the root endpoint.   |
| (GetMapping) solveMaze() "/solve"     | String  | This method calls the solve method of the service and retrieves the information to the view with the updated maze and the path that solves it, the string returned is for redirect the user to the root endpoint.|

## Service folder
Inside of this folder we can find the service, that contains the main functions of the maze solver
- *MazeSolverService*.java
	This class is meant to create and execute all the logic of the project:
	- *maze (MazeSection[][]):* Keeps the sections of the maze to be showed correctly and have a matrix structure.
	- *stackMaze (Stack<MazeSection\>):* The main data structure that executes all the logic, maze sections are added to this Stack.
	- *fileLoaderUtils (FileLoaderUtils):* An instance of the utils file for allow call the functions of it.
    
We are going to give a table with the methods, the returns, and a short explanation about the functionality of each one.

| Method         | Return| Functionality|
|----------------|---------------|-------------------------------------------|
| loadMaze(MultipartFile fileUploaded)     | MazeSection[][]  | This method handles the file loaded through the view, used the utility and fills all the maze spaces with the specified mazeSection|
| convertToSection(char character, int row, int col)    | MazeSection  | This method converts each character that the TXT file have into MazeSection|
| findMazeStart()| MazeSection  | This method iterates over the matrix and search the MazeSection that its SectionType value is START, returning it|
| validSurroundings(MazeSection section)     | MazeSection[]  |This methods iterates over the matrix in the specified MazeSection, validates if the 4 main sorroudings of it are an empty section (SectionType), returns an array of the coincidences|
| showPathSolved(Stack<MazeSection\> solution)     | void  |This method takes the Stack, iterates over it and takes (with pop()) the value of row and column of the MazeSection, search it in the matrix and overwrites it with the one of the Stack, overwritting the maze|
| solveMaze()     | MazeSection[][]  |The main method of the service, it calls the 4 methods mentioned before, adds the first element, that is the beginning of the maze, iterates through the stack an validates, keeps the correct path to solve it|

## Utils folder
This folder keeps the file that contains the utils used in the project
- *FileLoaderUtils*.java
	This class handles all the logic for read and interpret TXT files for being loaded in the solver, serves the following methods:

| Method         | Return| Functionality|
|----------------|---------------|-------------------------------------------|
| processFile(MultipartFile file)     | List<String\>  | This method takes the file passed by the user, read it and returns a list of strings with the information of the file. Is used in the loadMaze method of the service.|

---

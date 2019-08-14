# What is this?

This is a test I've been asked to do for a job interview.
The subject was :

    Consider an infinite grid of white and black squares. The grid is initially all white and there is a machine in one cell facing right. It will move based on the following rules:
     - If the machine is in a white square, turn 90° clockwise and move forward 1 unit;
     - If the machine is in a black square, turn 90° counter-clockwise and move forward 1 unit;
     - At every move, flip the color of the base square.
    Implement an application that will receive HTTP PUT requests with a number of steps the simulation should run, always starting from the same conditions, and output the resulting grid to a file. Please provide support documentation.

These simple rules are the rules used for the Langton's ant.

# How to use it

`com.Main` is the entry point. On a command line, we should run the following command on the folder containing the compiled project (the parent folder of the "com" folder).
```shell
java com.Main
```

Then a server starts and listen to the port 8080. To launch a run, you have to request it with a PUT method and specifying the number of iterations via the parameter "n".
Personally, I used the software Postman for that. 
A request for 500 iterations could be:

    http://127.0.0.1:8080?n=500

The server should answer you a "Success" (with status 200) if everything is ok or "Fail" (with status 500) if something was wrong.

Then you can check the result on your user folder in the file `langton_ant_grid_result.txt`.

# My choices 

## The language

The language used is Java. I never tried it really, but the company uses it so this is the reason why it has been made in Java.

## My approach

One requirement was to use an OOP language. So I guessed, my ability to use OOP + Design Patterns is tested here. To be honest, I pushed the architecture farther than I would have done if I was just trying to create a simple Langton's ant algorithm for fun.
So I tried to have the SOLID principles in mind while doing this. Also one of my personal preferences when coding is "less is best". It could be seen as the KISS principle, but I mean by that to reduce the code to have less to read (it's a personal opinion of course, but for me "less to read" means "easier to understand").

## Technical description of the project

### Main (com.Main)

This is of course the entry point of the program. Basically, it initialised everything we need to run and then start the server.

### The server (com.langtonsant.server.LangtonServer)

This is a wrapper to handle the HttpServer asked in the test. I put everything related to the HTTP protocol there (HttpServer, HttpExchange etc..). It also checks the inputs to be sure the request is valid. To be valid it has to use the PUT HTTP verb and have a "n" parameter (which is a strictly positive integer).  
I trigger an event when a valid request is received. 
I use this event to separate the concerns about the server from the application / algorithm itself. In my opinion the server doesn't have to know something about how the application should run.

### The application (com.langtonsant.application.Application)

This is where the main algorithm of the subject is run. For that it needs a grid, a machine (or an ant) and a file path for the output. Those are initialised in `com.Main.main` before the server starts. 
I used a string for the output file path. It was for the sake of the simplicity and not to be late, but I guess we can improve this part with an output stream instead.

### The cell (com.langtonsant.application.element.cell.(White|Black)Cell)

The cells are used as a chain. The idea is to link them to create a closed chain.
There are 2 cells only and we could have toggled them with a simple if statement.
But I imagined that we could create some other patterns later instead of just having "white -> black" pattern.
They implement ICell for 3 reasons : 
 - first, they hold the information on how to rotate the machine (ant) via an overridden method
 - second, it allows us to create other types of cells to try new behaviour like turning 180 degrees
 - third, the grid that holds the cells data use an abstraction of the cells.

### The grid (com.langtonsant.application.element.grid.Grid)

It holds the infinite grid data. I chose to use a 1 dimensional array of ICell. I could have used a 2D array, but I'm always concerned about performance. It's better to have contiguous memory allocated to optimise the CPU cache access, although I didn't check how Java works with memory / CPU.

One detail of the test was the infinity of the grid. It is not possible in the strict sense but I tried to manage that when the machine get "outside" the grid by allocating more memory basically.

### The machine / ant (com.langtonsant.application.element.ant.Machine)

It is a simple class that holds a position vector and a heading vector. We can move it or replace it at the center of the grid.
To be honest, I'm not convinced by the `resetOnGrid` method. It works, but I think the computation to center the machine shouldn't be done there.

### The machine factory (com.langtonsant.factories.MachineFactory)

Well, it's very simple and feels kind of useless for the moment. I did that because I thought we could have fun by using another machine with different behaviours later.
I would pass a machine type as argument to select the one we want.

### The grid writer (com.langtonsant.persitancy.GridTextWriter)

It manages the output into the file. The format wasn't mentioned in the test. So I chose a text version which is in my opinion the simplest approach.
Later I would output an image because the resulting grid with its colors is more graphical than textual. This is also the reason why it implements an interface. It looses the coupling and we can be more flexible later. However, the way it has been done will ask some refactoring. If I would refactor that, my first try would be to use a factory that gives us the right writer depending on the file extension, mimetype or output stream.

FYI: it outputs a "." for a white cell and a "+" for a black one.

### The 2D Vector (com.langtonsant.math.Vector2)

I made it to handles coordinates. A real vector would use float instead of int, but here I needed a discrete approach.
Also, this class has some methods to facilitate the creation of some vector (like the one pointing in the direction "right") and to have a fluent read for adding vectors.
I could put more method for the creation of specific vectors (top, left, bottom) and add more operations on them (subtraction, dot product etc, but I don't need that so it has not been made.

### The cell loop builder (com.langtonsant.application.builder.CellLoopBuilder)

I created this builder to answer one specific problem: the creation of the closed chain. It is used by adding cell in the order we want and then build the chain itself. I found this approach handy to solve the problem of the circular relation between cells.
I realized later that I could easily make a way more abstracted builder. Indeed, there's no need to chain "cells", we could use a dedicated interface for elements that are meant to be chained.  

### The cell initialisation strategy (com.langtonsant.application.builder.grid.DefaultCellInitialisationStrategy)

I use a Strategy Design Pattern to be able to set a way to create the cell array. It is meant to manage the complexity of the memory allocation of the grid and how the grid cells are first initialised. I used the Strategy DP to be able later to have different initial conditions for the grid. We can for example have a grid that is initialized with random cells instead of white.

# Some personal thoughts to go farther

I refrain myself to get farther, I could, but imo I reached an acceptable state to discuss about it in an interview (at least I hope ^^). Here is some idea I have right now on what can be improved :
-  CellLoopBuilder could be more generic to handle chainable objects instead of only cells
- Use an output stream instead of a string to output the file
- Change the way the machine is placed back in the middle of the grid
- check the Java coding standard and change it accordingly
- user command line arguments to have controls on some configurations (server port, output file etc...) 

For more fun : 
- Add more GridWriter to have more possibilities like having an image
- We could use the server to download the resulting file via a browser
- have a simple UI in a browser to run some tests instead of using a system like Postman or else
- Have some ants that have different behaviours
- Have more than one ant in the grid
- Have an "animated" output
- Have a better output message from the server
- Guide more the users when a request is not done properly

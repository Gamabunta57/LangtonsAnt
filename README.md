# What is this ?

This is a test I've been asked to do for a job interview.
The subject was :

    Consider an infinite grid of white and black squares. The grid is initially all white and there is a machine in one cell facing right. It will move based on the following rules:
     - If the machine is in a white square, turn 90° clockwise and move forward 1 unit;
     - If the machine is in a black square, turn 90° counter-clockwise and move forward 1 unit;
     - At every move flip the color of the base square.
    Implement an application that will receive HTTP PUT requests with a number of steps the simulation should run, always starting from the same conditions, and output the resulting grid to a file. Please provide support documentation.

These simple rules are the rules used for the Langton's ant.

# My choices 

## The language

The langugage use is Java. I never tried it really, but the company use it so this is the reason why it has been made in Java.

## My approach

One requirements was to use a OOP language. So I guessed, my ability to use OOP + Design Patterns is tested here. To be honest, I pushed the architecture farther than I would have done if I was just trying to create a simple Langton's ant algorithm for fun.
So I tried to have the SOLID principles in mind while doing this. Also one of my personnal preferences when coding is "less is best". It could be seen as the KISS principle but I mean by that to reduce the code to have less to read (it'a personal opinion of course but for me "less to read" means "easier to understand").

## Technical description of the project

### Main (com.Main)

This is of course the entry point of the program. Basically, it initialised everything we need to run and then start the server.

### The server (com.langtonsant.server.LangtonServer)

This is a wrapper to handle the HttpServer asked in the test. I put everything related to the HTTP protcol there (HttpServer, HttpExchange etc..). It also check the inputs to be sure the request is valid. To be valid a request has to use the PUT HTTP verb and have a "n" parameter (which is a strict positive integer).  
I trigger an event when a valid request is received. 
I use this event to separate the concerns about the server from the application / algorithme itself. In my opinion the server doesn't have to know something about how the application should run.

### The application (com.langtonsant.application.Application)

This is where the main algorithm of the subject is run. For that it needs a grid, a machine (or an ant) and a file path for the output. Those are initialised in com.Main.main before the server starts. 
I used a string for the output filepath. It was for the sake of the simplicity and not to be late, but I guess we can improve this part with an output stream instead.

### The cell (com.langtonsant.application.element.cell.(White|Black)Cell)

The cells are used as a chain. The idea is to link them to create a closed chain.
Here it is 2 cells only and we could have toggled them with a simple if statement.
But I imagined that we could create some other patterns later instead of just having "white -> black" pattern. 
They implement ICell for 3 reasons : 
 - first, they hold the information on how to rotate the machine (ant) via an overrided method
 - second, it allows us to create other types of cells to try new behaviour like turning 180 degrees
 - third, the grid that holds the cells data use an abstraction of the cells.

### The grid (com.langtonsant.application.element.grid.Grid)

It holds the infinite grid data. I chose to use a 1 dimensionnal array of Cell. I could have use a 2D array but I'm always concern about performance. It's better to have contigus memory allocated to optimise the CPU cache access although I didn't check how Java works with memory / CPU.

One detail of the test was the infinity of the grid. It is not possible in the strict sense but I tried to manage that when the machine get "outside" the grid by allocating more memory basically.

### The machine / ant (com.langtonsant.application.element.ant.Machine)

It is a simple class that holds a position vector and a heading vector. We can move it or replace it at the center of the grid.
To be honest, I'm not convinced by the "resetOnGrid" method. It works but I think the computation to center the machine shouldn't be done there.

### The machine factory (com.langtonsant.factories.MachineFactory)

Well, it's very simple and feels kind of useless for the moment. I made that because I thought we could have fun by using other machine with different behaviours later.
I would pass a machine type as argument to select the one we want.

### The cell factory (com.langtonsant.factories.CellFactory)

It's a simple factory where we passe a cell type and it gives us a cell from that type. The advantage I see here is that we can make a loose coupling with classes that use cells.

### The grid writer (com.langtonsant.persitancy.GridTextWriter)

It manages the way the outputing into the file. The output format wasn't mentionned in the test. So I chose a txt version which is in my opinion the simplest approach.
Later I would output an image because the resulting grid with its colors is more graphical than textual. This is also the reason why it implementes an interface. It looses the coupling and we can be more flexible later. However, the way it has been done will ask some refactoring. If I would refactor that, my first try would be to use a facotry that gives us the right writer depending on the file extension, mimetype or output stream.

FYI : it outputs a "." for a white cell and a "+" for a black one.

### The 2D Vector (com.langtonsant.math.Vector2)

I made it to handles coordinates. A real vector would use float instead of int but here I needed a discrete approach.
Also this class has some methods to facilitate the creation of some vector (like the one pointing at the direction "right") and to have a fluent read for adding vectors.
I could put more method for the creation of specific vectors (top, left, bottom) and add more operations on them (substraction, dot product etc but I don't need that so it has not been made.

### The cell loop builder (com.langtonsant.application.builder.CellLoopBuilder)

I created this builder to answer one specific problem: the creation of the closed chain. It is used by adding cell in the order we want and then build the chain itself. I found this approach handy to solve the problem of the circular relation between cells.
I realized later that I could easily make a way more abstracted builder. Indeed, there's no need to chain "cells", we could use a dedicated interface for elements that are meant to be chained.  

### The cell initialisation strategy (com.langtonsant.application.builder.grid.DefaultCellInitialisationStrategy)

I use a Strategy Design Pattern to be able to set a way to create the cell array. It is meant to manage the complexity of the memory allocation of the grid and how the grid cells are first initialised. I used the Strategy DP to be able later to have different intial conditions for the grid. We can for exemple have a grid that is initialized with random cells instead of white.
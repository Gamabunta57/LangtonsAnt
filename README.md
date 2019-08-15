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

`com.Main` is the entry point. 
On a command line, we should run the following command on the folder containing the compiled project (the parent folder of the "com" folder).
```shell
java com.Main
```

Then a server starts and listen to the port 8080. 
To launch a run, you have to request it with a PUT method and specifying the number of iterations via the parameter "n".
Personally, I used the software Postman for that. 
A request for 500 iterations could be:

    http://127.0.0.1:8080?n=500

The server should answer you a "Success" (with status 200) if everything is ok or "Fail" (with status 500) if something was wrong.

Then you can check the result on your user folder in the file `langton_ant_grid_result.(txt|png)`.

# My choices 

## The language

The language used is Java. 
I never tried it really, but the company uses it so this is the reason why it has been made in Java.

## My approach

One requirement was to use an OOP language. 
So I guessed, my ability to use OOP + Design Patterns is tested here. 
To be honest, I pushed the architecture farther than I would have done if I was just trying to create a simple Langton's ant algorithm for fun.
So I tried to have the SOLID principles in mind while doing this. 
Also one of my personal preferences when coding is "less is best". 
It could be seen as the KISS principle, but I mean by that to reduce the code to have less to read (it's a personal opinion of course, but for me "less to read" means "easier to understand").

## Technical description of the project

### Main (com.Main)

This is of course the entry point of the program. 
Basically, it initialised everything we need to run and then start the server.

### The server (com.langtonsant.server.LangtonServer)

This is a wrapper to handle the HttpServer asked in the test. 
I put everything related to the HTTP protocol there (HttpServer, HttpExchange etc..). 
It also checks the inputs to be sure the request is valid. 
To be valid it has to use the PUT HTTP verb and have a "n" parameter (which is a strictly positive integer).  
I trigger an event when a valid request is received. 
I use this event to separate the concerns about the server from the application / algorithm itself. 
In my opinion the server doesn't have to know something about how the application should run.

### The application (com.langtonsant.application.Application)

This is where the main algorithm of the subject is run. 
For that it needs a grid, a machine (or an ant) and a file path for the output. 
Those are initialised in `com.Main.main` before the server starts. 
I used a string for the output file path. 
It was for the sake of the simplicity and not to be late, but I guess we can improve this part with an output stream instead.

### The grid (com.langtonsant.application.element.grid.Grid)

It holds the infinite grid data. 
During the interview, they make me realized that the grid with the array system wasn't infinite.
Indeed, the array needs to be accessed with an index which is an integer (not unsigned as Java doesn't handle that). 
So the maximum index we could reached is 2^31.
They propose a solution which consists in using a HashSet instead of an array. 
I was curious and wanted to try, so I did.
But, there's another problem. 
The Grid is now infinite which is fine but the coordinates used by the machine is limited to that integer (I need to check BigInteger, may be it is a good solution for that).

### The machine / ant (com.langtonsant.application.element.ant.Machine)

It is a simple class that holds a position vector and a heading vector. 
We can move it or reset it at (0,0).

### The machine factory (com.langtonsant.factories.MachineFactory)

Well, it's very simple and feels kind of useless for the moment. 
I did that because I thought we could have fun by using another machine with different behaviours later.
I would pass a machine type as argument to select the one we want.

### The grid writer (com.langtonsant.persitancy.GridTextWriter)

It manages the output into the file. 
The format wasn't mentioned in the test. 
So I chose a text version which is in my opinion the simplest approach.
It looses the coupling and we can be more flexible later.

FYI: it outputs a "." on empty cell and a "+" otherwise.

### The grid writer (com.langtonsant.persitancy.GridPNGWriter)

It manages the output into a PNG file.

FYI: it uses a white background and black squares where there's cell.

### The 2D Vector (com.langtonsant.math.Vector2)

I made it to handles coordinates. 
A real vector would use float instead of int, but here I needed a discrete approach.
Also, this class has some methods to facilitate the creation of some vector (like the one pointing in the direction "right") and to have a fluent read for adding vectors.
I could put more method for the creation of specific vectors (top, left, bottom) and add more operations on them (subtraction, dot product etc, but I don't need that so it has not been made.

# Some personal thoughts on the approach

I like this approach with the HashSet. 
I removed the possibility to have many colors in the end and the result is way simpler (I really like that).
The advantage I found on it is the "real" infinity of the grid (we are only limited by the memory), also it holds only the strictly minimum information needed (the "black" cells).
It may be just a bit slower (although I'm not sure) because each time a cell is added in the Hashset there's memory allocation to be done and a hash to compute.
/* Programming Assignment 1 
 * Authors : Pinardy Yang (1001520), Loh Wei Quan (1001505)
 * Date: 07/03/2017 */

-=-=-Purpose of program -=-=-

The purpose of this program is to construct a directed acyclic graph (DAG) as
the system to keep track of the flow of the programs. The user will then traverse 
through this DAG in order to run the programs.



-=-=- How to compile program -=-=-

1) Download the code and put it in your favourite folder

2) In ProcessManagement.java, change the file path for currentDirectory to 
where you placed the code in. For example, the file path name could be 
"C:\Pinardy\SUTD\Term_5\50.005 - Computer Systems Engineering\ProgAssignment1\src"

3) In ProcessManagement.java, change the file path for instructionSet to whichever file
that you want to use for the test cases. 
These test cases include:
-> graph-file.txt
-> graph-file1.txt

4) Click run under ProcessManagement.java to start the program.



-=-=-What exactly does the program do -=-=-

1) The user parses in a file to generate a ProcessGraph

2) Information of the ProcessGraph is displayed to the user

3) Each line in the input file represents a node. The line is of format:
<program name with arguments :list of children ID's : input file : output file>

3) We run each line by each line, which means we check each node.
We check if the respective children of the node that is supposed to run 
have finished running. 

4) If the children have finished running, that node is allowed to run.


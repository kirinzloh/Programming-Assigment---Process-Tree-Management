import java.io.File;
import java.util.ArrayList;

public class ProcessManagement {

    //set the working directory
    private static File currentDirectory = new File("/Users/weiqu/workspace/Software Construction/Programming Assigment");
    //set the instructions file
    private static File instructionSet = new File("graph-file.txt");
    public static Object lock=new Object();
    private static int count;
    private static boolean allExecuted;
    private static ArrayList<ProcessGraphNode> children;
    
    public static void main(String[] args) throws InterruptedException {
        //parse the instruction file and construct a data structure, stored inside ProcessGraph class
        ParseFile.generateGraph(new File(currentDirectory + "/" + instructionSet));

        // Print the graph information
        // WRITE YOUR CODE
        ProcessGraph.printGraph();

        // Using index of ProcessGraph, loop through each ProcessGraphNode, to check whether it is ready to run
        // check if all the nodes are executed
        // WRITE YOUR CODE
        try {
        	ProcessBuilder pb = new ProcessBuilder();
        	count = 0;
        	allExecuted = false;
        	while (allExecuted == false){
	        	for (ProcessGraphNode node : ProcessGraph.nodes) {
	        		if (node.isExecuted()){
	        			count++;
	        		}
	        		else{
	        			children = node.getChildren();
	        			int i = 0;
	        			for (ProcessGraphNode child : children){
	        				if (child.isExecuted()){
	        					i++;
	        				}
	        			}
	        			if (i == children.size()){
	        				node.setRunnable();
	        				pb.redirectInput(node.getInputFile());
	        				pb.redirectOutput(node.getOutputFile());
	        				pb.command(node.getCommand());
	        				pb.start();
	        				node.setExecuted();
	        				count++;
	        			}
	        		}
	        	}
	        	if (count == ProcessGraph.nodes.size()){
	        		allExecuted = true;
	        	}
        	}
        }
        catch (Exception e){
        	e.printStackTrace();
        }

        System.out.println("All process finished successfully");
    }

}
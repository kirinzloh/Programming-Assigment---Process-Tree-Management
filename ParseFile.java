import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ParseFile {
    //this method generates a ProcessGraph and store in ProcessGraph Class
    public static void generateGraph(File inputFile) {
        try{
        	BufferedReader br = new BufferedReader(new FileReader(inputFile));
            int index=0;
            String line = br.readLine();
            while(line != null){
                String[] quatiles= line.split(":");
                if (quatiles.length!=4) {
                    System.out.println("Wrong input format!");
                    throw new Exception();
                }
                //add this node
                ProcessGraph.addNode(index);
                //handle Children
                if (!quatiles[1].equals("none")){
                    String[] childrenStringArray=quatiles[1].split(" ");
                    int[] childrenId=new int[childrenStringArray.length];
                    for (int child : childrenId){
                    	for (int i = 0; i < child; i++) {
                        	if (ProcessGraph.nodes.get(i) == null){
                        		ProcessGraph.addNode(i);
                        	}
                        }
                    }
                    for (int i = 0; i < childrenId.length; i++) {
                        ProcessGraph.nodes.get(index).addChild(ProcessGraph.nodes.get(childrenId[i]));
                    }
                }
                //setup command
                ProcessGraph.nodes.get(index).setCommand(quatiles[0]);
                //setup input
                ProcessGraph.nodes.get(index).setInputFile(new File(quatiles[2]));
                //setup output
                ProcessGraph.nodes.get(index).setOutputFile(new File(quatiles[3]));
                //setup parent
                for (ProcessGraphNode node : ProcessGraph.nodes) {
                    for (ProcessGraphNode childNode : node.getChildren()) {
                        ProcessGraph.nodes.get(childNode.getNodeId()).addParent(ProcessGraph.nodes.get(node.getNodeId()));
                    }
                }
                //mark initial runnable
                for (ProcessGraphNode node:ProcessGraph.nodes) {
                    if (node.getParents().isEmpty()){
                        node.setRunnable();
                    }
                }

                line = br.readLine();
                index++;
            }
        } catch (Exception e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }


}
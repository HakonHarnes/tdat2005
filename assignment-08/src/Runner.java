import java.net.URL;

public class Runner {
    public static void main(String[] args){
        URL[] URL = getURL();
        Graph graph = new Graph();

        //Iterates through each graph
        for(int i = 0; i < URL.length; i++){
            System.out.println("--- GRAPH " + (i + 1) + " ---");
            System.out.println("INCREASE - PATH");
            graph.initializeGraph(URL[i]);

            Node source = graph.nodes[0];
            Node sink   = graph.nodes[1];

            int maxFlow = graph.runEdmundKarp(source, sink);
            System.out.println("MAXIMUM FLOW IS " + maxFlow + "\n");
        }
    }

    //Returns an array of URLs
    private static URL[] getURL(){
        URL[] URL = null;

        try {
            URL = new URL[]{
                    new URL("http://www.iie.ntnu.no/fag/_alg/v-graf/flytgraf1"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/v-graf/flytgraf2"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/v-graf/flytgraf3")
            };
        } catch (Exception e){
            e.printStackTrace();
        }

        return URL;
    }
}
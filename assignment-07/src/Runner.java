import java.net.URL;

public class Runner {
    private final static int KALVSKINNET = 37774;
    private final static int MOHOLT = 18058;

    private final static int DRAMMEN = 65205;
    private final static int HELSINKI = 3378527;

    public static void main(String[] args){
        URL[] URL = getURL();
        Graph graph = new Graph();

        System.out.println("---  L7G1  ---");
        graph.initializeGraph(URL[0]);
        graph.BFS(graph.nodes[5]);
        graph.printBFS();

        System.out.println("\n---  L7G2  ---");
        graph.initializeGraph(URL[1]);
        graph.BFS(graph.nodes[8]);
        graph.printBFS();

        System.out.println("\n---  L7G3  ---");
        graph.initializeGraph(URL[2]);
        graph.BFS(graph.nodes[8]);
        graph.printBFS();

        System.out.println("\n---  L7G5  ---");
        graph.initializeGraph(URL[3]);
        Node node = graph.sort();
        graph.printSorted(node);

        System.out.println("\n\n-----  L7SK  -----");
        graph.initializeGraph(URL[4]);
        int intersections = graph.getIntersections(graph.nodes[KALVSKINNET], graph.nodes[MOHOLT]);
        System.out.println("INTERSECTIONS: " + intersections);
    }

    private static URL[] getURL(){
        URL[] URL = null;

        try {
            URL = new URL[]{
                    new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g1"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g2"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g3"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7g5"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7Skandinavia"),
                    new URL("http://www.iie.ntnu.no/fag/_alg/uv-graf/L7Skandinavia-navn")
            };
        } catch (Exception e){
            e.printStackTrace();
        }

        return URL;
    }
}
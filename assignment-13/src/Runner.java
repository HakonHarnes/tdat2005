public class Runner {
    private final static int SCANDINAVIA = 0;
    private final static int ICELAND     = 1;

    private final static int NODES = 0;
    private final static int EDGES = 1;

    private final static String[][] PATH = {
            { "files/scandinavia/noder.txt", "files/scandinavia/kanter.txt" },
            { "files/iceland/noder.txt",     "files/iceland/kanter.txt"     }
    };

    public static void main(String[] args){

        //Initializes the graph
        Graph graph = new Graph();
        graph.initializeGraph(PATH[SCANDINAVIA][NODES], PATH[SCANDINAVIA][EDGES]);

        //Source and sink nodes
        Node source = graph.nodes[2617841];
        Node sink   = graph.nodes[1491126];

        long startTime = System.currentTimeMillis();
        int processedNodes = graph.aStar(source, sink);
        long endTime = System.currentTimeMillis();

        int nodeCount = graph.getNodeCount(source, sink);
        String time = graph.calculateTime(sink);

        graph.writeCoordinates(source, sink,"coordinates/ASTAR.csv");

        System.out.println("\n----------------------------       STATS       ---------------------------");
        System.out.format("%-10s %-5s %-7s %-7s %-11s %-13s %-7s\n", "ALGORITHM", "TIME", "SOURCE", "SINK", "NODE_COUNT", "DRIVING_TIME", "PROCESSED_NODES");
        System.out.format("%-10s %-5d %-7d %-7d %-11d %-13s %-7d\n", "A*", endTime - startTime, source.number, sink.number, nodeCount, time, processedNodes);
        graph.writeCoordinates(source, sink, "coordinates/DIJKSTRA.csv");

        startTime = System.currentTimeMillis();
        processedNodes = graph.dijkstra(source, sink);
        endTime = System.currentTimeMillis();

        System.out.format("%-10s %-5d %-7d %-7d %-11d %-13s %-7d\n", "DIJKSTRA", endTime - startTime, source.number, sink.number, nodeCount, time, processedNodes);
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

class Graph {
    private int N, K;
    Node[] nodes;

    //Initializes the graph from a node file and edge file
    void initializeGraph(String nodeFile, String edgeFile) {
        try (BufferedReader nodeReader = new BufferedReader(new FileReader(nodeFile));
             BufferedReader edgeReader = new BufferedReader(new FileReader(edgeFile))) {

            //Reads the amount of nodes
            System.out.print("READING NODER.TXT.... ");
            StringTokenizer tokenizer = new StringTokenizer(nodeReader.readLine());
            N = Integer.parseInt(tokenizer.nextToken());

            //Initializes the node array
            nodes = new Node[N];
            for(int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(nodeReader.readLine());

                int number = Integer.parseInt(tokenizer.nextToken());
                double latitude  = Double.parseDouble(tokenizer.nextToken());
                double longitude = Double.parseDouble(tokenizer.nextToken());

                nodes[i] = new Node(number, latitude, longitude);
            }

            System.out.print("DONE\n");

            //Reads the amount of edges
            System.out.print("READING KANTER.TXT... ");
            tokenizer = new StringTokenizer(edgeReader.readLine());
            K = Integer.parseInt(tokenizer.nextToken());

            //Initializes the edges
            for(int i = 0; i < K; i++){
                tokenizer = new StringTokenizer(edgeReader.readLine());

                int from = Integer.parseInt(tokenizer.nextToken());
                int to   = Integer.parseInt(tokenizer.nextToken());
                int time = Integer.parseInt(tokenizer.nextToken());

                Edge edge = new Edge(nodes[from].edge, nodes[to], time);
                nodes[from].edge = edge;
            }

            System.out.print("DONE\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int dijkstra(Node source, Node sink){
        int processedNodes = 0;
        initializePrevious(source);

        //Initializes the queue
        PriorityQueue<Node> queue = new PriorityQueue<>(N, Comparator.comparingInt(node -> node.previous.distance));
        queue.add(source);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            node.previous.visited = true;
            processedNodes++;

            for(Edge edge = node.edge; edge != null; edge = edge.next)
                shortenEstimate(node, edge);

            Edge edge = node.edge;
            while(edge != null){
                if(!edge.to.previous.visited) {
                    queue.add(edge.to);
                    edge.to.previous.visited = true;
                }

                edge = edge.next;
            }

            if(node == sink) return processedNodes;
        }

        return -1;
    }

    int aStar(Node source, Node sink){
        int processedNodes = 0;
        initializePrevious(source);

        //Initializes the queue
        PriorityQueue<Node> queue = new PriorityQueue<>(N, Comparator.comparingInt(node -> node.previous.estimatedDistance));
        queue.add(source);

        while(!queue.isEmpty()){
            Node node = queue.poll();
            node.previous.visited = true;
            processedNodes++;

            for(Edge edge = node.edge; edge != null; edge = edge.next)
                shortenEstimate(node, sink, edge);

            Edge edge = node.edge;
            while(edge != null){
                if(!edge.to.previous.visited) {
                    queue.add(edge.to);
                    edge.to.previous.visited = true;
                }

                edge = edge.next;
            }

            if(node == sink) return processedNodes;
        }

        return -1;
    }

    //Shortens the estimated value (used by dijkstra)
    private void shortenEstimate(Node node, Edge edge){
        Previous nd = node.previous;
        Previous md = edge.to.previous;

        if(md.distance > nd.distance + edge.time){
            md.distance = nd.distance + edge.time;
            md.previous = node;
        }
    }

    //Shortens the estimated value (used by A*)
    private void shortenEstimate(Node node, Node sink, Edge edge){
        Previous nd = node.previous;
        Previous md = edge.to.previous;

        if(md.distance > nd.distance + edge.time){
            md.distance = nd.distance + edge.time;
            md.estimatedDistance = md.distance + estimateDistance(edge.to, sink);
            md.previous = node;
        }
    }

    private int estimateDistance(Node n1, Node n2){
        double sin_lat = Math.sin((Math.toRadians(n1.latitude) - Math.toRadians(n2.latitude)) / 2.0);
        double sin_long = Math.sin((Math.toRadians(n1.longitude) - Math.toRadians(n2.longitude)) / 2.0);
        double toHundredthsConstant = 2.0*6371.0/130.0*3600.0*100.0;
        return (int) (toHundredthsConstant * Math.asin(Math.sqrt(sin_lat*sin_lat+n1.cosWidth*n2.cosWidth*sin_long*sin_long)));
    }

    //Initializes previous
    private void initializePrevious(Node start) {
        for (Node node : nodes)
            node.previous = new Previous();

        start.previous.distance = 0;
    }

    //Returns the amount of nodes in the road
    int getNodeCount(Node source, Node sink){
        int nodeCount = 1;

        Node node = sink;
        while(node != source){
            nodeCount++;
            node = node.previous.previous;
        }

        return nodeCount;
    }

    //Calculates the driving time
    String calculateTime(Node sink){
        int milliseconds = sink.previous.distance * 10;

        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) % TimeUnit.MINUTES.toSeconds(1));
    }

    void writeCoordinates(Node source, Node sink, String path){
        ArrayList<Double> coordinates = new ArrayList<>();

        Node node = sink;
        while(node != source){
            coordinates.add(node.latitude);
            coordinates.add(node.longitude);
            node = node.previous.previous;
        }

        Writer writer = new Writer(path);
        writer.write(coordinates);
    }
}
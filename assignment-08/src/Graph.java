import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class Graph {
    int N, K;
    Node[] nodes;
    Edge[][] edges;

    //Initializes the graph from URL
    public void initializeGraph(URL url) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());

            //Initializes the edges
            edges = new Edge[N][N];
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    edges[i][j] = new Edge();

            //Initializes and sets values of the nodes
            nodes = new Node[N];
                for (int i = 0; i < N; i++) {
                    nodes[i] = new Node();
                    nodes[i].index = i;
            }

            //Sets the values of the edges
            for (int i = 0; i < K; i++) {
                tokenizer = new StringTokenizer(reader.readLine());

                int fromNode = Integer.parseInt(tokenizer.nextToken());
                int toNode   = Integer.parseInt(tokenizer.nextToken());
                int capacity = Integer.parseInt(tokenizer.nextToken());

                edges[fromNode][toNode].capacity = capacity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Initializes previous
    public void initializePrevious(Node start) {
        for (Node node : nodes)
            node.previous = new Previous();

        start.previous.distance = 0;
    }

    /*
        Runs a BFS from source -> sink
        The rest capacity of each node has to be > 0
        Return true if the BFS reaches the sink node,
        false otherwise
     */
    public boolean BFS(Node source, Node sink) {
        initializePrevious(source);

        Queue queue = new Queue(N - 1);
        queue.add(source);

        while (!queue.empty()) {
            Node node = queue.next();

            //Loops through all the edges of the node
            for(int i = 0; i < N; i++){
                Edge edge = edges[node.index][i];

                //Checks if the edge has sufficient rest capacity
                if(edge.getRestCapacity() < 1)
                    continue;

                //Sets previous
                Previous previous = nodes[i].previous;

                /*
                    The node hasn't been reached yet
                    - Sets the distance of the node
                    - Initializes previous
                    - Adds the node to the queue
                 */
                if (previous.distance == Previous.INFINITY) {
                    previous.distance = node.previous.distance + 1;
                    previous.previous = node;
                    queue.add(nodes[i]);
                }
            }

            //BFS has reached the sink
            if(node == sink) return true;
        }

        //BFS has not reached the sink
        return false;
    }

    //Finds the maximum flow in the graph from source -> sink
    public int runEdmundKarp(Node source, Node sink){
        int maxFlow = 0;

        //Runs BFS while the sink node has been reached
        while(BFS(source, sink)){
            int increase = getIncrease(source, sink);
            String path = getPath(source, sink);

            System.out.println(increase + " - " + path);

            /*
                Iterates through each node (and thereby edge) in the path
                Increments the flow in the path accordingly
             */
            Node node = sink;
            while(node != source){
                edges[node.previous.previous.index][node.index].flow += increase; //edge
                edges[node.index][node.previous.previous.index].flow -= increase; //inverse edge
                node = node.previous.previous;
            }

            maxFlow += increase;
        }

        return maxFlow;
    }

    /*
        Finds the minimum rest capacity in the path
        i.e. the amount we can increase the flow by
     */
    private int getIncrease(Node source, Node sink){
        Node node = sink;
        int increase = edges[node.previous.previous.index][node.index].getRestCapacity();

        //Iterates through each node (and thereby edge) in the path
        while(node != source){
            int capacity = edges[node.previous.previous.index][node.index].getRestCapacity();

            if(capacity < increase)
                increase = capacity;

            node = node.previous.previous;
        }

        return increase;
    }

    /*
        Finds the path from the BFS
        e.g. 0 4 3 7
     */
    private String getPath(Node source, Node sink){
        Node node = sink;
        String path = "";

        while(node != source){
            path = node.index + " " + path;
            node = node.previous.previous;
        }

        return source.index + " " + path;
    }
}
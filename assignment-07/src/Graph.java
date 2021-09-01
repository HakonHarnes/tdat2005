import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class Graph {
    int N, K;
    Node[] nodes;

    public void initializeGraph(URL url){
        try (InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        BufferedReader reader = new BufferedReader(inputStreamReader)){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            N = Integer.parseInt(tokenizer.nextToken());
            K = Integer.parseInt(tokenizer.nextToken());

            nodes = new Node[N];
            for(int i = 0; i < N; i++) {
                nodes[i] = new Node();
                nodes[i].i = i;
            }

            for(int i = 0; i < K; i++){
                tokenizer = new StringTokenizer(reader.readLine());

                int from = Integer.parseInt(tokenizer.nextToken());
                int to   = Integer.parseInt(tokenizer.nextToken());

                Edge edge = new Edge(nodes[from].edge, nodes[to]);
                nodes[from].edge = edge;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initializePrevious(Node start){
        for(Node node : nodes)
            node.d = new Previous();

        ((Previous) start.d).distance = 0;
    }

    public void BFS(Node start){
        initializePrevious(start);

        Queue queue = new Queue(N - 1);
        queue.add(start);

        while(!queue.empty()){
            Node node = queue.next();

            for(Edge edge = node.edge; edge != null; edge = edge.next){
                Previous previous = ((Previous) edge.to.d);

                if(previous.distance == Previous.INFINITY){
                    previous.distance = ((Previous) node.d).distance + 1;
                    previous.previous = node;
                    queue.add(edge.to);
                }
            }
        }
    }

    public void printBFS() {
        System.out.println("NODE PREV DIST");
        for(Node node : nodes){
            int nodeval = node.i;
            int previous = ((Previous) node.d).previous != null ? ((Previous) node.d).previous.i : -1;
            int distance = ((Previous) node.d).distance;

            if(previous == -1 && distance == 1000000000)
                System.out.format("%2d %4s %5s\n", nodeval, " ", "INF");
            else if(previous == -1)
                System.out.format("%2d %4s %4d\n", nodeval, " ", distance);
            else
                System.out.format("%2d %4d %4d\n", nodeval, previous, distance);
        }
    }

    public int getIntersections(Node start, Node end){
        initializePrevious(start);

        Queue queue = new Queue(N - 1);
        queue.add(start);

        while(!queue.empty()){
            Node node = queue.next();

            for(Edge edge = node.edge; edge != null; edge = edge.next){
                if(edge.to.equals(end))
                    return ((Previous) node.d).distance + 1;

                Previous previous = (Previous) edge.to.d;

                if(previous.distance == Previous.INFINITY){
                    previous.distance = ((Previous) node.d).distance + 1;
                    previous.previous = node;
                    queue.add(edge.to);
                }
            }
        }

        return -1;
    }

    public Node DFS(Node node, Node head){
        TopologicalList list = (TopologicalList) node.d;

        if(list.found) return head;
        list.found = true;

        for(Edge edge = node.edge; edge != null; edge = edge.next)
            head = DFS(edge.to, head);

        list.next = head;
        return node;
    }

    public Node sort(){
        Node head = null;

        for(Node node : nodes)
            node.d = new TopologicalList();

        for(Node node : nodes)
            head = DFS(node, head);

        return head;
    }

    public void printSorted(Node node){
        TopologicalList list;

        while(node != null){
            System.out.print(node.i + " ");
            list = (TopologicalList) node.d;
            node = list.next;
        }
    }
}
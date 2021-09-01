class Edge {
    Edge next;
    Node to;
    int time;

    Edge(Edge next, Node to, int time) {
        this.next = next;
        this.to = to;
        this.time = time;
    }
}
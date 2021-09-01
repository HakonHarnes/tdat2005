public class Edge {
    int capacity = 0;
    int flow = 0;

    public int getRestCapacity(){
        return this.capacity - this.flow;
    }

    public String toString(){
        return this.flow + "";
    }
}
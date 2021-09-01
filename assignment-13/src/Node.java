class Node {
    Previous previous;
    Edge edge;

    int number;
    double latitude;
    double longitude;
    double cosWidth;

    Node(int number, double latitude, double longitude){
        this.number    = number;
        this.latitude  = latitude;
        this.longitude = longitude;
        this.cosWidth  = Math.cos(Math.toRadians(latitude));
    }

    public String toString(){
        return number + "";
    }
}
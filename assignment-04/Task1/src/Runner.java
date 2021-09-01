public class Runner {
    public static void solveJosephus(int people, int interval){
        LinkedList list = new LinkedList();

        for(int i = 1; i <= people; i++){
            list.add(new Node(i));
        }

        Iterator iterator = new Iterator(list);

        while(list.getLength() > 1){
            for(int i = 0; i < interval-1; i++){
                iterator.next();
            }

            Node node = iterator.getNode();
            iterator.next();
            list.remove(node);

            System.out.println(list);
        }
    }

    public static void main(String[] args){
        solveJosephus(5, 2);
    }
}

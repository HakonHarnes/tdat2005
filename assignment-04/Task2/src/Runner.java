public class Runner {
    public static void main(String[] args){
        String code = IO.readFile("../Task1/src/LinkedList.java");

        System.out.println("CODE IS " + (CodeChecker.checkCode(code) ? "OK" : "NOT OK"));
    }
}

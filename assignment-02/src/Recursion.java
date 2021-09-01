import java.util.Date;

public class Recursion {

    //Complexity n
    private static double algorithm1(double x, int n){
        if(n == 0) return 1;
        if(n < 0) return -1;

        return x * algorithm1(x, n-1); //n
    }

    //a = 1, b = 2, k = 0
    //T(n/2)
    //b^0 = a => (log n)
    private static double algorithm2(double x, int n){
        if(n == 0) return 1;
        if(n < 1) return -1;

        if(n % 2 == 0) return algorithm2(x*x, n/2); //even

        return x * algorithm2(x*x, (n-1)/2); //odd
    }

    private static double algorithm3(double x, int n){
        return Math.pow(x, n);
    }

    public static void main(String[] args){
        final int[] n  = {10, 100, 1000, 10000};
        final double x = 1.001;

        for(int i = 0; i < n.length; i++){
            int rounds = 0;

            Date stop;
            Date start = new Date();

            do {
                algorithm3(x, n[i]);
                stop = new Date();
                rounds++;
            } while (stop.getTime() - start.getTime() < 1000);

            double time = (double) (stop.getTime() - start.getTime()) / rounds;
            System.out.format("n = %-5d time: %8.6f\n", n[i], time);
        }
    }
}
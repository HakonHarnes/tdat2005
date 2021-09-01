import java.util.Date;
import java.util.Random;

public class Stock {
    private static final int MIN = -10;
    private static final int MAX =  10;

    private static int[] generateTestData(int n){
        int[] testData = new int[n];

        Random random = new Random();

        for(int i = 0; i < n; i++)
            testData[i] = random.nextInt(MAX - MIN + 1) + MIN;

        return testData;
    }

    private static String algorithm(int[] testData){
        int[] absValue  = new int[testData.length];

        int val = 0;

        //Calculates the absolute value of the stocks
        for(int i = 0; i < testData.length; i++){
            val += testData[i];
            absValue[i] = val;
        }

        int maxProfit = 0;
        int[] days = new int[2];

        //Finds the biggest difference between the values of the stocks
        for(int i = 0; i < absValue.length - 1; i++){
            for(int j = i+1; j < absValue.length; j++){
                int profit = absValue[j] - absValue[i];

                if(profit > maxProfit) {
                    maxProfit = profit;
                    days[0] = i+1;
                    days[1] = j+1;
                }
            }
        }

        return ("Buy day: " + days[0] + "\nSale day: " + days[1] + "\nProfit: " + maxProfit);
    }

    public static void main(String[] args){
        final int[] DATASIZE = {10, 100, 1000, 10000, 100000};

        for(int i = 0; i < DATASIZE.length; i++){
            int[] testData = generateTestData(DATASIZE[i]);
            
            Date start = new Date();
            Date stop;

            int rounds = 0;

            do {
                algorithm(testData);
                stop = new Date();
                rounds++;
            } while (stop.getTime() - start.getTime() < 1000);

            double time = (double) (stop.getTime() - start.getTime()) / rounds;
            System.out.format("n = %-6d time: %11.6f\n", DATASIZE[i], time);
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;

public class IO {
    public static String readFile(String url){
        String content = "";

        try (FileReader reader = new FileReader(url);
             BufferedReader bufferedReader = new BufferedReader(reader)){

            String line = bufferedReader.readLine();

            while(line != null){
                content += line + "\n";
                line = bufferedReader.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return content;
    }
}

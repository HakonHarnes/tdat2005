import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

class Writer {
    private String path;

    Writer(String path){
        this.path = path;
    }

    void write(ArrayList<Double> coordinates){
        try (PrintWriter writer = new PrintWriter(new File(this.path))){
            StringBuilder sb = new StringBuilder();

            sb.append("lat");
            sb.append(',');
            sb.append("lng");

            for(int i = 0; i < coordinates.size(); i+= 2){
                sb.append('\n');
                sb.append(coordinates.get(i));
                sb.append(',');
                sb.append(coordinates.get(i+1));
            }

            writer.write(sb.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
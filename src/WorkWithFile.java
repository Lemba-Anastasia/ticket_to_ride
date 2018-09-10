import ElementsOfGraph.Node;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by Lemba on 10.09.2018.
 */
public class WorkWithFile {
    public void loadNode(){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("citys_coordinate.txt"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array=line.split(",");
                Node node=new Node(array[0],parseInt(array[1]),parseInt(array[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadBond(){

    }
}

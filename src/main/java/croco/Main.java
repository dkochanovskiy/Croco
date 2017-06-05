package croco;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     *   Logger init
     */
    private static final Logger logger = Logger.getLogger(Parser.class);

    public static void main(String[] args) {

        String FILE_NAME = "./src/main/resources/input.json";
        String[] finalArray;
        String employeeId;
        int count;

        Parser parser = new Parser();
        Sender sender = new Sender();

        finalArray = parser.getArray(FILE_NAME);
        count = parser.countTrackForAdd(FILE_NAME);
        ArrayList<String> finalArrayList = new ArrayList<>(finalArray.length);
        finalArrayList.addAll(Arrays.asList(finalArray));

        while(finalArrayList != null){
//            sender.sender(finalArrayList.get(0), finalArrayList.get(1), finalArrayList.get(2), finalArrayList.get(3),
//                    finalArrayList.get(4));
            for(int i = 0; i < 5; i++){
                finalArrayList.remove(0);
            }
        }
    }
}

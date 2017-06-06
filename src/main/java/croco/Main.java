package croco;

import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    /**
     *   Logger init
     */
    private static final Logger logger = Logger.getLogger(Parser.class);

    public static void main(String[] args) {

        /**
         * Number of fields required to add a track
         */
        int NUMBER_NEED_FIELDS = 5;

        String FILE_NAME = "./src/main/resources/input.json";
        String[] finalArray;
        int count;

        Parser parser = new Parser();
        Sender sender = new Sender();

        finalArray = parser.getArray(FILE_NAME);
        count = parser.countTrackForAdd(FILE_NAME);
        ArrayList<String> finalArrayList = new ArrayList<>(finalArray.length);
        try{
            finalArrayList.addAll(Arrays.asList(finalArray));

            for (int i = 0; i < count; i++){
                sender.sender(finalArrayList.get(0), finalArrayList.get(1), finalArrayList.get(2), finalArrayList.get(3),
                        finalArrayList.get(4));
                for (int j = 0; j < NUMBER_NEED_FIELDS; j++){
                    finalArrayList.remove(0);
                }
            }
        } catch(NullPointerException ex) {
            logger.trace("NullPointerException! " + ex);
        } catch(ArrayIndexOutOfBoundsException ex) {
            logger.trace("ArrayIndexOutOfBoundsException! " + ex);
        }
    }
}

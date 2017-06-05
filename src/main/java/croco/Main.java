package croco;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Main {

    /**
     *   Logger init
     */
    private static final Logger logger = Logger.getLogger(Parser.class);

    public static void main(String[] args) {

        String FILE_NAME = "./src/main/resources/input.json";
        String[] finalArray;
        Parser parser = new Parser();
        finalArray= parser.getTimeString("24.10.2016.09.00");
        if(finalArray != null){
            for(String e : finalArray){
                System.out.println(e);
            }
        }
//        Sender sender = new Sender();
//        for(int i = 1; i <= parser.countTrackForAdd(FILE_NAME); i++){
//            finalArray= parser.getArray(FILE_NAME);
//            sender.sender(finalArray[0], finalArray[1], finalArray[2], finalArray[3], finalArray[4]);
//        }
    }
}

package croco;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Parser {

    /**
     * Number of milliseconds in second
     */
    int NUMBER_MILLISECONDS_IN_SECOND = 1000;

    /**
     * Number of fields required to add a track
     */
    int NUMBER_NEED_FIELDS = 5;

    /**
    *   Logger init
     */
    private static final Logger logger = Logger.getLogger(Parser.class);

    /**
     * Converting time to UNIX-format
     */
    long convertTime(String thisTime){
        DateFormat dfm = new SimpleDateFormat("yyyyMMddHHmm");
        long finalTime = 0;
        try {
            finalTime = dfm.parse(thisTime).getTime();
            finalTime = finalTime/NUMBER_MILLISECONDS_IN_SECOND;
        } catch (ParseException ex) {
            logger.trace("ParseException! " + ex);
        }
        return finalTime;
    }

    /**
     * Counting the number of tracks for adding
     */
    int countTrackForAdd(String FILE_NAME){
        int count = 0;
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            while ((line = reader.readLine()) != null) {
                if(line.contains("employee.id")){
                    count++;
                }
            }
        } catch(IOException ex){
            logger.trace("IOException! " + ex);
        } catch(NullPointerException ex) {
            logger.trace("NullPointerException! " + ex);
        }
        return count;
    }

    /**
     * Method for creating an array from a file
     */
    String[] getArray(String FILE_NAME){
        String line;
        ArrayList<String> stringArrayList = null;
        String[] stringArray = null;
        String[] finalArray = null;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            stringArrayList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if(line.contains("employee.id")
                        || line.contains("task.id")
                        || line.contains("comment")
                        || line.contains("start")
                        || line.contains("end")){
                    line = line.replaceAll("\"", "");
                    line = line.replaceAll(",", "");
                    line = extValue(line);
                    stringArrayList.add(line.trim());
                    stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                    finalArray = new String[stringArray.length];
                }
            }
            if(finalArray != null){
                finalArray[0] = String.valueOf(stringArray[0]);
                finalArray[1] = String.valueOf(stringArray[1]);
                finalArray[2] = String.valueOf(stringArray[2]);
                finalArray[3] = String.valueOf(convertTime(getTimeString(stringArray[3])));
                finalArray[4] = String.valueOf(convertTime(getTimeString(stringArray[4])));
                finalArray[5] = String.valueOf(stringArray[5]);
                finalArray[6] = String.valueOf(stringArray[6]);
                finalArray[7] = String.valueOf(stringArray[7]);
                finalArray[8] = String.valueOf(convertTime(getTimeString(stringArray[8])));
                finalArray[9] = String.valueOf(convertTime(getTimeString(stringArray[9])));
            }
        } catch(IOException ex){
            logger.trace("IOException! " + ex);
        } catch(NullPointerException ex) {
            logger.trace("NullPointerException! " + ex);
        } catch(ArrayIndexOutOfBoundsException ex) {
            logger.trace("ArrayIndexOutOfBoundsException! " + ex);
        }
        return finalArray;
    }

    /**
     * Extraction of value
     */
    String extValue(String string){
        String value;
        String[] stringArray = string.split(": ");
        value = stringArray[1];
        return value;
    }

    /**
     * Parsing strings with time
     */
    String getTimeString(String string){

        String changedString = null;
        string = string.replaceAll(" ", ".");
        string = string.replaceAll(":", ".");
        String[] oktet = string.split("\\.");
        String[] changedOktet = new String[oktet.length];
        changedOktet[0] = oktet[2];
        changedOktet[1] = oktet[1];
        changedOktet[2] = oktet[0];
        changedOktet[3] = oktet[3];
        changedOktet[4] = oktet[4];

        changedString = changedOktet[0] + changedOktet[1] + changedOktet[2] + changedOktet[3] +changedOktet[4];

        return changedString;
    }
}
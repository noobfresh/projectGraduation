import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvFileInit {

    private static List<CSVWriter> files = new ArrayList<>();

    public static void initFile() throws IOException {
        String path = "C:\\Users\\PYF\\Desktop\\test\\oddata";
        for(int i  = 0 ; i < 30; i++){
            files.add(new CSVWriter(
                    new FileWriter(
                            new File(path + "\\201709" + getNumbers(i+1) + ".csv"))));
        }
    }

    public static void closeFile() throws IOException {
        for(CSVWriter temp : files){
            temp.close();
        }
    }

    public static String getNumbers(int i){
        if(i < 10){
            return "0" + i;
        }else {
            return String.valueOf(i);
        }
    }

    public static int date2Num(String date){
        return Integer.valueOf(date.substring(date.length() - 2));
    }

    public static CSVWriter getWriter(int date){
        return files.get(date-1);
    }
}

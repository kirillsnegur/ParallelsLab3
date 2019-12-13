import org.apache.hadoop.io.Text;

public class CSVParser {

    public static String[] parseString(Text str){
        return str.toString().split(",");
    };

    public static String[] parseStringQuote(Text str){
        return str.toString().split(",(?=\")");
    };


    public static boolean isArrDelay(String v){
        return  (v.equals("\"ARR_DELAY\""));
    }

//    public static boolean isDestAirport(String v){
//        return  (v.equals("\"DEST_AIRPORT_ID\""));
//    }

    public static boolean isDesription(String v){
        return  (v.equals("Code,Description"));
    }

    public static int getCell(String str){
        return Integer.parseInt(str.replace("\"",""));
    }
}

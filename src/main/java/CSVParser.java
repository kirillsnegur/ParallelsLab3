import org.apache.spark.api.java.JavaRDD;

public class CSVParser {

    public static String[] parseFlightsString(String str){
        return str.split(",");
    };

    public static String[] parseAirportString(String str){ return str.split(",(?=\")");
    };


    public static JavaRDD<String> replaceRDDHeader(JavaRDD<String> str){
        String header = str.first();
        return str.filter(row -> !row.equals(header));
    }

    public static String replaceQuotes(String str){
        return str.replace("\"","");
    }
}

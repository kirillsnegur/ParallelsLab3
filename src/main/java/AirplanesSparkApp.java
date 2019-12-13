import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;


public class AirplanesSparkApp {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flights = sc.textFile("Flights.csv");
        JavaRDD<String> airports = sc.textFile("Airports.csv");

        JavaRDD<String[]> flightsSplit  = flights.map(s -> Arrays.stream(s.split(" ")).toArray(String[]::new ));
        JavaRDD<String[]> airportsSplit  = airports.map(s -> Arrays.stream(s.split(" ")).toArray(String[]::new));

        JavaPairRDD<String,String> flightsPair = flightsSplit.mapToPair(s -> new Tuple2<>(s[0],s[1]));
        JavaPairRDD<String,String> airportsPair = airportsSplit.mapToPair((String s) ->{
            String[] airportAttr = CSVParser.parseAirportString(s);
            return new Tuple2<>(CSVParser.replaceQuotes(airportAttr[0]),CSVParser.replaceQuotes(airportAttr[1]));
        });

        JavaPairRDD<Tuple2<String,String>, RaceData> flightsReduce = flightsPair.reduceByKey();
        JavaPairRDD<Tuple2<String,String>, RaceData> airportReduce = airportsPair.reduceByKey();
    }

}

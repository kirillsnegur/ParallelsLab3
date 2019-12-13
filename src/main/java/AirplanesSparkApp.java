import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Map;


public class AirplanesSparkApp {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flights = CSVParser.replaceRDDHeader(sc.textFile("Flights.csv"));
        JavaRDD<String> airports = CSVParser.replaceRDDHeader(sc.textFile("Airports.csv"));

        JavaPairRDD<String,String> airportsPair = RDDManipulations.mapAirports(airports);

        final Broadcast<Map<String, String>> airportsBroadcasted = sc.broadcast(airportsPair.collectAsMap());

        JavaPairRDD<Tuple2,Tuple2> flightsPair = RDDManipulations.mapFlights(flights,airportsBroadcasted);

        flightsPair.saveAsTextFile("output");
        };
}


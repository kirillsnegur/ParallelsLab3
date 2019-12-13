import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class RDDManipulations {

    public static JavaPairRDD<String,String> mapAirports(JavaRDD<String> airports){
        return airports.mapToPair(
                (s -> {
                    String[] airportsAttr = CSVParser.parseAirportString(s);
                    String airportCode = airportsAttr[0];
                    String airportDscr = airportsAttr[1];
                    return new Tuple2<>(CSVParser.replaceQuotes(airportCode), CSVParser.replaceQuotes(airportDscr));
                })
        );
    }

    public static JavaPairRDD<Tuple2, Tuple2> mapFlights (JavaRDD<String> flights, Broadcast<Map<String,String>> airportsBroadcaster){
        return flights.mapToPair((String s)->{
            String[] flightsAttr = CSVParser.parseFlightsString(s);
            String airportOrign = flightsAttr[11];
            String airportDest = flightsAttr[14];
            String timeDelay = flightsAttr[18];
            float failValue = Float.parseFloat(flightsAttr[19]);
            return new Tuple2<>(new Tuple2<>(
                    airportsBroadcaster.value().get(airportOrign),
                    airportsBroadcaster.value().get(airportDest)),
                    new RaceData(timeDelay, failValue));
        }).reduceByKey((RaceData a,RaceData b) -> new RaceData(
                Math.max(a.getTimeDelay(), b.getTimeDelay()),
                a.getCounterTotal()+b.getCounterTotal(),
                a.getCounterFail()+b.getCounterFail())
        ).mapToPair((Tuple2<Tuple2<String,String>, RaceData> a)-> new Tuple2<>(a._1, new Tuple2<>(a._2.getTimeDelay(), a._2.getFailsPersent())));
    }

}

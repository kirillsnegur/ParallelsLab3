import java.io.Serializable;

public class RaceData implements Serializable {
    private float timeDelay, cancelValue;
    private int counterTotal, counterDelay;

    RaceData(String timeDelay, float cancelValue){
        if (timeDelay != ""){
            this.timeDelay = Float.parseFloat(timeDelay);
        } else this.timeDelay = 0;
        this.cancelValue = cancelValue;
    }

    RaceData(float timeDelay, int counter, int countDelay){
        this.timeDelay = timeDelay;
        this.counter = counter;
        this.cancelValue = c
    }
}

import java.io.Serializable;

public class RaceData implements Serializable {
    private float timeDelay, cancelValue;
    private int counterTotal, counterFails;

    RaceData(String timeDelay, float cancelValue){
        if (timeDelay != ""){
            this.timeDelay = Float.parseFloat(timeDelay);
        } else this.timeDelay = 0;
        this.cancelValue = cancelValue;
    }

    RaceData(float timeDelay, int counterTotal, int counterFail){
        this.timeDelay = timeDelay;
        this.counterTotal = counterTotal;
        this.counterFails = counterFail;
    }
}

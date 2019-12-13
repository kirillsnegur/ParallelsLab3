import java.io.Serializable;

public class RaceData implements Serializable {
    private float timeDelay, failValue, failsPersent;
    private int counterTotal, counterFail;

    RaceData(String timeDelay, float failValue){
        if (timeDelay != ""){
            this.timeDelay = Float.parseFloat(timeDelay);
        } else this.timeDelay = 0;
        this.failValue = failValue;
        this.counterTotal = 1;
        this.counterFail = (this.timeDelay >= 1.0 || this.failValue == 1.0) ? 1 : 0;

    }

    RaceData(float timeDelay, int counterTotal, int counterFail){
        this.timeDelay = timeDelay;
        this.counterTotal = counterTotal;
        this.counterFail = counterFail;
    }
}

import java.io.Serializable;

public class RaceData implements Serializable {
    private float timeDelay, cancelValue;
    private int counter;

    RaceData(String timeDelay, float cancelValue){
        if (timeDelay != ""){
            this.timeDelay = Float.parseFloat(timeDelay);
        } else this.timeDelay = 0;
        this.cancelValue = cancelValue;
    }
}

package ch9;

import static ch9.Logger.*;

public class StateStopped extends  State {
    @Override
    public int getTypeCode() {
        return STATE_STOPPED;
    }



}

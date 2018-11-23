package record;

import java.util.List;

public class HeartRateVariabilitySDNN extends Record {
    private List<InstantaneousBeatsPerMinute> instantaneousBeatsPerMinuteList;

    public List<InstantaneousBeatsPerMinute> getInstantaneousBeatsPerMinuteList() {
        return instantaneousBeatsPerMinuteList;
    }

    public void setInstantaneousBeatsPerMinuteList(List<InstantaneousBeatsPerMinute> instantaneousBeatsPerMinuteList) {
        this.instantaneousBeatsPerMinuteList = instantaneousBeatsPerMinuteList;
    }
}

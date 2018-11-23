package record;

import java.util.Date;

public class InstantaneousBeatsPerMinute {
    private Integer bpm;
    private Date time;

    @Override
    public String toString() {
        return "InstantaneousBeatsPerMinute{" +
                "bpm=" + bpm +
                ", time=" + time +
                '}';
    }

    public InstantaneousBeatsPerMinute() {
    }

    public InstantaneousBeatsPerMinute(Integer bpm, Date time) {
        this.bpm = bpm;
        this.time = time;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


}

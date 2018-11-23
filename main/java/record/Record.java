package record;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private String sourceName;
    private String sourceVersion;
    private String unit;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private Double value;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "sourceName='" + sourceName + '\'' +
                ", sourceVersion='" + sourceVersion + '\'' +
                ", unit='" + unit + '\'' +
                ", creationDate=" + creationDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", value=" + value +
                '}';
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceVersion() {
        return sourceVersion;
    }

    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

package activity;

import java.util.Date;

public class ActivitySummary {
    private Date dateComponents;
    private Double activeEnergyBurned;
    private Double activeEnergyBurnedGoal;
    private String activeEnergyBurnedUnit;
    private Double appleExerciseTime;
    private Double appleExerciseTimeGoal;
    private Double appleStandHours;
    private Double appleStandHoursGoal;

    @Override
    public String toString() {
        return "ActivitySummary{" +
                "dateComponents=" + dateComponents +
                ", activeEnergyBurned=" + activeEnergyBurned +
                ", activeEnergyBurnedGoal=" + activeEnergyBurnedGoal +
                ", activeEnergyBurnedUnit='" + activeEnergyBurnedUnit + '\'' +
                ", appleExerciseTime=" + appleExerciseTime +
                ", appleExerciseTimeGoal=" + appleExerciseTimeGoal +
                ", appleStandHours=" + appleStandHours +
                ", appleStandHoursGoal=" + appleStandHoursGoal +
                '}';
    }

    public Date getDateComponents() {
        return dateComponents;
    }

    public void setDateComponents(Date dateComponents) {
        this.dateComponents = dateComponents;
    }

    public Double getActiveEnergyBurned() {
        return activeEnergyBurned;
    }

    public void setActiveEnergyBurned(Double activeEnergyBurned) {
        this.activeEnergyBurned = activeEnergyBurned;
    }

    public Double getActiveEnergyBurnedGoal() {
        return activeEnergyBurnedGoal;
    }

    public void setActiveEnergyBurnedGoal(Double activeEnergyBurnedGoal) {
        this.activeEnergyBurnedGoal = activeEnergyBurnedGoal;
    }

    public String getActiveEnergyBurnedUnit() {
        return activeEnergyBurnedUnit;
    }

    public void setActiveEnergyBurnedUnit(String activeEnergyBurnedUnit) {
        this.activeEnergyBurnedUnit = activeEnergyBurnedUnit;
    }

    public Double getAppleExerciseTime() {
        return appleExerciseTime;
    }

    public void setAppleExerciseTime(Double appleExerciseTime) {
        this.appleExerciseTime = appleExerciseTime;
    }

    public Double getAppleExerciseTimeGoal() {
        return appleExerciseTimeGoal;
    }

    public void setAppleExerciseTimeGoal(Double appleExerciseTimeGoal) {
        this.appleExerciseTimeGoal = appleExerciseTimeGoal;
    }

    public Double getAppleStandHours() {
        return appleStandHours;
    }

    public void setAppleStandHours(Double appleStandHours) {
        this.appleStandHours = appleStandHours;
    }

    public Double getAppleStandHoursGoal() {
        return appleStandHoursGoal;
    }

    public void setAppleStandHoursGoal(Double appleStandHoursGoal) {
        this.appleStandHoursGoal = appleStandHoursGoal;
    }
}

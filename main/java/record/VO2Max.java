package record;

public class VO2Max extends Record {
    private Integer HKVO2MaxTestType;

    public Integer getHKVO2MaxTestType() {
        return HKVO2MaxTestType;
    }

    public void setHKVO2MaxTestType(Integer HKVO2MaxTestType) {
        this.HKVO2MaxTestType = HKVO2MaxTestType;
    }

    @Override
    public String toString() {
        return super.toString() + "::" +
                "{ HKVO2MaxTestType=" + HKVO2MaxTestType +
                '}';
    }
}

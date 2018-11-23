import activity.ActivitySummary;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import record.Record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ActivityUtils {


    private static String FORMAT = "yyyy-MM-dd";
    private static String BTIME = "1996-03-03";

    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(RecordUtils.class.getClassLoader().getResourceAsStream("export2.xml"));
        ActivityUtils father = new ActivityUtils();
        Map<String, List<Record>> map = father.getAllRecord(document);
    }

    private Map<String, List<Record>> getAllRecord(Document document) throws Exception {
        Element root = document.getRootElement();
        Map<String, List<Record>> map = new HashMap<>();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            if (element.getName().equals("ActivitySummary")) {
                System.out.println("name = " + element.asXML());
                setActivitySummary(element);
            }
        }
        return map;
    }

    private ActivitySummary setActivitySummary(Element element) throws ParseException {
        ActivitySummary summary = new ActivitySummary();
        SimpleDateFormat f = new SimpleDateFormat(FORMAT);
        summary.setDateComponents(f.parse(Optional.ofNullable(element.attributeValue("dateComponents")).orElse(BTIME)));
        summary.setActiveEnergyBurned(Double.valueOf(Optional.ofNullable(element.attributeValue("activeEnergyBurned")).orElse("-9999")));
        summary.setActiveEnergyBurnedGoal(Double.valueOf(Optional.ofNullable(element.attributeValue("activeEnergyBurnedGoal")).orElse("-9999")));
        summary.setActiveEnergyBurnedUnit(element.attributeValue("activeEnergyBurnedUnit"));
        summary.setAppleExerciseTime(Double.valueOf(Optional.ofNullable(element.attributeValue("appleExerciseTime")).orElse("-9999")));
        summary.setAppleExerciseTimeGoal(Double.valueOf(Optional.ofNullable(element.attributeValue("appleExerciseTimeGoal")).orElse("-9999")));
        summary.setAppleStandHours(Double.valueOf(Optional.ofNullable(element.attributeValue("appleStandHours")).orElse("-9999")));
        summary.setAppleStandHoursGoal(Double.valueOf(Optional.ofNullable(element.attributeValue("appleStandHoursGoal")).orElse("-9999")));
        System.out.println("summary = " + summary);
        return summary;
    }
}

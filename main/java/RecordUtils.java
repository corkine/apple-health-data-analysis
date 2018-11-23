import record.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class RecordUtils {

    private static String FORMAT = "yyyy-MM-dd HH:mm:ss Z";
    private static String FORMAT2 = "ah:mm:ss.SS";
    private static String BTIME = "1996-03-03 00:00:00 +0800";
    private static String PATH = "record";


    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(RecordUtils.class.getClassLoader().getResourceAsStream("export2.xml"));
        RecordUtils father = new RecordUtils();
        //father.saveAllData(father.getAllRecord(document),"export.out");
        //Map<String, List<Record>> map = father.loadAllRecord("export.out");
        Map<String, List<Record>> map = father.getAllRecord(document);
        Set<String> strings = map.keySet();
        System.out.println("strings = " + strings);
        List<Record> hr = map.get("HKQuantityTypeIdentifierHeartRate");
        DoubleSummaryStatistics collect = hr.stream().parallel().collect(Collectors.summarizingDouble(Record::getValue));
        System.out.println("collect = " + collect);
    }

    @SuppressWarnings("unchecked")
    private Map<String,List<Record>> loadAllRecord(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
        return (Map<String, List<Record>>) stream.readObject();
    }

    private void saveAllData(Map<String,List<Record>> allRecord, String fileName) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(allRecord);
        outputStream.close();
    }
    
    private Map<String, List<Record>> getAllRecord(Document document) throws Exception {
        Element root = document.getRootElement();
        Map<String, List<Record>> map = new HashMap<>();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element element = it.next();
            String type = element.attributeValue("type");
            if (type != null) {
                List<Record> list = map.getOrDefault(type, new ArrayList<>());
                switch (type) {
                    case "HKQuantityTypeIdentifierActiveEnergyBurned": {
                        list.add(setActiveEnergyBurned(element));
                        break;
                    } case "HKQuantityTypeIdentifierAppleExerciseTime": {
                        list.add(setAppleExerciseTime(element));
                        break;
                    } case "HKQuantityTypeIdentifierBasalEnergyBurned": {
                        list.add(setBasalEnergyBurned(element));
                        break;
                    } case "HKQuantityTypeIdentifierBodyMass": {
                        list.add(setBodyMess(element));
                        break;
                    } case "HKQuantityTypeIdentifierDistanceCycling": {
                        list.add(setDistanceCycling(element));
                        break;
                    } case "HKQuantityTypeIdentifierDistanceWalkingRunning": {
                        list.add(setDistanceWalkingRunning(element));
                        break;
                    } case "HKQuantityTypeIdentifierFlightsClimbed": {
                        list.add(setFlightsClimbed(element));
                        break;
                    } case "HKQuantityTypeIdentifierHeartRate": {
                        list.add(setHeartRate(element));
                        break;
                    } case "HKQuantityTypeIdentifierHeartRateVariabilitySDNN": {
                        list.add(setHeartRateVariabilitySDNN(element));
                        break;
                    } case "HKQuantityTypeIdentifierHeight": {
                        list.add(setHeight(element));
                        break;
                    } case "HKQuantityTypeIdentifierRestingHeartRate": {
                        list.add(setRestingHeartRate(element));
                        break;
                    } case "HKQuantityTypeIdentifierStepCount": {
                        list.add(setStepCount(element));
                        break;
                    } case "HKQuantityTypeIdentifierVO2Max": {
                        list.add(setVO2Max(element));
                        break;
                    } case "HKQuantityTypeIdentifierWalkingHeartRateAverage": {
                        list.add(setWalkingHeartRateAverage(element));
                        break;
                    }
                }
                map.put(type,list);
            }
        }
        return map;
    }

    private Record setRecord(Element element) throws Exception {
        String type = element.attributeValue("type").replace("HKQuantityTypeIdentifier","");
        Record record = (Record) Class.forName(PATH + "." + type).newInstance();
        SimpleDateFormat f = new SimpleDateFormat(FORMAT);
        record.setSourceName(element.attributeValue("sourceName"));
        record.setSourceVersion(element.attributeValue("sourceVersion"));
        record.setUnit(element.attributeValue("unit"));
        record.setCreationDate(f.parse(Optional.ofNullable(element.attributeValue("creationDate")).orElse(BTIME)));
        record.setStartDate(f.parse(Optional.ofNullable(element.attributeValue("startDate")).orElse(BTIME)));
        record.setEndDate(f.parse(Optional.ofNullable(element.attributeValue("endDate")).orElse(BTIME)));
        record.setValue(Double.valueOf(Optional.ofNullable(element.attributeValue("value")).orElse("-9999")));
        return record;
    }

    private VO2Max setVO2Max(Element element) throws Exception {
        VO2Max res = (VO2Max) setRecord(element);
        String tType = Optional.ofNullable(element.element("MetadataEntry"))
                .filter(entry -> entry.attributeValue("key") != null ||
                        entry.attributeValue("key").equalsIgnoreCase("HKVO2MaxTestType"))
                .map(testType -> testType.attributeValue("value")).orElse("-9999");
        res.setHKVO2MaxTestType(Integer.valueOf(tType));
        return res;
    }

    private ActiveEnergyBurned setActiveEnergyBurned(Element element) throws Exception {
        return (ActiveEnergyBurned) setRecord(element);
    }

    private AppleExerciseTime setAppleExerciseTime(Element element) throws Exception {
        return (AppleExerciseTime) setRecord(element);
    }

    private BasalEnergyBurned setBasalEnergyBurned(Element element) throws Exception {
        return (BasalEnergyBurned) setRecord(element);
    }

    private BodyMass setBodyMess(Element element) throws Exception {
        return (BodyMass) setRecord(element);
    }

    private DistanceCycling setDistanceCycling(Element element) throws Exception {
        return (DistanceCycling) setRecord(element);
    }

    private DistanceWalkingRunning setDistanceWalkingRunning(Element element) throws Exception {
        return (DistanceWalkingRunning) setRecord(element);
    }

    private FlightsClimbed setFlightsClimbed(Element element) throws Exception {
        return (FlightsClimbed) setRecord(element);
    }

    private HeartRate setHeartRate(Element element) throws Exception {
        return (HeartRate) setRecord(element);
    }

    private HeartRateVariabilitySDNN setHeartRateVariabilitySDNN(Element element) throws Exception {
        HeartRateVariabilitySDNN res = (HeartRateVariabilitySDNN) setRecord(element);
        SimpleDateFormat f = new SimpleDateFormat(FORMAT);
        SimpleDateFormat f2 = new SimpleDateFormat(FORMAT2);
        Date createDate = f.parse(Optional.ofNullable(element.attributeValue("creationDate")).orElse(BTIME));
        
        List<InstantaneousBeatsPerMinute> list = new LinkedList<>();
        List<Element> bpms = Optional.ofNullable(element.element("HeartRateVariabilityMetadataList"))
                .filter(mdList -> mdList.elements("InstantaneousBeatsPerMinute") != null)
                .map(mdLis -> mdLis.elements("InstantaneousBeatsPerMinute")).orElse(new LinkedList());
        bpms.forEach(bp -> {
                    InstantaneousBeatsPerMinute beats = new InstantaneousBeatsPerMinute();
                    beats.setBpm(Integer.valueOf(Optional.ofNullable(bp.attributeValue("bpm")).orElse("-9999")));
                    try {
                        Date time = f2.parse(Optional.ofNullable(bp.attributeValue("time")).orElse("上午0:00:00.00"));
                        beats.setTime(covDate(createDate, time));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    list.add(beats);
        });
        return res;
    }

    private Date covDate(Date date, Date time) {
        Calendar rd = Calendar.getInstance();
        rd.setTime(date);
        int year = rd.get(Calendar.YEAR);
        int month = rd.get(Calendar.MONTH);
        int day = rd.get(Calendar.DATE);

        Calendar rt = Calendar.getInstance();
        rt.setTime(time);
        int hour = rt.get(Calendar.HOUR);
        int mins = rt.get(Calendar.MINUTE);
        int secs = rt.get(Calendar.SECOND);

        Calendar cc = Calendar.getInstance();
        cc.set(year,month,day,hour,mins,secs);
        return cc.getTime();
    }

    private Height setHeight(Element element) throws Exception {
        return (Height) setRecord(element);
    }

    private RestingHeartRate setRestingHeartRate(Element element) throws Exception {
        return (RestingHeartRate) setRecord(element);
    }

    private StepCount setStepCount(Element element) throws Exception {
        return (StepCount) setRecord(element);
    }

    private WalkingHeartRateAverage setWalkingHeartRateAverage(Element element) throws Exception {
        return (WalkingHeartRateAverage) setRecord(element);
    }
 }

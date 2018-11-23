import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConvData {
    private String fileName = "export2.xml";
    private int current = 0;
    private int all = 0;
    
    private Element readFromFile() throws DocumentException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
        return new SAXReader().read(stream).getRootElement();
    }

    @SuppressWarnings("unchecked")
    private void doLoop(Element root) {
        all = root.elements().size();
        Map<String, String> map = new HashMap<>();
        ((List<Element>) root.elements()).forEach(elements -> {
            String newLine = readString(elements);
            String key = Optional.ofNullable(elements.getName()).orElse("Something");
            map.put(key, map.getOrDefault(key, "") + newLine);
            current += 1;
            System.out.println("Progress:  " + (double) current / (double) all);
        });
        System.out.println("map.keySet() = " + map.keySet());
    }
    
    private String readString(Element element) {
        String type_ = element.attributeValue("type");
        String unit = element.attributeValue("unit");
        String value = element.attributeValue("value");
        String sourceName = element.attributeValue("sourceName");
        String sourceVersion = element.attributeValue("sourceVersion");
        String device = element.attributeValue("device");
        String creationDate = element.attributeValue("creationDate");
        String startDate = element.attributeValue("startDate");
        String endDate = element.attributeValue("endDate");
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                type_, unit, value, sourceName, sourceVersion, device, creationDate, startDate, endDate);
    }

    public static void main(String[] args) throws DocumentException {
        ConvData father = new ConvData();
        Element element = father.readFromFile();
        father.doLoop(element);
    }
}

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Iterator;

class DoTest {

    @Test public void test1() throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(getClass().getClassLoader().getResourceAsStream("export.xml"));
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("export.out"));
        outputStream.writeObject(document);
        outputStream.close();
    }

    @Test public void testRead() {

    }

}
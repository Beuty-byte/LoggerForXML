package loggingInXML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XMLParser {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public String getXMLValue(String pathToXMLFile)throws  XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser  = null;
        try {
            parser = factory.createXMLStreamReader(new FileInputStream(pathToXMLFile));
            logger.info("Config File read {}",pathToXMLFile);
        } catch (XMLStreamException e) {
            e.printStackTrace();
            logger.warn("XMLStreamException {}",e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.warn("FileNotFoundException {}",e);
        }

        StringBuilder valueInXML = new StringBuilder();
        while(parser.hasNext()){
            int event = parser.next();
            if(event == XMLStreamConstants.CHARACTERS){
                valueInXML.append(parser.getText());
            }
        }
        return valueInXML.toString();
    }
}

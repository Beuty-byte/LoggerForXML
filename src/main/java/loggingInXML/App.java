package loggingInXML;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;

import java.io.*;
import java.util.Objects;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws XMLStreamException {

        logger.info("Start Application");

        String configXMLFile = args[0];
        String fileToChange = args[1];

        XMLParser xmlParser = new XMLParser();
        String suffix = xmlParser.getXMLValue(configXMLFile);
        System.out.println(suffix);

        File[] filesList;
        File filesPath = new File(fileToChange);

        filesList = filesPath.listFiles();

        for (int i = 0; i < Objects.requireNonNull(filesList).length; i++) {
            String buf = filesList[i].getName();
            System.out.print(buf + " -> ");

            String[] fileName = buf.split("\\.");
            buf = fileName[0]+suffix+"."+fileName[1];
            System.out.println(buf);



            filesList[i].renameTo(new File(fileToChange + buf));
            logger.info("file rename " + filesList[i].getName());

        }
            logger.info("Application finish work");
    }
}
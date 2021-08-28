package Task3.com.company.xml;

import Task3.com.company.domain.CallPrice;
import Task3.com.company.domain.Parameters;
import Task3.com.company.domain.Tarif;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ConnectXmlReader {
    public List<Tarif> read(String fileName) throws FileNotFoundException {
        XMLStreamReader reader = null;
        try {
            List<Tarif> tarifs = new ArrayList<>();
            Tarif tarif = null;
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("tarif".equals(tagName)) {
                            tarif = new Tarif();
                        } else if ("name".equals(tagName)) {
                            tarif.setName(reader.getElementText());
                        } else if ("operatorName".equals(tagName)) {
                            tarif.setOperatorName(reader.getElementText());
                        } else if ("payRoll".equals(tagName)) {
                            tarif.setPayRoll(Integer.parseInt(reader.getElementText()));
                        } else if ("callPrice".equals(tagName)) {
                            CallPrice callPrice = new CallPrice();
                            tarif.setCallPrice(callPrice);
                        } else if ("inNetCallCost".equals(tagName)) {
                            tarif.getCallPrice().setInNetCallCost(Integer.valueOf(reader.getElementText()));
                        } else if ("outNetCallCost".equals(tagName)) {
                            tarif.getCallPrice().setOutNetCallCost(Integer.valueOf(reader.getElementText()));
                        } else if ("homePhoneCallCost".equals(tagName)) {
                            tarif.getCallPrice().setHomePhoneCallCost(Integer.valueOf(reader.getElementText()));
                        } else if ("smsPrice".equals(tagName)) {
                            tarif.setSmsPrice(Integer.valueOf(reader.getElementText()));
                        } else if ("parameters".equals(tagName)) {
                            Parameters parameters = new Parameters();
                            tarif.setParameters(parameters);
                        } else if ("lovelyNumber".equals(tagName)) {
                            tarif.getParameters().setLovelyNumber(reader.getElementText());
                        } else if ("tarificationTime".equals(tagName)) {
                            tarif.getParameters().setTarificationTime(Integer.valueOf(reader.getElementText()));
                        } else if ("comingPay".equals(tagName)) {
                            tarif.getParameters().setComingPay(Integer.valueOf(reader.getElementText()));
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("tarif".equals(tagName)) {
                            tarifs.add(tarif);
                        }
                        break;
                    }
                }
            }
            return tarifs;
        } catch (XMLStreamException e) {
            e.printStackTrace();

            return null;
        }
        finally {
            try {
                reader.close();
            }catch (Exception e){

            }
        }
    }
}

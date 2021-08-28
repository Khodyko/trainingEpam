package Task3.com.company.xml;

import Task3.com.company.domain.Tarif;

import java.io.IOException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {


        ConnectXmlValidator validator = new ConnectXmlValidator("Task3/resources/connect.xml");
        if (validator.validate()){

            ConnectXmlReader reader=new ConnectXmlReader();
            List<Tarif> tarifs=reader.read("Task3/resources/connect.xml");

            for (Tarif tarif:tarifs) {
                System.out.println(tarif);
            }
        }
    }
}

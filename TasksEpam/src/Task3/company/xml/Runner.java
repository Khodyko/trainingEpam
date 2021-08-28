package com.company.xml;

import com.company.domain.Tarif;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {


        ConnectXmlValidator validator = new ConnectXmlValidator("connect.xml");
        if (validator.validate()){

            ConnectXmlReader reader=new ConnectXmlReader();
            List<Tarif> tarifs=reader.read("connect.xml");

            for (Tarif tarif:tarifs) {
                System.out.println(tarif);
            }
        }
    }
}

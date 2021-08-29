package Task3.com.company.xml;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Task3.com.company.domain.Tarif;

public class Runner {
	public static void main(String[] args) throws IOException {
		System.out.println("Run!");

		ConnectXmlValidator validator = new ConnectXmlValidator("src/Task3/resources/connect.xml");
		if (validator.validate()) {

			ConnectXmlReader reader = new ConnectXmlReader();
			List<Tarif> tarifs = reader.read("src/Task3/resources/connect.xml");

			Collections.sort(tarifs, new Comparator<Tarif>() {
				@Override
				public int compare(Tarif o1, Tarif o2) {
					return o1.getOperatorName().compareTo(o2.getOperatorName());
				}
			});
			for (Tarif tarif : tarifs) {
				System.out.println(tarif);
			}
		}
	}
}

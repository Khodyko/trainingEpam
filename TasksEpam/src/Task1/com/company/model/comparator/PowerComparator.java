package Task1.com.company.model.comparator;

import Task1.com.company.model.device.ElectricDevice;

import java.util.Comparator;

public class PowerComparator implements Comparator<ElectricDevice> {
	@Override
	public int compare(ElectricDevice o1, ElectricDevice o2) {
		return o1.getPower() - o2.getPower();
	}
}

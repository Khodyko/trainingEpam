package Task1.com.company.model.filter.filterImpl;


import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.filter.MyFilter;

import java.util.Locale;

public class NameFilter extends MyFilter {
	private String name;

	public void setName() {
		controller.showMessage("Insert exact name or part of it");
		String nameFilter=controller.InsertAndTrim();
		name=nameFilter.toLowerCase(Locale.ROOT);
	}


	public NameFilter() {

	}

	@Override
	public boolean isDeviceSuitable(ElectricDevice electricDevice) {

		if(electricDevice.getClass().getSimpleName().toLowerCase(Locale.ROOT).contains(name)){
			return true;
		}
		else{
		return false;}
	}

	public boolean isDeviceSuitable() {

		return false;
	}


	public boolean isActivate() {
		return isActivate;
	}

	@Override
	public void getParamForActivation() {
		setName();
	}


}

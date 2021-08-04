package Task1.com.company.controller;




import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.device.deviceImpl.*;
import Task1.com.company.model.filter.filterImpl.NameFilter;
import com.company.model.device.deviceImpl.*;
import Task1.com.company.model.filter.MyFilter;
import Task1.com.company.model.filter.filterImpl.AmperageFilter;
import Task1.com.company.model.filter.filterImpl.SystemSupplyFilter;
import Task1.com.company.model.filter.filterImpl.VoltageFilter;

import java.util.ArrayList;

public class Creator {
	private static Creator instance;
	private ArrayList<ElectricDevice> electricDevicesList;
	private ArrayList<MyFilter> filtersList;
	private Creator() {}

	public ArrayList<MyFilter> getFiltersList() {
		return filtersList;
	}

	public ArrayList<ElectricDevice> createAllDevices() {
	electricDevicesList =new ArrayList<ElectricDevice>();
	electricDevicesList.add(new Refrigerator(123,220));
	electricDevicesList.add(new Lamp(150,110));
	electricDevicesList.add(new Mobile(123,220));
	electricDevicesList.add(new TV(123,120));
	electricDevicesList.add(new Microwave(150,220));
	return electricDevicesList;
	}

	public ArrayList<ElectricDevice> getEDevicesList() {
		return electricDevicesList;
	}

	public ArrayList<MyFilter> createAllFilters() {
		filtersList =new ArrayList<>();
		filtersList.add(new AmperageFilter());
		filtersList.add(new NameFilter( ));
		filtersList.add(new SystemSupplyFilter());
		filtersList.add(new VoltageFilter( ));

		return filtersList;
	}

	public static Creator getInstance() {
		 if (instance == null) {
	            instance = new Creator();
	        }
	        return instance;
	}

}

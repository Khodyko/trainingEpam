package Task1.com.company.model.filter.filterImpl;

import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.filter.MyFilter;
import Task1.com.company.controller.commands.activateDeactivateFilters.systemSupplyFilterActivate.ShowSysSuplyForActSuppFilter;
import Task1.com.company.model.device.SystemOfSupply;

public class SystemSupplyFilter extends MyFilter {

	public SystemSupplyFilter() {
		super();
	}

	private SystemOfSupply systemOfSupplyFiltrate;

	public SystemOfSupply getSystemOfSupplyFiltrate() {
		return systemOfSupplyFiltrate;
	}

	@Override
	public boolean isDeviceSuitable(ElectricDevice electricDevice) {
		if ((electricDevice.getSystemOfSupply()).equals(getSystemOfSupplyFiltrate())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isActivate() {
		return isActivate;
	}

	@Override
	public void getParamForActivation() {
		new ShowSysSuplyForActSuppFilter().execute();
	}

	public void setSystemOfSupplyFiltrate(SystemOfSupply systemOfSupplyFiltrate) {
		this.systemOfSupplyFiltrate = systemOfSupplyFiltrate;
		System.out.println(this.systemOfSupplyFiltrate);
	}
}

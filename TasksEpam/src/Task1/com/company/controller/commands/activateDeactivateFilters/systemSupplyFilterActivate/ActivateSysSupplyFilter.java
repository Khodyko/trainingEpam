package Task1.com.company.controller.commands.activateDeactivateFilters.systemSupplyFilterActivate;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.device.SystemOfSupply;
import Task1.com.company.model.filter.MyFilter;
import Task1.com.company.model.filter.filterImpl.SystemSupplyFilter;

import java.util.ArrayList;

public class ActivateSysSupplyFilter extends Command {
	private SystemOfSupply systemOfSupply;
	MyFilter systemOfSupplyFilter;

	public ActivateSysSupplyFilter(SystemOfSupply systemOfSupply) {
		this.systemOfSupply = systemOfSupply;
	}

	@Override
	public void execute() {
		ArrayList<MyFilter> allFilterList = controller.getAllFilterList();

		for (int i = 0; i < allFilterList.size(); i++) {
			if (allFilterList.get(i) instanceof SystemSupplyFilter) {
				systemOfSupplyFilter = allFilterList.get(i);
				break;
			}
		}
		SystemSupplyFilter systemSupplyFilter = (SystemSupplyFilter) systemOfSupplyFilter; // cast from MyFilter to
																							// SystemSupplyFilter
		systemSupplyFilter.setSystemOfSupplyFiltrate(systemOfSupply);

	}
}

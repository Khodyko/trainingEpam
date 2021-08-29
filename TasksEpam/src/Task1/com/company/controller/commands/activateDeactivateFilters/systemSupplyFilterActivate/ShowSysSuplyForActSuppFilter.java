package Task1.com.company.controller.commands.activateDeactivateFilters.systemSupplyFilterActivate;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.device.SystemOfSupply;

public class ShowSysSuplyForActSuppFilter extends Command {
	@Override
	public void execute() {
		controller.showMessage("Insert Supply System is needed to filtrate");
		for (SystemOfSupply systemOfSupply : SystemOfSupply.values()) {
			String numberOfInsertVariant = String.valueOf((systemOfSupply.ordinal() + 1));
			nameOfChoiceAndCommand.put(systemOfSupply.name(), new ActivateSysSupplyFilter(systemOfSupply));
		}

		controller.makeMenuWithCommands("", nameOfChoiceAndCommand);

	}
}

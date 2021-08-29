package Task1.com.company.controller.commands.activateDeactivateFilters;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.controller.commands.FirstMenu;
import Task1.com.company.model.filter.MyFilter;

import java.util.ArrayList;

public class ActivateDeactivate extends Command {
	private ArrayList<MyFilter> filterArray;
	private boolean needActivate;

	public ActivateDeactivate(ArrayList<MyFilter> filterArray, boolean needActivate) {
		this.filterArray = filterArray;
		this.needActivate = needActivate;
	}

	public void execute() {
		for (int i = 0; i < filterArray.size(); i++) {
			filterArray.get(i).setActivate(needActivate);
		}

		nameOfChoiceAndCommand.put("Back", new FirstMenu());
		controller.makeMenuWithCommands("", nameOfChoiceAndCommand);
	}
}

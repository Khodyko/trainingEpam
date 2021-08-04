package Task1.com.company.controller.commands.activateDeactivateFilters;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.filter.MyFilter;
import Task1.com.company.controller.commands.FirstMenu;

import java.util.ArrayList;

public class DeactivateFiltersMenu extends Command {
    private MyFilter filter;
    @Override
    public void execute() {
        ArrayList<MyFilter> activatedFilterList= controller.getActivatedFilterList();
        nameOfChoiceAndCommand.put("Deactivate All Filters", new ActivateDeactivate(activatedFilterList,false));

        for (int i = 0; i < activatedFilterList.size(); i++) {

            ArrayList<MyFilter> oneFilterArray=new ArrayList<>();
            oneFilterArray.add(activatedFilterList.get(i));
            nameOfChoiceAndCommand.put("Deactivate " +activatedFilterList.get(i).getClass().getSimpleName(),( new ActivateDeactivate(oneFilterArray,false)));
        }
        nameOfChoiceAndCommand.put("Show activated filters", new ShowActivatedFilters());
        nameOfChoiceAndCommand.put("Back", new FirstMenu());
        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}

package Task1.com.company.controller.commands.activateDeactivateFilters;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.filter.MyFilter;
import Task1.com.company.controller.commands.FirstMenu;

import java.util.ArrayList;

public class ActivateFiltersMenu extends Command {
    private MyFilter filter;
    @Override
    public void execute() {
        ArrayList<MyFilter> unactivatedFilterList= controller.getUnactivatedFilterList();
        nameOfChoiceAndCommand.put("Activate All Filters", new ActivateDeactivate(unactivatedFilterList,true));

        for (int i = 0; i < unactivatedFilterList.size(); i++) {

            ArrayList<MyFilter> oneFilterArray=new ArrayList<>();
            oneFilterArray.add(unactivatedFilterList.get(i));
            nameOfChoiceAndCommand.put("Activate " +unactivatedFilterList.get(i).getClass().getSimpleName(),( new ActivateDeactivate(oneFilterArray,true)));
        }
        nameOfChoiceAndCommand.put("Show activated filters", new ShowActivatedFilters());
        nameOfChoiceAndCommand.put("Back", new FirstMenu());
        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}

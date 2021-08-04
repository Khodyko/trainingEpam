package Task1.com.company.controller.commands.activateDeactivateFilters;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.filter.MyFilter;
import Task1.com.company.controller.commands.FirstMenu;

import java.util.ArrayList;

public class ShowActivatedFilters extends Command {




    @Override
    public void execute() {
        ArrayList<MyFilter> filtersList=controller.getAllFilterList();
        ArrayList<MyFilter> activatedFilterList=new ArrayList<>();
        for (int i = 0; i <filtersList.size() ; i++) {
            if(filtersList.get(i).isActivate()){
                activatedFilterList.add(filtersList.get(i));
            }
        }
        controller.showMessage("*****ACTIVE FILTERS:*****");

        if(activatedFilterList==null || activatedFilterList.size()==0){
            controller.showSpecMessage("not available" );
        }
        else{
            for (int i = 0; i < activatedFilterList.size(); i++) {

                controller.showSpecMessage(activatedFilterList.get(i).getClass().getSimpleName());
            }}
        nameOfChoiceAndCommand.put("Back", new FirstMenu());

        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}

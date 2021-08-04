package Task1.com.company.controller.commands;

import Task1.com.company.controller.commands.activateDeactivateFilters.ActivateFiltersMenu;
import Task1.com.company.controller.commands.activateDeactivateFilters.DeactivateFiltersMenu;
import Task1.com.company.controller.commands.activateDeactivateFilters.ShowActivatedFilters;
import Task1.com.company.controller.commands.turnOnOff.TurnOnMenu;
import Task1.com.company.controller.commands.showPicture.ShowPicture;
import Task1.com.company.controller.commands.turnOnOff.ShowTurnedOnEDevices;
import Task1.com.company.controller.commands.turnOnOff.TurnOffMenu;

public class FirstMenu extends Command{
    @Override
    public void execute() {


        nameOfChoiceAndCommand.put("Show electric devices with current filters \n    (only for \"turnOn\" devices)", new ShowEDevicesWithFilters());
        nameOfChoiceAndCommand.put("Get a picture of device", new ShowPicture());
        nameOfChoiceAndCommand.put("Activate filters", new ActivateFiltersMenu());
        nameOfChoiceAndCommand.put("Deactivate filters", new DeactivateFiltersMenu());
        nameOfChoiceAndCommand.put("Show activated filters", new ShowActivatedFilters());
        nameOfChoiceAndCommand.put("Calculate Power of electric devices(with current filters \n    (only for \"turnOn\" devices)", new CalculatePowerEDevices());
        nameOfChoiceAndCommand.put("Turn on some devices", new TurnOnMenu());
        nameOfChoiceAndCommand.put("Turn off some devices", new TurnOffMenu());
        nameOfChoiceAndCommand.put("Show turned On devices", new ShowTurnedOnEDevices());
        nameOfChoiceAndCommand.put("Show sorted electric devices with current filters \n    (only for \"turnOn\" devices)", new SortEDeviceWithPower());


        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}

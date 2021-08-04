package Task1.com.company.controller.commands.turnOnOff;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.controller.commands.FirstMenu;
import Task1.com.company.model.device.ElectricDevice;

import java.util.ArrayList;

public class ShowTurnedOnEDevices extends Command {
    @Override
    public void execute() {
        ArrayList<ElectricDevice> turnedOnEDevices=controller.getTurnedOnEDeviceList();
        controller.showMessage("*****Turned ON Devices:*****");
        if(turnedOnEDevices==null || turnedOnEDevices.size()==0){
            controller.showSpecMessage("not available");
        }
        else {
            for (int i = 0; i < turnedOnEDevices.size(); i++) {

                controller.showSpecMessage(turnedOnEDevices.get(i).getClass().getSimpleName());
            }
        }
        nameOfChoiceAndCommand.put("Back", new FirstMenu());
        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}

package Task1.com.company.controller.commands.turnOnOff;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.controller.commands.FirstMenu;
import Task1.com.company.model.device.ElectricDevice;

import java.util.ArrayList;

public class TurnOffMenu extends Command {
    @Override
    public void execute() {
        ArrayList<ElectricDevice> allEDeviceList = controller.getAllEDevices();
        ArrayList<ElectricDevice> turnedOnEDeviceList = controller.getTurnedOnEDeviceList();

        for (int i = 0; i < allEDeviceList.size(); i++) {
            ElectricDevice device=allEDeviceList.get(i);
            if(device.isTurnOn()){
                turnedOnEDeviceList.add(device);
            }}

        nameOfChoiceAndCommand.put("Turn Off all devices", new TurnOnOffAllOr1Device(turnedOnEDeviceList,false));

        for (int i = 0; i < turnedOnEDeviceList.size(); i++) {

            ArrayList<ElectricDevice> oneFilterArray=new ArrayList<>();
            oneFilterArray.add(turnedOnEDeviceList.get(i));
            nameOfChoiceAndCommand.put("Turn Off " +turnedOnEDeviceList.get(i).getClass().getSimpleName(),( new TurnOnOffAllOr1Device(oneFilterArray, true)));
        }
        nameOfChoiceAndCommand.put("Back", new FirstMenu());
        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}
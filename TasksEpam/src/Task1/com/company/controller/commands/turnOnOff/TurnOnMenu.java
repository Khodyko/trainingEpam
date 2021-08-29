package Task1.com.company.controller.commands.turnOnOff;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.controller.commands.FirstMenu;

import java.util.ArrayList;

public class TurnOnMenu extends Command {
	@Override
	public void execute() {

		ArrayList<ElectricDevice> turnedOffEDeviceList = controller.getTurnedOffEDeviceList();

		nameOfChoiceAndCommand.put("Activate All Devices", new TurnOnOffAllOr1Device(turnedOffEDeviceList, true));

		for (int i = 0; i < turnedOffEDeviceList.size(); i++) {

			ArrayList<ElectricDevice> oneFilterArray = new ArrayList<>();
			oneFilterArray.add(turnedOffEDeviceList.get(i));
			nameOfChoiceAndCommand.put("Turn On " + turnedOffEDeviceList.get(i).getClass().getSimpleName(),
					(new TurnOnOffAllOr1Device(oneFilterArray, true)));
		}
		nameOfChoiceAndCommand.put("Back", new FirstMenu());
		controller.makeMenuWithCommands("", nameOfChoiceAndCommand);
	}
}

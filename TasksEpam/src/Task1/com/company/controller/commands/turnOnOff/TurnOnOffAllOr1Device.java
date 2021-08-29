package Task1.com.company.controller.commands.turnOnOff;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.controller.commands.FirstMenu;

import java.util.ArrayList;

public class TurnOnOffAllOr1Device extends Command {
	private ArrayList<ElectricDevice> electricDevices;
	private boolean needTurnOn;

	public TurnOnOffAllOr1Device(ArrayList<ElectricDevice> electricDevices, boolean needTurnOn) {
		this.electricDevices = electricDevices;
		this.needTurnOn = needTurnOn;
	}

	@Override
	public void execute() {
		for (int i = 0; i < electricDevices.size(); i++) {
			if (needTurnOn) {
				electricDevices.get(i).turnOn();
			} else {
				electricDevices.get(i).turnOff();
			}
		}
		nameOfChoiceAndCommand.put("Back", new FirstMenu());
		controller.makeMenuWithCommands("", nameOfChoiceAndCommand);
	}
}

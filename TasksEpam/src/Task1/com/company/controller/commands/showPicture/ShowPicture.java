package Task1.com.company.controller.commands.showPicture;

import Task1.com.company.controller.commands.Command;
import Task1.com.company.controller.commands.FirstMenu;
import Task1.com.company.model.device.ElectricDevice;

import java.util.ArrayList;

public class ShowPicture extends Command {
	@Override
	public void execute() {
		ArrayList<ElectricDevice> eDeviceList = controller.getAllEDevices();
		for (int i = 0; i < eDeviceList.size(); i++) {
			nameOfChoiceAndCommand.put("Show " + eDeviceList.get(i).getClass().getSimpleName() + " Picture",
					new ShowEDevicePic(eDeviceList.get(i)));
		}
		nameOfChoiceAndCommand.put("Back", new FirstMenu());

		controller.makeMenuWithCommands("", nameOfChoiceAndCommand);
	}
}

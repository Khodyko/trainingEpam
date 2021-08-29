package Task1.com.company.controller;

import Task1.com.company.console.ConsoleSpeaker;
import Task1.com.company.console.InsertManager;
import Task1.com.company.controller.commands.Command;
import Task1.com.company.controller.commands.FirstMenu;
import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.filter.MyFilter;


import java.util.*;

public class Controller {
	static Command lastCommand = new FirstMenu(); // for return to last monitor with exception

	public void setLastCommand(Command lastCommand) {
		if (lastCommand != null) {
			this.lastCommand = lastCommand;
		}
	}

	public Command getLastCommand() {
		return lastCommand;
	}

	public ArrayList<ElectricDevice> electricDevicesList; // private!!!!!
	public ArrayList<MyFilter> filtersList;// private!!!!!
	private static Controller instance;
	Creator creator;

	ConsoleSpeaker consoleSpeaker;
	InsertManager insertManager;
	Validator validator;

	private Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void startSystem() {
		creator = Creator.getInstance();
		creator.createAllDevices();
		electricDevicesList = creator.getEDevicesList();
		creator.createAllFilters();
		filtersList = creator.getFiltersList();// Exception!!!!!
		consoleSpeaker = ConsoleSpeaker.getInstance();
		validator = Validator.getInstance();
		insertManager = InsertManager.getInstance();
		showMessage("Hello");
		showMessage("It is search of electric devices");
		lastCommand.execute();

	}

	////////////////////// Consolework
	public Command showHeaderAndItems2Choice(String headingTitle, LinkedHashMap<String, Command> numberAndStr2Choice) {
		Command headerAndItems2Choice = consoleSpeaker.showHeaderAndItems2Choice(headingTitle, numberAndStr2Choice);
		return headerAndItems2Choice;
	}

	public void showMessage(String message) {
		consoleSpeaker.showMessage(message);
	}

	public void showSpecMessage(String message) {
		consoleSpeaker.showListMessage("<<<<<" + message + ">>>>>");
	}
///////////////////////Console work

////////////////////////Arrays
	public ArrayList<MyFilter> getAllFilterList() {
		return creator.getFiltersList();
	}

	public ArrayList<ElectricDevice> getAllEDevices() {
		return creator.getEDevicesList();
	}

	public ArrayList<MyFilter> getUnactivatedFilterList() {
		ArrayList<MyFilter> allFilterList = getAllFilterList();
		ArrayList<MyFilter> unactivatedFilterList = new ArrayList<>();
		for (int i = 0; i < allFilterList.size(); i++) {
			if (!allFilterList.get(i).isActivate()) {
				unactivatedFilterList.add(allFilterList.get(i));
			}
		}
		return unactivatedFilterList;
	}

	public ArrayList<MyFilter> getActivatedFilterList() {
		ArrayList<MyFilter> allFilterList = getAllFilterList();
		ArrayList<MyFilter> activatedFilterList = new ArrayList<>();
		for (int i = 0; i < allFilterList.size(); i++) {
			if (allFilterList.get(i).isActivate()) {
				activatedFilterList.add(allFilterList.get(i));
			}
		}
		return activatedFilterList;
	}

	public ArrayList<ElectricDevice> getTurnedOffEDeviceList() {
		ArrayList<ElectricDevice> allEDeviceList = getAllEDevices();

		ArrayList<ElectricDevice> turnedOffEDeviceList = new ArrayList<>();
		for (int i = 0; i < allEDeviceList.size(); i++) {
			ElectricDevice device = allEDeviceList.get(i);
			if (!device.isTurnOn()) {
				turnedOffEDeviceList.add(device);
			}
		}
		return turnedOffEDeviceList;
	}

	public ArrayList<ElectricDevice> getTurnedOnEDeviceList() {
		ArrayList<ElectricDevice> allEDeviceList = getAllEDevices();

		ArrayList<ElectricDevice> turnedOnEDeviceList = new ArrayList<>();
		for (int i = 0; i < allEDeviceList.size(); i++) {
			ElectricDevice device = allEDeviceList.get(i);
			if (device.isTurnOn()) {
				turnedOnEDeviceList.add(device);
			}
		}
		return turnedOnEDeviceList;
	}
////////////////////////Arrays

///////////////////////Insert
	public String InsertAndTrim() {
		String string = insertManager.InsertAndTrim();
		return string;
	}
///////////////////////Insert

///////////////////////Validate
	public boolean isPositiveAndIsNumber(String string) {
		return validator.isPositiveAndIsNumber(string);
	}

	public boolean isNumber(String string) {
		return validator.isNumber(string);
	}

	public boolean isPositiveNumberLowerCountOfVariants(String message, Integer countOfPunkts) {
		return validator.isInsertPunktsValid(message, countOfPunkts);

	}
///////////////////////Validate

	public void makeMenuWithCommands(String headingTitle, LinkedHashMap<String, Command> NameOfChoiceAndCommand) {
		LinkedHashMap<String, Command> nameOfChoiceWithNumbersAndCommand = new LinkedHashMap<>();
		int counter = 1;
		for (Map.Entry<String, Command> entry : NameOfChoiceAndCommand.entrySet()) {
			nameOfChoiceWithNumbersAndCommand.put((counter++) + " - " + entry.getKey(), entry.getValue());
		}

		Command command = consoleSpeaker.showHeaderAndItems2Choice(headingTitle, nameOfChoiceWithNumbersAndCommand);
		command.execute();
	}

}

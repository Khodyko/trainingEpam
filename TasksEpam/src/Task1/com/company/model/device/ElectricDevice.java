package Task1.com.company.model.device;

import Task1.com.company.controller.Controller;
import Task1.com.company.controller.exception.InsertException;

public abstract class ElectricDevice {
	public Controller controller = Controller.getInstance();
	private boolean isTurnOn = false;

	public void setSystemOfSupply(SystemOfSupply systemOfSupply) {
		this.systemOfSupply = systemOfSupply;
	}

	private SystemOfSupply systemOfSupply;

	public SystemOfSupply getSystemOfSupply() {
		return systemOfSupply;
	}

	public boolean isTurnOn() {

		return isTurnOn;
	}

	public abstract void turnOn();

	public abstract void turnOff();

	private Integer voltage;
	private Integer amperage;

	public Integer getVoltage() {

		return voltage;

	}

	public Integer getAmperage() {

		return amperage;

	}

	public Integer getPower() {
		try {
			Integer power = getAmperage() * getVoltage();
			return power;
		} catch (NullPointerException e) {
			new InsertException("Amperage or Voltage is not activated, restart the programm and correct Data");
			return 0;
		}

	}

	public ElectricDevice(Integer voltage, Integer amperage) {
		this.voltage = voltage;
		this.amperage = amperage;
	}

}

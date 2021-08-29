package Task1.com.company.model.device;

import Task1.com.company.controller.exception.InsertException;

public abstract class BatteryDevice extends ElectricDevice {

	private boolean isTurnOn = false;

	public BatteryDevice(Integer voltage, Integer amperage) {
		super(voltage, amperage);
		super.setSystemOfSupply(SystemOfSupply.Battery);

	}

	@Override
	public void turnOn() {
		if (isTurnOn) {
			controller.showMessage(this.getClass().getSimpleName() + " is already working!");
		} else {
			isTurnOn = true;
			controller.showMessage("Batterry " + this.getClass().getSimpleName() + " put into the device. It works!");
		}
	}

	@Override
	public boolean isTurnOn() {
		return isTurnOn;
	}

	@Override
	public void turnOff() {

		if (isTurnOn) {
			isTurnOn = false;
			controller.showMessage(
					"Battery " + this.getClass().getSimpleName() + " take out of the device. It doesn't work!");

		} else {
			controller.showMessage(this.getClass().getSimpleName() + " is already turn off!");
		}
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
}

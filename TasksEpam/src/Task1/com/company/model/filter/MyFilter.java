package Task1.com.company.model.filter;

import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.controller.Controller;

public abstract class MyFilter {
	// May be it was needed to create name field, but i didn't do it,
	// that's why names of Filters must be "NameFilter". i use declaration of name
	// with this.getClass().getSimpleName().replace("Filter"," Filter")
	public Controller controller = Controller.getInstance();
	public boolean isActivate;

	public MyFilter() {
	}

	public abstract boolean isDeviceSuitable(ElectricDevice electricDevice);

	public void setActivate(boolean activate) { // I think, it isn't needed to override this method

		if (activate) {
			controller
					.showMessage("Activating " + this.getClass().getSimpleName().replace("Filter", " Filter") + "...");

			getParamForActivation();// Exception!!!!!!!!!???
			// if min>max??????
			this.isActivate = activate;
			controller.showMessage(
					this.getClass().getSimpleName().replace("Filter", " Filter") + " successfully activated!");
		} else {
			controller.showMessage(
					"Deactivating " + this.getClass().getSimpleName().replace("Filter", " Filter") + "...");

			this.isActivate = activate;
			controller.showMessage(
					this.getClass().getSimpleName().replace("Filter", " Filter") + "successfully Deactivated!");
		}

	}

	public boolean isActivate() {
		return this.isActivate;
	}

	public abstract void getParamForActivation(); // It's very important to put there setting of parameters that used in
													// Filtering, when override
}

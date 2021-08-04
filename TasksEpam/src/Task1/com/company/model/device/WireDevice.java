package Task1.com.company.model.device;


public abstract class WireDevice extends ElectricDevice{

    private boolean isTurnOn=false;


	public WireDevice(Integer voltage, Integer amperage) {
    	super(voltage,amperage);
		super.setSystemOfSupply(SystemOfSupply.Wire);

	}

	@Override
	public void turnOn() {
		if(isTurnOn) {

			controller.showMessage(this.getClass().getSimpleName()+ " is already working!");
		}
		else {
			isTurnOn=true;
			controller.showMessage("Plug of this " + this.getClass().getSimpleName()+ " put into the socket. It works!");
		}}

	@Override
	public boolean isTurnOn() {
		return isTurnOn;
	}




    @Override
    public void turnOff() {
    	
    	if(isTurnOn) {
			isTurnOn=false;
    		controller.showMessage("Plug of this " + this.getClass().getSimpleName()+ " take out of the socket. It doesn't work!");
    		
    	}
    	else {
    		controller.showMessage(this.getClass().getSimpleName()+ " is already turn off!");
    	}
    }


}

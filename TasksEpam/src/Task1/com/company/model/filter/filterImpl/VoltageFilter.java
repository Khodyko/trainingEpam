package Task1.com.company.model.filter.filterImpl;

import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.filter.MyFilter;

public class VoltageFilter extends MyFilter {

    private int maxVoltage;
    private int minVoltage;

    public int getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage() {
        Integer maxVoltageInt;
        controller.showMessage("Activating Voltage Filter...");
        controller.showMessage("Insert maximum Voltage value");
        String maxVoltageStr=controller.InsertAndTrim();
        if(controller.isPositiveAndIsNumber(maxVoltageStr)){
            maxVoltageInt=Integer.parseInt(maxVoltageStr);
            this.maxVoltage=maxVoltageInt;                    //Exception!!!!!!!!!1
        }
        else{
            controller.showMessage("Insert positive number value");
            setMinVoltage();
        }

    }

    public int getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage() {
        Integer minVoltageInt;
        controller.showMessage("Activating Voltage Filter...");
        controller.showMessage("Insert minimal Voltage value");
        String minVoltageStr=controller.InsertAndTrim();
        if(controller.isPositiveAndIsNumber(minVoltageStr)){
            minVoltageInt=Integer.parseInt(minVoltageStr);
            this.minVoltage=minVoltageInt;                    //Exception!!!!!!!!!1
        }
        else{
            controller.showMessage("Insert positive number value");
            setMinVoltage();
        }
    }

    public VoltageFilter( ) {
        super( );
    }

    @Override
    public boolean isDeviceSuitable(ElectricDevice electricDevice) {
        Integer voltage=electricDevice.getVoltage();
        if(minVoltage<=voltage && voltage<=maxVoltage){
            return true;
        }
        return false;

    }

    @Override
    public void setActivate(boolean activate) {
        super.setActivate(activate);
        isActivate=activate; // without it doesn't work
    }

    public boolean isActivate() {
        return isActivate;
    }

    @Override
    public void getParamForActivation() {
        setMinVoltage();
        setMaxVoltage(); //Exception!!!!!!!!!!!!?
    }
}

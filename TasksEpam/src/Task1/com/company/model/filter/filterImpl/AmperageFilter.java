package Task1.com.company.model.filter.filterImpl;

import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.filter.MyFilter;

public class AmperageFilter extends MyFilter {

    private int maxAmperage;
    private int minAmperage;

    public int getMaxAmperage() {
        return maxAmperage;
    }

    public void setMaxAmperage() {
        Integer maxAmperageInt;

        controller.showMessage("Insert maximum amperage value");
        String maxAmperageStr=controller.InsertAndTrim();
        if(controller.isPositiveAndIsNumber(maxAmperageStr)){
            maxAmperageInt=Integer.parseInt(maxAmperageStr);
            this.maxAmperage=maxAmperageInt;                    //Exception!!!!!!!!!1
        }
        else{
            controller.showMessage("Insert positive number value");
            setMinAmperage();
        }

    }

    public int getMinAmperage() {
        return minAmperage;
    }

    public void setMinAmperage() {
        Integer minAmperageInt;

        controller.showMessage("Insert minimal amperage value");
        String minAmperageStr=controller.InsertAndTrim();
        if(controller.isPositiveAndIsNumber(minAmperageStr)){
            minAmperageInt=Integer.parseInt(minAmperageStr);
            this.minAmperage=minAmperageInt;                    //Exception!!!!!!!!!1
        }
        else{
            controller.showMessage("Insert positive number value");
            setMinAmperage();
        }
    }

    public AmperageFilter( ) {
        super( );
    }

    @Override
    public boolean isDeviceSuitable(ElectricDevice electricDevice) {
        Integer amperage=electricDevice.getAmperage();
        if(minAmperage<=amperage && amperage<=maxAmperage){
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public boolean isActivate() {
        return isActivate;
    }

    @Override
    public void getParamForActivation() {
        setMinAmperage();
        setMaxAmperage();
    }

}

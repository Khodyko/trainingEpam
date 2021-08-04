package Task1.com.company.controller.commands;

import Task1.com.company.model.comparator.PowerComparator;
import Task1.com.company.model.device.ElectricDevice;
import Task1.com.company.model.filter.MyFilter;

import java.util.ArrayList;
import java.util.Collections;

public class SortEDeviceWithPower extends Command{
    @Override
    public void execute() {
        ArrayList<ElectricDevice> eDevicesList=controller.getTurnedOnEDeviceList();//All Turned On Devices
        ArrayList<MyFilter> filtersList=controller.getActivatedFilterList();//All active Filters
        ArrayList<ElectricDevice> eDevicesFiltered=new ArrayList<>();
        boolean deviceSuitForAllFilters=true;
        for (int i = 0; i < eDevicesList.size(); i++) {
            for (int j = 0; j < filtersList.size(); j++) {
                if((!filtersList.get(j).isDeviceSuitable(eDevicesList.get(i))) //  device not pass filter
                ){
                    deviceSuitForAllFilters=false;
                }
            }
            if(deviceSuitForAllFilters){
                eDevicesFiltered.add(eDevicesList.get(i));
            }
            deviceSuitForAllFilters=true;
        }
        controller.showSpecMessage("Devices with current filters");
        Collections.sort(eDevicesFiltered,new PowerComparator());
        for (int i = 0; i < eDevicesFiltered.size(); i++) {
            controller.showSpecMessage("Power of "+eDevicesFiltered.get(i).getClass().getSimpleName()+" is: "+eDevicesFiltered.get(i).getPower());

        }
        if(eDevicesFiltered.size()==0){
            controller.showSpecMessage("List is empty");
        }
        nameOfChoiceAndCommand.put("Back", new FirstMenu());

        controller.makeMenuWithCommands("",nameOfChoiceAndCommand);
    }
}
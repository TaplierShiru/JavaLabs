package VehiclesThreadTools.SimpleThread;

import BaseVehicle.Vehicle;

public class ModelNameWriterThread extends Thread {

    private Vehicle vehicle;

    public ModelNameWriterThread(Vehicle vehicle){
        super("MyModelNameWriter");
        this.vehicle = vehicle;
    }

    @Override
    public void run(){
        System.out.println("Names..");
        String[] modelNames = this.vehicle.getModelNames();

        /*
        for (String elem : modelNames){
            System.out.println(elem);
        }
        */

        for (int i = 0;i < modelNames.length; i++){
            System.out.println(modelNames[i]);
        }

    }
}

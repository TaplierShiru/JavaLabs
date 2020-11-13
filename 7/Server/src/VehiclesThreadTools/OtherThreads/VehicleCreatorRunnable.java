package VehiclesThreadTools.OtherThreads;

import BaseVehicle.Vehicle;
import CustomReader.VehicleReader;
import Vehicles.Car;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class VehicleCreatorRunnable implements Runnable {

    private String pathToVehicle;
    private BlockingQueue<Vehicle> que;

    public VehicleCreatorRunnable(String pathToVehicle, BlockingQueue<Vehicle> que){
        this.pathToVehicle = pathToVehicle;
        this.que = que;
    }

    @Override
    public void run() {
        // Read the mark name
        VehicleReader in = new VehicleReader(this.pathToVehicle);

        Vehicle newVehicle = new Car(in.readString(), 0);

        try {
            this.que.put(newVehicle);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package VehiclesThreadTools.OtherThreads;

import BaseVehicle.Vehicle;

public class VehicleMarkWriterRunnable implements Runnable{
    private Vehicle vehicle;

    public VehicleMarkWriterRunnable(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        System.out.println(vehicle.getMark());
    }

}

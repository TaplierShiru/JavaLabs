package VehiclesThreadTools.SimpleThread;

import BaseVehicle.Vehicle;

public class PriceWriterThread extends Thread {

    private Vehicle vehicle;

    public PriceWriterThread(Vehicle vehicle){
        super("MyPriceWriter");
        this.vehicle = vehicle;
    }

    @Override
    public void run(){
        System.out.println("Prices..");
        float[] prices = this.vehicle.getModelPrices();


        for (float elem : prices){
            System.out.println(elem);
        }

        /*
        for (int i = 0;i < prices.length; i++){
            System.out.println(prices[i]);
        }

         */
    }
}
